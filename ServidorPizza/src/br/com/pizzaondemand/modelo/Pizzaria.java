package br.com.pizzaondemand.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pizzaria implements java.io.Serializable {
	
    @Id
    @GeneratedValue
    private Long id;
    private String razao_social;
    private String nome_fantasia;
    private String email;
    private String senha;
    private String cnpj;
    private String telefone1;
    private String telefone2;
    private String telefone3;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private Double latitude;
    private Double longitude;
    private String mensagemPerfil;
    private byte[] imagemPerfil;
    @OneToMany(mappedBy="pizzaria", cascade=CascadeType.ALL) 
    private List<Produto> produtos;
    @OneToMany(mappedBy="pizzaria", cascade=CascadeType.ALL) 
    private List<Pedido> pedidos;
    @OneToMany(mappedBy = "pizzaria", cascade = CascadeType.ALL)
    private List<PizzariaFormaPagamento> pizzariasFormasPagamento;
    private Integer aceitaMeia;
    private Integer valorTamanho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getMensagemPerfil() {
        return mensagemPerfil;
    }

    public void setMensagemPerfil(String mensagemPerfil) {
        this.mensagemPerfil = mensagemPerfil;
    }

    public byte[] getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(byte[] imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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

    public Integer getAceitaMeia() {
        return aceitaMeia;
    }

    public void setAceitaMeia(Integer aceitaMeia) {
        this.aceitaMeia = aceitaMeia;
    }

    public Integer getValorTamanho() {
        return valorTamanho;
    }

    public void setValorTamanho(Integer valorTamanho) {
        this.valorTamanho = valorTamanho;
    }

}
