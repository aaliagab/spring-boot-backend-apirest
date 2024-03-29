package com.curso.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(nullable = false)
	private String nombre;
	private String apellidos;

	@Column(nullable = false)
	private String email;

	@Column(name = "create_at", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createAt;

	private String foto;

	@NotNull(message = "La region no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")//opcional, el crea por defecto foreing key con ese nombre
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;

	@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	//mappedBy = "cliente" utiliza el atributo cliente en la tabla facturas para la relacion
	private List<Factura> facturas;
		
	/*
	//asignando valor al campo antes de persistir en la BD
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	*/
	
	public Cliente() {
		this.facturas = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}





	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
}
