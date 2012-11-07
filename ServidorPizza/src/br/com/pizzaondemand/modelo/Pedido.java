package br.com.pizzaondemand.modelo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido implements java.io.Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String enderecoEntrega;
    private int status;
    private int quantidade;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private UsuarioAndroid usuarioAndroid;

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public UsuarioAndroid getUsuarioAndroid() {
        return usuarioAndroid;
    }

    public void setUsuarioAndroid(UsuarioAndroid usuarioAndroid) {
        this.usuarioAndroid = usuarioAndroid;
    }
    
}
