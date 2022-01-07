package br.com.foxi.controleveiculosapi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxi.controleveiculosapi.domain.Adresses;
import br.com.foxi.controleveiculosapi.domain.City;
import br.com.foxi.controleveiculosapi.domain.State;
import br.com.foxi.controleveiculosapi.repository.AdressesRepository;
import br.com.foxi.controleveiculosapi.repository.CityRepository;
import br.com.foxi.controleveiculosapi.repository.StateRepository;
import br.com.foxi.controleveiculosapi.service.exceptions.ObjectNotFoundException;

@Service
public class AdressesService {
    @Autowired
    private AdressesRepository adressesRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;

    public Adresses find(Integer id) {
        Optional<Adresses> obj = adressesRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Adresses.class.getName()));

    }

    public Adresses findZipCode(String zipCode) {

        Adresses obj = adressesRepository.findByZipCode(zipCode);
        if (obj == null) {
            return findNewZipCode(zipCode);
        }
        return obj;
    }

    private Adresses findNewZipCode(String zipCode) {
        Adresses adresses = new Adresses();
        City city2 = new City();
        String urlWebservice = "https://viacep.com.br/ws/" + zipCode + "/json/";
        try {
            URL url = new URL(urlWebservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output, json = "";
            while ((output = br.readLine()) != null) {
                json += output;
            }
            conn.disconnect();
            JSONObject jsonObject = new JSONObject(json);

            adresses.setStreet(jsonObject.getString("logradouro"));
            adresses.setComplement(jsonObject.getString("complemento"));
            adresses.setDistrict(jsonObject.getString("bairro"));
            adresses.setZipCode(zipCode);
            State state = stateRepository.findByAbbreviation(jsonObject.getString("uf"));
            City city = cityRepository.findByName(jsonObject.getString("localidade"));
            if (city == null) {
                city2.setName(jsonObject.getString("localidade"));
                city2.setCodeIbge(jsonObject.getString("ibge"));
                city2.setState(state);
                city2 = cityRepository.save(city2);
            }
            adresses.setCity(city2);
            adresses = adressesRepository.save(adresses);
        } catch (Exception e) {
            System.out.println("Erro aqui mobral: " + e);

        }
        return adresses;
    }

}
