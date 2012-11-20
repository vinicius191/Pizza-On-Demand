package br.com.pizzaondemand.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements java.io.Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 2056)
    private String descricao;
    private String enderecoEntrega;
    private int status;
    private Double valor;
    @ManyToOne
    private Pizzaria pizzaria;
    @ManyToOne
    private UsuarioAndroid usuarioAndroid;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private String dataPedido;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private String dataEntrega;
    private Double troco;
    @ManyToOne
    private FormaPagamento formaPagamento;
    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
    private List<ProdutoPedido> produtoPedidos;

    //    private Produto produto;
    //    private Produto produto;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pizzaria getPizzaria() {
        return pizzaria;
    }

    public void setPizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
    }

    public UsuarioAndroid getUsuarioAndroid() {
        return usuarioAndroid;
    }

    public void setUsuarioAndroid(UsuarioAndroid usuarioAndroid) {
        this.usuarioAndroid = usuarioAndroid;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ProdutoPedido> getProdutoPedidos() {
        return produtoPedidos;
    }

    public void setProdutoPedidos(List<ProdutoPedido> produtoPedidos) {
        this.produtoPedidos = produtoPedidos;
    }

 
}
