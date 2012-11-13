/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.modelo;

import java.util.List;

/**
 *
 * @author VINICIUS
 */
public class PizzariaWS {
    
    private Long id;
    private String razaoSocial;
    private String mensagemPerfil;
    private Double latitude;
    private Double longitude;
    private List<PizzariaFormaPagamento> formaPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getMensagemPerfil() {
        return mensagemPerfil;
    }

    public void setMensagemPerfil(String mensagemPerfil) {
        this.mensagemPerfil = mensagemPerfil;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<PizzariaFormaPagamento> getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(List<PizzariaFormaPagamento> formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    
}