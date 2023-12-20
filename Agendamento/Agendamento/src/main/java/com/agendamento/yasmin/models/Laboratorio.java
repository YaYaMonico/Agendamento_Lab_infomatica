package com.agendamento.yasmin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "laboratorios")
public class Laboratorio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtdcomputadores() {
		return qtdcomputadores;
	}
	public void setQtdcomputadores(int qtdcomputadores) {
		this.qtdcomputadores = qtdcomputadores;
	}
	private String nome;
	private int qtdcomputadores;
}

