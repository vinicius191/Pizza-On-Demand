<div style="padding-bottom:8px; font-weight: bold; padding-left: 20px; padding-top: 5px; border-bottom: 1px solid #773e3c; font-size: 16px; width: 80%; background:url('${pageContext.request.contextPath}/imagens/bg-titulos.png') repeat-x;">Menu</div>
<ul id="nav">
    <li><a href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/imagens/seta-01.png" border="0" style="padding-right: 7px; border: 0px;" />Página Inicial</a></li>
  <li><a href="#"><img src="${pageContext.request.contextPath}/imagens/seta-01.png" border="0" style="padding-right: 7px; border: 0px;" />Dados da Pizzaria</a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pizzariaFormaPagamento"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Forma de Pagamento</span></a></li>
        <li><a href="${pageContext.request.contextPath}/mensagemPerfil"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Mensagem do Perfil</span></a></li>
      <li><a href="${pageContext.request.contextPath}/imagemPerfil"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Imagem do Perfil</span></a></li>
	  <li><a href="${pageContext.request.contextPath}/editar"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Editar Cadastro</span></a></li>
          <li><a href="${pageContext.request.contextPath}/editarEmailSenha"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Alterar Email/Senha</span></a></li>
    </ul>
  </li>
  <li><a href="#"><img src="${pageContext.request.contextPath}/imagens/seta-01.png" border="0" style="padding-right: 7px; border: 0px;" />Cardápio</a>
    <ul>
      <li><a href="${pageContext.request.contextPath}/produto"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Cadastrar Produtos</span></a></li>
      <li><a href="${pageContext.request.contextPath}/listaProduto"><img src="${pageContext.request.contextPath}/imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Visualizar Produtos</span></a></li>
      <!--<li><a href="${pageContext.request.contextPath}/listaProduto"><img src="imagens/seta-02.png" border="0" style="padding-right: 7px; border: 0px;" /><span style="color: #b16e6c;">Editar Produtos</span></a></li>-->
    </ul>
  </li>
  <li><a href="${pageContext.request.contextPath}/logout"><img src="${pageContext.request.contextPath}/imagens/seta-01.png" border="0" style="padding-right: 7px; border: 0px;" />Sair</a></li>
</ul>

<script type="text/javascript">
    $(document).ready(function() {
       jQuery('#nav').dcAccordion(); 
    });
</script>