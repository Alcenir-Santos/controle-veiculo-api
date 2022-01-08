package br.com.foxi.controleveiculosapi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_driver")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String document;
    private String email;
    private String cellPhone;
    private String license;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date validateLicense;

    @ManyToOne
    @JoinColumn(name = "adresses_id")
    private Adresses adresses;

    public Driver() {
    }

    public Driver(Integer id, String fullName, String document, String email, String license, Date validateLicense,
            Adresses adresses) {
        this.id = id;
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.license = license;
        this.validateLicense = validateLicense;
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Date getValidateLicense() {
        return validateLicense;
    }

    public void setValidateLicense(Date validateLicense) {
        this.validateLicense = validateLicense;
    }

    public Adresses getAdresses() {
        return adresses;
    }

    public void setAdresses(Adresses adresses) {
        this.adresses = adresses;
    }

}
