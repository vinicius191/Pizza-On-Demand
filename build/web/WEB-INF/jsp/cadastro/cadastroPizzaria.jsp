<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" media="all">
        <!-- Scripts -->
        <script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.min.js"></script>
        <!-- Inclusão do Jquery Validate -->
        <script type="text/javascript" src="scripts/jquery.validate.js" ></script>
        <script type="text/javascript" src="scripts/jquery.maskedinput-1.3.js" ></script>
        <script type="text/javascript" src="http://igorescobar.github.com/jQuery-Mask-Plugin/jquery.mask.js"></script>
        <style>
            td {
                padding: 5px;
            }

        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Pizza - On Demand! | Cadastro Pizzaria</title>
    </head>
    <body>
        <div id="container">
            <form id="formularioCadastroPizzaria" action="${pageContext.request.contextPath}/cadastrarPizzaria" method="post">
                <table border="0" width="850px" align="center" style="color: #5a0805; font-size: 18px;">
                    <tr>
                        <td colspan="2" style="text-align: center; font-size: 12px;">
                            <img src="imagens/logo_inicial.png" width=20% height=35% />
                        </td>
                    </tr>
                    <!-- 	<tr> -->
                    <!-- 		<td colspan="2" style="text-align: center; font-weight:bold; padding-top: 20px; padding-bottom: 10px;">Cadastro da sua Pizzaria</td> -->
                    <!-- 	</tr> -->
                    <tr><td colspan="2" style="height: 30px; text-align: right; font-size: 14px;"><a href="${pageContext.request.contextPath}/">&laquo; Voltar</a></td></tr>
                    <tr>
                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 16px; font-weight: bold; padding-bottom: 10px;">Dados de acesso ao Sistema</td>
                    </tr>
                    <tr><td colspan="2" style="height: 20px;"></td></tr>	
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Email da Pizzaria: </td>
                        <td>
                            <input type="text" name="pizzaria.email" id="pizzaria.email" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" />
                            <span style="font-size: 11px; padding-left: 20px;">Este será o login utilizado para entrar no sistema</span>
                        </td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Senha: </td>
                        <td>
                            <input type="password" name="pizzaria.senha" id="pizzaria.senha" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" />
                            <span style="font-size: 11px; padding-left: 20px;">Esta será a senha utilizada para entrar no sistema</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 16px; font-weight: bold; padding-bottom: 10px;">Dados da sua Pizzaria</td>
                    </tr>
                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Razão Social: </td>
                        <td><input type="text" name="pizzaria.razao_social" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">CNPJ da Pizzaria: </td>
                        <td>
                            <input type="text" name="pizzaria.cnpj" id="cnpj" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" />
                        </td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Telefone 1 da Pizzaria: </td>
                        <td>
                            <input type="text" name="pizzaria.telefone1" id="telefone1" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" />
                        </td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Telefone 2 da Pizzaria: </td>
                        <td>
                            <input type="text" name="pizzaria.telefone2" id="telefone2" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" />
                        </td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Telefone 3 da Pizzaria: </td>
                        <td>
                            <input type="text" name="pizzaria.telefone3" id="telefone3" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 16px; font-weight: bold; padding-bottom: 10px;">Sua localização</td>
                    </tr>
                    <tr><td colspan="2" style="height: 20px;"></td></tr>	
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Endereço da Pizzaria: </td>
                        <td><input type="text" name="pizzaria.endereco" id="pizzaria.endereco" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Complemento: </td>
                        <td><input type="text" name="pizzaria.complemento" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">CEP: </td>
                        <td><input type="text" name="pizzaria.cep" id="cep" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Bairro: </td>
                        <td><input type="text" name="pizzaria.bairro" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Cidade: </td>
                        <td><input type="text" name="pizzaria.cidade" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Estado: </td>
                        <!--                        <td><input type="text" name="pizzaria.estado" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>-->
                        <td>
                            <select name="pizzaria.estado" id="pizzaria.estado" style="width: 20%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;">
                                <option value="">Selecione</option>
                                <option value="AC">AC</option>
                                <option value="AL">AL</option>
                                <option value="AM">AM</option>
                                <option value="AP">AP</option>
                                <option value="BA">BA</option>
                                <option value="CE">CE</option>
                                <option value="DF">DF</option>
                                <option value="ES">ES</option>
                                <option value="GO">GO</option>
                                <option value="MA">MA</option>
                                <option value="MG">MG</option>
                                <option value="MS">MS</option>
                                <option value="MT">MT</option>
                                <option value="PA">PA</option>
                                <option value="PB">PB</option>
                                <option value="PE">PE</option>
                                <option value="PI">PI</option>
                                <option value="PR">PR</option>
                                <option value="RJ">RJ</option>
                                <option value="RN">RN</option>
                                <option value="RO">RO</option>
                                <option value="RR">RR</option>
                                <option value="RS">RS</option>
                                <option value="SC">SC</option>
                                <option value="SE">SE</option>
                                <option value="SP">SP</option>
                                <option value="TO">TO</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Latitude: </td>
                        <td><input type="text" name="pizzaria.latitude" id="pizzaria.latitude" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr>
                        <td style="font-size: 14px; width: 200px; padding-left: 10px;">Longitude: </td>
                        <td><input type="text" name="pizzaria.longitude" id="pizzaria.longitude" style="width: 25%; height: 20px; border: 1px solid #5a0805; padding-left: 20px;" /></td>
                    </tr>
                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                    <tr>
                        <td colspan="2" style="height: 30px; font-size: 11px;">
                            <div style="padding: 20px; border:1px dotted #5a0805; line-height: 1.8em">Precisamos saber a Latitude e a Longitude da sua Pizzaria. Siga os passos abaixo para descobrir estes dados.<br/><br/>
                                1) Acesse <a href="http://www.procriativo.com.br/include/app/google-longitude-latitude.php" target="_blank">http://www.procriativo.com.br/include/app/google-longitude-latitude.php</a> digite seu endereço e clique em "Procurar".<br/>
                                2) Copie os número de "Latitude / Longitude" que aparecem abaixo do mapa e cole nos campos deste formulário.</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="height: 50px; text-align: center;">
                            <input type="submit" id="enviar" value="Cadastrar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                            <input type="reset" id="enviar" value="Limpar" style="margin-left: 80px;  width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                        </td>	
                    </tr>
                </table>
            </form>
            <div style="display: none;" id="dialog-validation" class="errorList"></div>
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
                // Mascaras para os campos do formulario
                $("#cnpj").mask("99.999.999/9999-99");
                $("#cep").mask("99999-999");
                $("#telefone1").mask('(00) 0000-0000',
                {onKeyPress: function(phone, e, currentField, options){
                        var new_sp_phone = phone.match(/^(\(11\) 9(5[0-9]|6[0-9]|7[01234569]|8[0-9]|9[0-9])[0-9]{1})/g);
                        new_sp_phone ? $(currentField).mask('(00) 00000-0000', options) : $(currentField).mask('(00) 0000-0000', options)
                    }
                });
                $("#telefone2").mask('(00) 0000-0000',
                {onKeyPress: function(phone, e, currentField, options){
                        var new_sp_phone = phone.match(/^(\(11\) 9(5[0-9]|6[0-9]|7[01234569]|8[0-9]|9[0-9])[0-9]{1})/g);
                        new_sp_phone ? $(currentField).mask('(00) 00000-0000', options) : $(currentField).mask('(00) 0000-0000', options)
                    }
                });
                $("#telefone3").mask('(00) 0000-0000',
                {onKeyPress: function(phone, e, currentField, options){
                        var new_sp_phone = phone.match(/^(\(11\) 9(5[0-9]|6[0-9]|7[01234569]|8[0-9]|9[0-9])[0-9]{1})/g);
                        new_sp_phone ? $(currentField).mask('(00) 00000-0000', options) : $(currentField).mask('(00) 0000-0000', options)
                    }
                });


                $("#dialog-validation").dialog({
                    position: ['middle',20],
                    modal: false,
                    autoOpen: false,
                    title: "Verifique os campos abaixo",
                    width: 380,
                    resizable: false,
                    buttons: { 
                        Fechar: function() {
                            $(this).text("");
                            $(this).dialog("close");
                        }
                    },
                    close: function() {
                        $(this).text("");
                    }
                });

                $('#formularioCadastroPizzaria').validate({
                    errorPlacement: function(error) {
                        error.prependTo('#dialog-validation');
                        $("#dialog-validation").dialog("open");
                    },
                    errorLabelContainer: $("dialog-validation ul"),
                    onblur: false,
                    onkeyup: false,
                    onsubmit: true,
                    onfocusout: false,
                    onclick: false,
                    debug: false,
                    sucess: "valid",
                    wrapper: "li",
                    rules:{
                        "pizzaria.email": {
                            required: true,
                            email: true,
                            remote: {  
                               url: 'verificaEmailCadastro'
                            }
                        },
                        "pizzaria.senha": {
                            required: true
                        },
                        "pizzaria.razao_social": {
                            required: true
                        },
                        "pizzaria.cnpj": {
                            required: true
                        },
                        "pizzaria.telefone1": {
                            required: true
                        },
                        "pizzaria.longitude": {
                            required: true,
                            number: true
                        },
                        "pizzaria.latitude": {
                            required: true,
                            number: true
                        },
                        "pizzaria.endereco": {
                            required: true
                        },
                        "pizzaria.cep": {
                            required: true
                        },
                        "pizzaria.bairro": {
                            required: true
                        },
                        "pizzaria.cidade": {
                            required: true
                        },
                        "pizzaria.estado": {
                            required: true
                        }

                    },
                    messages:{
                        "pizzaria.email": {
                            required: "O campo <b>Email da Pizzaria</b> é obrigatório.",
                            email: "O campo <b>Email da Pizzaria</b> deve conter um email válido.",
                            remote: "O <b>Email da Pizzaria</b> informado ja esta em uso."
                        },
                        "pizzaria.senha": {
                            required: "O campo <b>Senha</b> é obrigatório."
                        },
                        "pizzaria.razao_social": {
                            required: "O campo <b>Razão Social</b> é obrigatório."
                        },
                        "pizzaria.cnpj": {
                            required: "O campo <b>CNPJ</b> é obrigatório."
                        },
                        "pizzaria.telefone1": {
                            required: "O campo <b>Telefone 1</b> é obrigatório."
                        },
                        "pizzaria.latitude": {
                            required: "O campo <b>Latitude</b> é obrigatório.",
                            number: "Verifique a formatação do campo <b>Latitude.</b>"
                        },
                        "pizzaria.longitude": {
                            required: "O campo <b>Longitude</b> é obrigatório.",
                            number: "Verifique a formatação do campo <b>Latitude.</b>"
                        },
                        "pizzaria.endereco": {
                            required: "O campo <b>Endereço da Pizzaria</b> é obrigatório."
                        },
                        "pizzaria.cep": {
                            required: "O campo <b>CEP</b> é obrigatório."
                        },
                        "pizzaria.bairro": {
                            required: "O campo <b>Bairro</b> é obrigatório."
                        },
                        "pizzaria.cidade": {
                            required: "O campo <b>Cidade</b> é obrigatório."
                        },
                        "pizzaria.estado": {
                            required: "O campo <b>Estado</b> é obrigatório."
                        }
                    },
                    showErrors: function() {
                        this.defaultShowErrors();
//                        $("#dialog-validation").dialog('open');
                    }    
                });        
    
            });
            
//            jQuery(function($){
//                $("#pizzaria.cnpj").mask("99.999.999/9999-99");
//            });
//            
//            $(document).ready(function(){
//                alert("2");
//                $("#cnpj").mask("99.999.999/9999-99");
//                
//                $('#formularioCadastroPizzaria').validate({
//                    errorPlacement: function(error, element) {
//                        error.prependTo('#dialog-validation');
//                    },
//                    errorLabelContainer: $("dialog-validation ul"),
//                    onblur: false,
//                    onkeyup: false,
//                    onsubmit: true,
//                    onfocusout: false,
//                    wrapper: "li",
//                    rules:{
//                        "pizzaria.email": {
//                            required: true,
//                            email: true
////                            remote: {  
////                                url: "<c:url value='/verificaEmail' />",  
////                                type: post,
////                                data: {
////                                    return $("#pizzaria.email").val();
////                                }
////                            }
//                        },
//                        "pizzaria.senha": {
//                            required: true,
//                            min: 6
//                        },
//                        "pizzaria.razao_social": {
//                            required: true
//                        },
//                        "pizzaria.cnpj": {
//                            required: true
//                        },
//                        "pizzaria.telefone1": {
//                            required: true,
//                            min: 16
//                        },
//                        "pizzaria.telefone2": {
//                            required: false,
//                            minlenght: 14
//                        },
//                        "pizzaria.telefone3": {
//                            required: false,
//                            minlenght: 14
//                        }
//                    },
//                    messages:{
//                        "pizzaria.email": {
//                            required: "O campo Email da Pizzaria é obrigatório.",
//                            email: "O campo Email da Pizzaria deve conter um email válido.",
//                            remote: "O email da existe"
//                        },
//                        "pizzaria.senha": {
//                            required: "O campo Senha é obrigatorio.",
//                            min: "O campo Senha precisa ter pelo menos 6 caracteres.",
//                            max: "O campo Senha precisa ter no máximo 12 caracteres."
//                        },
//                        "pizzaria.razao_social": {
//                            required: "O campo Razão Social é obrigatório."
//                        },
//                        "pizzaria.cnpj": {
//                            required: "O campo CNPJ é obrigatório."
//                        },
//                        "pizzaria.telefone1": {
//                            required: "O campo Telefone 1 é obrigatório.",
//                            min: "O campo Telefone 1 pode ter no máximo 16 digitos."
//                        },
//                        "pizzaria.telefone2": {
//                            minlenght: "O campo Telefone 2 tem poucos digitos."
//                        },
//                        "pizzaria.telefone3": {
//                            minlenght: "O campo Telefone 3 tem poucos digitos."
//                        }
//                    },
//                    showErrors: function() {
//                        $("#dialog-validation").dialog('open');
//                        this.defaultShowErrors();
//                    }    
//                });
//            });
            
            

        </script>

    </body>
</html>