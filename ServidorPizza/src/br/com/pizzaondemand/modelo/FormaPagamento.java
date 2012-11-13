/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author VINICIUS
 */
@Entity
public class FormaPagamento implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String descricao;
    @OneToMany(mappedBy="formaPagamento")
    private List<Pedido> pedidos;
    @OneToMany(mappedBy = "formaPagamento")
    private List<PizzariaFormaPagamento> pizzariasFormasPagamento;
//    @OneToMany(mappedBy="formaPagamento")
//    private List<Pizzaria> pizzarias;

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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<PizzariaFormaPagamento> getPizzariasFormasPagamento() {
        return pizzariasFormasPagamento;
    }

    public void setPizzariasFormasPagamento(List<PizzariaFormaPagamento> pizzariasFormasPagamento) {
        this.pizzariasFormasPagamento = pizzariasFormasPagamento;
    }




    
    
}
