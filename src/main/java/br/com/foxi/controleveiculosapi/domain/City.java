package br.com.foxi.controleveiculosapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "t_city")
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @NotEmpty(message = "Nome nao pode ser em branco")
    @Column(nullable = false)
	private String name;
    private String codeIbge;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	public City() {
		
	}

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCodeIbge() {
        return codeIbge;
    }

    public void setCodeIbge(String codeIbge) {
        this.codeIbge = codeIbge;
    }


}
