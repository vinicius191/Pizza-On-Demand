<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" media="all">
        <!-- Scripts -->
        <script src="scripts/jquery-1.8.2.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.min.js"></script>
        <script src="scripts/jquery.cookie.js"></script>
        <script src="scripts/jquery.dcjqaccordion.2.6.min.js"></script>
        <script src="scripts/jquery.hoverIntent.minified.js"></script>
        <!-- Inclusão do Jquery Validate -->
        <script type="text/javascript" src="scripts/jquery.validate.js" ></script>
        <script type="text/javascript" src="scripts/jquery.maskedinput-1.3.js" ></script>
        <script type="text/javascript" src="http://igorescobar.github.com/jQuery-Mask-Plugin/jquery.mask.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza - On Demand! | Página Inicial</title>
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
                        <div style="padding: 20px;">
                            Seja Bem-Vindo ${usuarioSession.user.nome_fantasia}. No momento o site esta em manutenção. Clique <a href="${pageContext.request.contextPath}/logout"><b>aqui</b></a> para sair.

                        </div>
                    </td>
                </tr>

                <tr>
                    <%-- 	<td>Desculpe-nos ${usuarioSession.user.nomePizzaria}, mas no momento o site esta em manutenção. Clique <a href="${pageContext.request.contextPath}/logout"><b>aqui</b></a> para sair.</td> --%>
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
            
//                jQuery('#nav').dcAccordion();
            });

        </script>

    </body>
</html>