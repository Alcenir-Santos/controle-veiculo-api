package br.com.foxi.controleveiculosapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_states")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(unique = true, precision = 2, nullable = false)
    private String abbreviation;

    public State(Integer id, String name, String abbreviation) {
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
	}
	// @ElementCollection(fetch = FetchType.EAGER)
    // @CollectionTable(name = "t_user_roles", joinColumns = @JoinColumn(name = "id"))
    // @Column(name = "role_id")
    // private List<String> roles = new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

    

}
