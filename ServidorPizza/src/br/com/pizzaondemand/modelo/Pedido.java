package br.com.pizzaondemand.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Pedido implements java.io.Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String enderecoEntrega;
    private int status;
    private Double valor;
    @ManyToOne
    private Pizzaria pizzaria;
    @ManyToOne
    private UsuarioAndroid usuarioAndroid;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPedido;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEntrega;
    private Double troco;
    @ManyToOne
    private FormaPagamento formaPagamento;
    @OneToMany(mappedBy="pedido")
    private List<ProdutoPedido> produtoPedidos;
    @ManyToOne
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
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
