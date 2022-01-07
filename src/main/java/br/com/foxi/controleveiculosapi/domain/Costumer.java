package br.com.foxi.controleveiculosapi.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "t_costumer")
public class Costumer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Nome completo obrigatório")
    private String fullName;
    @NotEmpty(message = "Documento obrigatório")
    private String document;
    @NotEmpty(message = "Email obrigatório")
    @Email
    private String email;
    
	@ElementCollection
	@CollectionTable(name = "t_cellphones")
	private Set<String> cellPhone = new HashSet<>();

    @ManyToOne
	@JoinColumn(name = "adresses_id")
    private Adresses adresses;
    
    public Costumer() {
    
    }
    public Costumer(Integer id, @NotEmpty(message = "Nome completo obrigatório") String fullName,
            @NotEmpty(message = "Documento obrigatório") String document,
            @NotEmpty(message = "Email obrigatório") @Email String email, Set<String> cellPhone, Adresses adresses) {
        this.id = id;
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.cellPhone = cellPhone;
        this.adresses = adresses;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<String> getCellPhone() {
        return cellPhone;
    }
    public void setCellPhone(Set<String> cellPhone) {
        this.cellPhone = cellPhone;
    }
    public Adresses getAdresses() {
        return adresses;
    }
    public void setAdresses(Adresses adresses) {
        this.adresses = adresses;
    }

    
}
