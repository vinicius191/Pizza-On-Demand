<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza - On Demand! | Lista Produtos</title>
    </head>
    <body>
<!--        <script>
            alert("ID da pizzariaperfil: " + ${usuarioSession.user.nome_fantasia});
        </script>-->
        <div id="container">
            <table border="0" width="850px" align="center" style="text-align: center; color: #5a0805; font-size: 14px;">
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <img src="imagens/logo_inicial.png" width=20% height=35% />
                    </td>
                </tr>
                <tr><td colspan="2" style="height: 30px;"></td></tr>
                <tr>
                    <td height="150px" style="text-align: left; padding-left: 18px;">
                <c:if test="${pizzaria.imagemPerfil != null}">
                    <img class="imagem" src="<c:url value="/mostraImagem/${pizzaria.id}/imagem"/>" width="150" height="150" />
                </c:if>
                <c:if test="${pizzaria.imagemPerfil == null}">
                    <div class="imagem" style="width: 150px; height: 150px;">
                        <div style="padding-top: 67px; padding-left: 28px; color: #d1cfbd;">
                            Sem Imagem
                        </div>
                    </div>
                </c:if>                    
                </td>
                <td style="vertical-align: top;">
                    <div style="text-align: justify; background-image: url('imagens/fundomensagem.png'); background-repeat: no-repeat; width: 574px; height: 164px;">
                        <c:if test="${pizzaria.mensagemPerfil != null}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 25px;">
                            ${pizzaria.mensagemPerfil}
                        </div>
                        </c:if>
                        <c:if test="${pizzaria.mensagemPerfil == null}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 67px; color: #d1cfbd; text-align: center;">
                            Sem Mensagem de Perfil
                        </div>
                        </c:if>
                        <c:if test="${pizzaria.mensagemPerfil == ''}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 42px; color: #d1cfbd; text-align: center;">
                            Sem Mensagem de Perfil
                        </div>
                        </c:if>
                    </div>
                </td>			
                </tr>
                <tr>
                    <td width="220px" style="padding-top: 30px; text-align: left; vertical-align: top;">

                        <jsp:include page="../template/menu.jsp" />

                    </td>
                    <td style="padding-top: 30px; text-align: left;  vertical-align: top;">
                        <jsp:include page="../template/conteudo.jsp" />
                            <div style="padding:20px">                        
                                <table border="0">
                                    <tr style="text-align: center; background:url('imagens/bg-titulos.png') repeat-x; font-weight: bold;">
                                        <td style="border-bottom: 1px solid #5a0805; border-left: 1px solid #5a0805; padding: 5px; width: 220px;">Descrição</td>
                                        <td style="border-bottom: 1px solid #5a0805; border-left: 1px solid #5a0805; padding: 5px;">Tipo do produto</td>
                                        <td style="border-bottom: 1px solid #5a0805; border-left: 1px solid #5a0805; padding: 5px; width: 180px;">Ação</td>
                                    </tr>
                                    <c:forEach var="produto" items="${produto}">
                                        <tr style="text-align: center;">
                                            <td style="padding: 5px; border-bottom: 1px dotted #99967d; border-left: 1px solid #5a0805;">${produto.descricao}</td>
                                            <td style="padding: 5px; border-bottom: 1px dotted #99967d; border-left: 1px solid #5a0805;">${produto.tipo}</td>
                                            <td style="padding: 5px; border-bottom: 1px dotted #99967d; border-left: 1px solid #5a0805;"><s>Editar</s> | <a href="<c:url value="/excluiProduto/${produto.id}/${produto.pizzaria.id}"/>">Excluir</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                    </td>
                </tr>
            </table>

            <div id="footer">
                Copyright 2012 © Pizza - On Demand! - Todos os direitos Reservados.
            </div>
        </div>

        <script type="text/javascript">
            //Execute the function when window load
            $(document).ready(function() {
   
                var docHeight = $(window).height();
                var footerHeight = $('#footer').height();
                var footerTop = $('#footer').position().top + footerHeight;
   
                if (footerTop < docHeight) {
                    $('#footer').css('margin-top', 10 + (docHeight - footerTop) + 'px');
                }
            });
        </script>

    </body>
</html>