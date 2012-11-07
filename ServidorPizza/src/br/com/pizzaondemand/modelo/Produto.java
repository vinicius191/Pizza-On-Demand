package br.com.pizzaondemand.modelo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto implements java.io.Serializable{
    
    @Id @GeneratedValue
    private Long id;
    private String detalhe;
    private char tipo;
    private Double preco1; 
    private Double preco2; 
    private Double preco3;
    private String descricao;
    private String extraDescricao;
    private Double extraPreco;    
    @ManyToOne
    private Pizzaria pizzaria;
    @OneToMany(mappedBy="produto")
    private List<Pedido> pedidos;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getExtraDescricao() {
        return extraDescricao;
    }

    public void setExtraDescricao(String extraDescricao) {
        this.extraDescricao = extraDescricao;
    }

    public Double getExtraPreco() {
        return extraPreco;
    }

    public void setExtraPreco(Double extraPreco) {
        this.extraPreco = extraPreco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pizzaria getPizzaria() {
        return pizzaria;
    }

    public void setPizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
    }

    public Double getPreco1() {
        return preco1;
    }

    public void setPreco1(Double preco1) {
        this.preco1 = preco1;
    }

    public Double getPreco2() {
        return preco2;
    }

    public void setPreco2(Double preco2) {
        this.preco2 = preco2;
    }

    public Double getPreco3() {
        return preco3;
    }

    public void setPreco3(Double preco3) {
        this.preco3 = preco3;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

}
