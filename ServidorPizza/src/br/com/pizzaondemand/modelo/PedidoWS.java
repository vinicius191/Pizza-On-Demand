/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.modelo;

/**
 *
 * @author cvinicius
 */
public class PedidoWS {

    private Long id;
    private String enderecoEntrega;
    private int status;
    private int quantidade;
    private String produto;
    private String usuarioAndroid;

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getUsuarioAndroid() {
        return usuarioAndroid;
    }

    public void setUsuarioAndroid(String usuarioAndroid) {
        this.usuarioAndroid = usuarioAndroid;
    }

 

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    
    
}
