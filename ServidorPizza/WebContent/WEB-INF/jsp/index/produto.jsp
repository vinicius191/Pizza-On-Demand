<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <!-- Inclusão do Jquery Validate -->
        <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.6/jquery.validate.js" ></script>
        <title>Pizza - On Demand! | Cadastro de Produtos</title>
    </head>
    <body>
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
                            <form id="formAtualizaCadastro" action="${pageContext.request.contextPath}/cadastraProduto/${pizzaria.id}" method="POST">
<!--                            <form id="formAtualizaCadastro"> -->
                                <table border="0" width="94%" align="left" style="color: #5a0805; font-size: 18px;">
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Cadastro de produtos</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;" width="150px">Tipo do produto:
                                            <!--<input type="text" name="produto.tipo" id="produto.tipo" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />-->
                                            <select name="produto.tipo" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;">
                                                <option value="1">1 - Pizza</option>
                                                <option value="2">2 - Bebidas</option>
                                                <option value="3">3 - Outros</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Titulo do produto:
                                            <input type="text" name="produto.descricao" id="produto.descricao" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Ingredientes:
                                            <input type="text" name="produto.detalhe" id="produto.detalhe" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Preço:
                                            <input type="text" name="produto.preco" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="height: 50px;"></td>
                                    </tr>    
                                    <tr>
                                        <td colspan="2" style="height: 50px; text-align: center;">
                                            <input type="submit" id="enviar" value="Cadastrar" style="font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                            <input type="reset" id="limpar" value="Limpar" style="margin-left: 40px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                        </td>	
                                    </tr>
                                </table>
                            </form>                      
                        </div>
                    </td>
                </tr>
                <tr>
                </tr>
            </table>
            <div style="display: none;" id="dialog" class="errorList"></div>
            <div id="footer">
                Copyright 2012  Pizza - On Demand! - Todos os direitos Reservados.
            </div>
        </div>

        <script type="text/javascript">
            //Execute the function when window load
            $(document).ready(function() {
   
                $('#pizzaria_estado').val( '${pizzaria.estado}' );
   
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
