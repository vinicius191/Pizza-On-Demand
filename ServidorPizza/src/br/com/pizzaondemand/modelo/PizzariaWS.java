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
    private String nomeFantasia;
    private String mensagemPerfil;
    private Double latitude;
    private Double longitude;
    private List<PizzariaFormaPagamento> formaPagamento;
    private List<String> pagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
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

    public List<String> getPagamento() {
        return pagamento;
    }

    public void setPagamento(List<String> pagamento) {
        this.pagamento = pagamento;
    }

    
}
