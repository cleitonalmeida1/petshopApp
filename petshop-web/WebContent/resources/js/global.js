// ################################
// #### Funções de Inicilização ###
// ################################

//Funções que iniciam ao carregar a página
jQuery(document).ready(function() {
	mascaraElementosTypeTel(document, 'input', 'type', 'tel'); //Qualquer Página que tenha Input Type = Tel
});
(jQuery);


// ################################
// ##### Funções de Validação #####
// ################################

//Função Valida Formulário da Página de Contato
function validaFormContato(idElemento, idAviso){
	elemento = document.getElementById(idElemento);
	if (elemento.nome.value==""){
		avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe seu Nome.', 800);
		elemento.nome.focus();
	}else if (elemento.nome.value.length <= 1){
		avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe um Nome Válido.', 800);
		elemento.nome.focus();
	}else if (elemento.email.value==""){
		avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe seu E-mail.', 800);
		elemento.email.focus();
	}else if ((elemento.email.value != "") && (!validaEmail(elemento.email))){
		avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe um E-mail Válido.', 800);
		elemento.email.focus();
	}else if (elemento.mensagem.value == ""){
		avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe uma Mensagem.', 800);
		elemento.mensagem.focus();
	}else if (elemento.mensagem.value.length < 10){
		avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe uma Mensagem Válida (acima de 10 caracteres).', 800);
		elemento.mensagem.focus();
	}else{
		enviaFormAjax(elemento, idAviso, '<div class="warning">', '</div>', 'Sua mensagem foi enviada e em breve será respondida.', 'Sua mensagem não foi enviada. Tente novamente.');
	}	
}

//Função Valida Formulário da Página de Cadastro
function validaFormCadastro(idElemento, idAviso){
	elemento = document.getElementById(idElemento);
	if (elemento.nome.value==""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe seu Nome.', 800);
		focusInputTextarea(elemento.nome);
	}else if (dividirString(elemento.nome.value, " ").length <= 1){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um Nome Completo Válido. Ex: José Silva (mais de um nome).', 800);
		focusInputTextarea(elemento.nome);
	}else if (elemento.email.value==""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe seu E-mail.', 800);
		focusInputTextarea(elemento.email);
	}else if ((elemento.email.value != "") && (!validaEmail(elemento.email))){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um E-mail Válido.', 800);
		focusInputTextarea(elemento.email);
	}else if (elemento.senha.value==""){
		elemento.senha.value = '';
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe uma Senha.', 800);
		focusInputTextarea(elemento.senha);
	}else if (elemento.senha.value.length < 6 || elemento.senha.value.length > 8){
		elemento.senha.value = '';
		elemento.senharepetida.value = '';
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe uma Senha com no Mínimo 6 e no Máximo 8 caracteres.', 800);
		focusInputTextarea(elemento.senha);
	}else if (elemento.senharepetida.value==""){
		elemento.senharepetida.value = '';
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Repita a Senha.', 800);
		focusInputTextarea(elemento.senharepetida);
	}else if (elemento.senharepetida.value != elemento.senha.value){
		elemento.senharepetida.value = '';
		elemento.senha.value = '';
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'As Senhas não correspondem. Digite novamente.', 800);
		focusInputTextarea(elemento.senha);
	}else if (elemento.cpf.value == ""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe seu CPF.', 800);
		focusInputTextarea(elemento.cpf);
	}else if (!cpfValida(substituirCaracteresEmString(elemento.cpf.value, ['.', '.', '\/', '-', '-', ' ', '  ', '_', '/', '\\'], ''))){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um CPF Válido.', 800);
		elemento.cpf.value = '';
		focusInputTextarea(elemento.cpf);
	}else if (elemento.datanasc.value == ""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe sua Data de Nascimento.', 800);
		focusInputTextarea(elemento.datanasc);
	}else if (!stringDataValida(substituirCaracteresEmString(elemento.datanasc.value, ['\/', '-', '-', ' ', '  ', '_', '/', '\\'], ''))){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe uma Data de Nascimento Válida.', 800);
		elemento.datanasc.value = '';
		focusInputTextarea(elemento.datanasc);
	}else if(!validaInputRadio(listaElementosPorNome(document, 'sexo'))){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Selecione o Sexo.', 800);
	}else if(elemento.telefone.value != "" && !validaTelefoneFixoBrasil(substituirCaracteresEmString(buscaElementoPorTagAtributoEValorAtributo(document, 'input', 'name', 'telefone').value, ['(', ')', '-', ' ', '_', '_', '_', ' '], ''))){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um Telefone Válido.', 800);
		elemento.telefone.value = '';
		focusInputTextarea(elemento.telefone);
	}else if(elemento.celular.value == ""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um número de Celular.', 800);
		focusInputTextarea(elemento.celular);
	}else if(!validaCelularBrasil(substituirCaracteresEmString(buscaElementoPorTagAtributoEValorAtributo(document, 'input', 'name', 'celular').value, ['(', ')', '-', ' ', '_', '_', '_', ' '], ''))){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um Celular Válido.', 800);
		elemento.celular.value = '';
		focusInputTextarea(elemento.celular);
	}else if(elemento.avrua.value == ""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe seu Endereço.', 800);
		focusInputTextarea(elemento.avrua);
	}else if(elemento.avrua.value.length < 2){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um Endereço Válido.', 800);
		focusInputTextarea(elemento.avrua);
	}else if(elemento.numero.value != "" && !validaNumero(elemento.numero.value)){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um Número Válido.', 800);
		elemento.numero.value = '';
		focusInputTextarea(elemento.numero);
	}else if(elemento.bairro.value == ""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe seu Bairro.', 800);
		focusInputTextarea(elemento.bairro);
	}else if(elemento.bairro.value.length < 2){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe um Bairro Válido.', 800);
		focusInputTextarea(elemento.bairro);
	}else if(!validaSelectBox(elemento.slcpaises)){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Selecione seu País.', 800);
	}else if(!validaSelectBox(elemento.slcestados)){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Selecione seu Estado.', 800);
	}else if(!validaSelectBox(elemento.slccidades)){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Selecione sua Cidade.', 800);
	}else if(elemento.cep.value == ""){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Informe seu CEP.', 800);
		focusInputTextarea(elemento.cep);
	}else if(!validaCheckBox(elemento.termosdeuso)){
		scrollTopComVelocidade('html, body', idAviso, 1000);
		avisoHideFadeIn('#'+idAviso, '<div class="warning">', '</div>', 'Aceite os Termos de Uso para continuar.', 800);
	}
	//else if (emailCadastrado(elemento.email.value)){
	//elemento = buscaElementoPorTagAtributoEValorAtributo(documento, tagElemento, atributoTag, valorAtributoTag);
	//	avisoHideFadeIn(idAviso, '<div class="warning">', '</div>', 'Informe um E-mail Válido.', 800);
	//	elemento.email.focus();
	//}
	else{
		enviaFormAjax(elemento, idAviso, '<div class="warning">', '</div>', 'Sua mensagem foi enviada e em breve será respondida.', 'Sua mensagem não foi enviada. Tente novamente.');
	}
	//elemento.datanasc.value = substituirCaracteresEmString(elemento.datanasc.value, ['\/', '-', '-', ' ', '  ', '_', '/', '\\'], '');
	
	//alert(elemento.datanasc.value);
	//data = new Date(2012, 5, 30);
	
	//alert(dataValida(data, '30', '6', '2012'));
	//alert(data.toString());
	//alert(data.getFullYear());
	//alert(data.getFullYear());
}

//Função que Valida Data String
function stringDataValida(stringData){
	data = new Date(stringData.substring(4, 8), (stringData.substring(2, 4)-1), stringData.substring(0, 2));
	if(stringData.length < 8 || stringData.length >8){
		return false;
	}else if(data.getDate() == stringData.substring(0, 2) && (data.getMonth()+1) == stringData.substring(2, 4) && data.getFullYear() == stringData.substring(4, 8)){
		return true;
	}else{
		return false;
	}
}

//Função que Valida CPF
function cpfValida(cpf){
	s = cpf; 

	if(s=="11111111111" || s=="22222222222" || s=="33333333333" || s=="44444444444" || s=="55555555555" || s=="66666666666" || s=="77777777777" || s=="88888888888" || s=="99999999999"){
		return false; 
	}

	var c = s.substr(0,9); 
	var dv = s.substr(9,2); 
	var d1 = 0; 
	
	for (var i=0; i < 9; i++){ 
		d1 += c.charAt(i)*(10-i); 
	} 
	
	if (d1 == 0){ 
		return false; 
	} 
	
	d1 = 11 - (d1 % 11); 
	if (d1 > 9) d1 = 0; 
	if (dv.charAt(0) != d1){ 
		return false; 
	} 
	d1 *= 2; 
	for (var i=0; i < 9; i++) { 
		d1 += c.charAt(i)*(11-i); 
	} 
	d1 = 11 - (d1 % 11); 
	if (d1 > 9) d1 = 0; 
	if (dv.charAt(1) != d1){ 
		return false; 
	} 
	return true;
}

//Função principal que Valida Email
function validaEmail(email){
	x = email.value;
	atpos = x.indexOf("@");
	dotpos = x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+3>=x.length){
	  return false;
	}
	// ver a quantidade de @
	else if((email.value).split("@").length >2){
		return false;
	}// ver o ultima validação de email
	else if(!IsEmail(email.value)){
		return false;
	}else{
		return true;
	}
}

//Função que Valida Número de Celular
function IsEmail(email){
    var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
    var check=/@[\w\-]+\./;
    var checkend=/\.[a-zA-Z]{2,3}$/;
    if(((email.search(exclude) != -1)||(email.search(check)) == -1)||(email.search(checkend) == -1)){
		return false;
	} else {
		return true;
	}
}

//Função que Valida Número de Celular de Operadoras do Brasil
function validaCelularBrasil(num){
	if(num.length >= 8 && num.length < 9){
		if(checaPrefixoOpCel(num.substring(0, 1))){ // 8 dígitos (sem DDD)
			return true;
		}
	}else if(num.length >= 9 && num.length < 10){
		if(num.substring(0, 1) == '9' && checaPrefixoOpCel(num.substring(2, 3))){ // 9 dígitos (sem DDD)
			return true;
		}
	}else if(num.length >= 10 && num.length < 11){
		if(checaDDDBrasil(num.substring(0, 2)) && checaPrefixoOpCel(num.substring(2, 3))){ //10 dígitos (sem 9 e com DDD)
			return true;
		}
	}else if(num.length > 10 && num.length <= 11){
		if(checaDDDBrasil(num.substring(0, 2)) && num.substring(2, 3) == '9' && checaPrefixoOpCel(num.substring(3, 4))){ //11 dígitos (com 9 e com DDD)
			return true;
		}
	}else{
		return false; // menor que 8 ou maior que 11
	}
	return false; // Entrou em algum if ou elseif , porém não satisfez o if
}

//Função que Valida Número de Telefone Fixo de Operadoras do Brasil
function validaTelefoneFixoBrasil(num){ // Telefones Fixo Não tem o 9º Dígito
	if(num.length >= 8 && num.length < 9){
		if(checaPrefixoOpTelFixo(num.substring(0, 1))){ // 8 dígitos (sem DDD)
			return true;
		}
	}else if(num.length >= 10 && num.length < 11){
		if(checaDDDBrasil(num.substring(0, 2)) && checaPrefixoOpTelFixo(num.substring(2, 3))){ //10 dígitos (com DDD)
			return true;
		}
	}else{
		return false; // menor que 8 ou maior que 10
	}
	return false; // Entrou em algum if ou elseif , porém não satisfez o if
}

//Função que Valida Prefixo de Operadoras de Celular do Brasil
function checaPrefixoOpCel(num){
	listaPrefixosCel = ['6', '7', '8', '9']; // Valores de Números Reservas: 0 => true, 1 => true,
	for(var i=0; i<listaPrefixosCel.length; i++){
		if(listaPrefixosCel[i] == num){
			return true;
		}
	}
	return false;
}

//Função que Valida Prefixo de Operadoras de Telefone Fixo do Brasil
function checaPrefixoOpTelFixo(num){
	listaPrefixosTelFixo = ['2', '3', '4', '5']; // Telefones Fixo Não tem o 9º Dígito
	for(var i=0; i<listaPrefixosTelFixo.length; i++){
		if(listaPrefixosTelFixo[i] == num){
			return true;
		}
	}
	return false;
}

//Função que Valida DDDs do Brasil
function checaDDDBrasil(ddd){
	listaDDDs = ['11', '12', '13', '14', '15', '16', '17', '18', '19', '21', '22', '24', '27', '28', '91', '92', '93', '94', '95', '96', '97', '98', '99', '31',
				'32', '33', '34', '35', '37', '38', '71', '73', '74', '75', '77', '79', '81', '82', '83', '84', '85', '86', '87', '88', '89', '41', '42', '43',
				'44', '45', '46', '47', '48', '49', '51', '53', '54', '55', '61', '62', '63', '64', '65', '66', '67', '68', '69'];
	for(var i=0; i<listaDDDs.length; i++){
		if(listaDDDs[i] == ddd){
			return true;
		}
	}
	return false;
}

//Função alternativa que Valida Email
function IsEmail(email){
    var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
    var check=/@[\w\-]+\./;
    var checkend=/\.[a-zA-Z]{2,3}$/;
    if(((email.search(exclude) != -1)||(email.search(check)) == -1)||(email.search(checkend) == -1)){
		return false;
	} else {
		return true;
	}
}

//Função que Valida se uma String é Número
function validaNumero(num){
	ValidChars = "0123456789";
	for (i = 0; i < num.length; i++){
		if (ValidChars.indexOf(num.charAt(i)) == -1){
			return false;
		}
	}
	return true; 
}

//Função que Valida Input Type Radio
function validaInputRadio(elementoInputRadio){
	for(var i=0; i<elementoInputRadio.length; i++){
		if(elementoInputRadio[i].checked){
			return true;
		}
	}
	return false;
}

//Função que Valida Select Box
function validaSelectBox(elementoSelectBox){
	if (elementoSelectBox.value){
		return true;
	}
	return false;
}

//Função que Valida Check Box
function validaCheckBox(elementoCheckBox){
	if(elementoCheckBox.checked){
		return true;
	}
	return false;
}


// ################################
// ###### Funções de Postagem #####
// ################################

function enviaFormAjax(elemento, localTagHTMLAviso, HtmlInicioAviso, HtmlFinalAviso, MsgOk, MsgErro) {
	var res;
	$.ajax({
		type: $(elemento).attr('method'),
		url: $(elemento).attr('action'),
		data: $(elemento).serialize(),
		success: function (resultado) {
			if(resultado == 'Ok'){
				res = MsgOk;
				limparFormulario(elemento);
				limitaTextarea(buscaElementoPorTagAtributoEValorAtributo(document, 'textarea', 'name', 'mensagem'), 'contcaracter', 1000);
				
			}else{
				res = MsgErro;
			}
			avisoHideFadeInFadeOut(localTagHTMLAviso, HtmlInicioAviso, HtmlFinalAviso, res, 800, 3000, 15000);
        },
        error: function(jqXHR, exception) {
            if (jqXHR.status === 0) {
				res = res + 'Sem Conexão. Verifique sua conexão com a Internet.';
            } else if (jqXHR.status == 404) {
				res = res + 'Página não encontrada. Erro 404.';
            } else if (jqXHR.status == 500) {
				res = res + 'Erro interno de Servidor. Erro 500.';
            } else if (exception === 'parsererror') {
				res = res + 'Falha de requisição JSON.';
            } else if (exception === 'timeout') {
				res = res + 'Tempo Esgotado.';
            } else if (exception === 'abort') {
				res = res + 'Requisição Ajax abortada.';
            } else {
				res = res + 'Erro desconhecido. '+ jqXHR.responseText;
            }
			avisoHideFadeInFadeOut(localTagHTMLAviso, HtmlInicioAviso, HtmlFinalAviso, res, 800, 3000, 15000);
		}
	});
}

//Função Carrega Lista de Estados e Cidades ao Carregar a Página
function carregaPaisEstados(documento, VetorTagElemento, VetorAtributoTag, VetorValorAtributoTag, url){
	pais = buscaElementoPorTagAtributoEValorAtributo(documento, VetorTagElemento[0], VetorAtributoTag[0], VetorValorAtributoTag[0]);
	if(pais.value != ''){		
		atualizaElementoPorParametroURL(documento, VetorTagElemento[1], VetorAtributoTag[1], VetorValorAtributoTag[1], pais.value, url);
	}
}

// ################################
// #### Funções de Recebimento ####
// ################################

//Verificar se o Dado já esta cadastrado no Banco de Dados
function buscaElementoNoBDPorParametroURL(elemento, parametro, url){
	if(parametro){
		$.get(url+parametro, function(dataReturn) {
			if(dataReturn == 'SIM'){
				return true;
			}else{
				return false;
			}
		});
    }
}

//Atualizar HTML Interno de Elemento
function atualizaElementoPorParametroURL(documento, tagElemento, atributoTag, valorAtributoTag, parametro, url){
    elemento = buscaElementoPorTagAtributoEValorAtributo(documento, tagElemento, atributoTag, valorAtributoTag);
	if(parametro){
		$.get(url+parametro, function(dataReturn) {
			$('#'+elemento.getAttribute('id')).html(dataReturn);
		});
    }
}

//Atualizar Valor do Elemento
function atualizaValorElementoPorParametroURL(documento, tagElemento, atributoTag, valorAtributoTag, parametro, url){
    elemento = buscaElementoPorTagAtributoEValorAtributo(documento, tagElemento, atributoTag, valorAtributoTag);
	if(parametro){
		$.get(url+parametro, function(dataReturn) {
			$(elemento).val(dataReturn);
		});
    }
}


// ################################
// ####### Funções de Avisos ######
// ################################

//Função exibe Aviso com Hide e FadeIn
function avisoHideFadeIn(localTagHTMLAviso, HtmlInicioAviso, HtmlFinalAviso, Msg, tempoFadeIn){
	$(localTagHTMLAviso).html(HtmlInicioAviso + Msg + HtmlFinalAviso).hide();
	$(localTagHTMLAviso).fadeIn(tempoFadeIn);
}

//Função exibe Aviso com Hide e FadeOut
function avisoHideFadeOut(localTagHTMLAviso, HtmlInicioAviso, HtmlFinalAviso, Msg, tempoFadeOut, tempoWait){
	$(localTagHTMLAviso).delay(tempoWait).fadeTo(tempoFadeOut);
	$(localTagHTMLAviso).slideUp(2000);
}

//Função exibe Aviso com Hide, FadeIn e FadeOut
function avisoHideFadeInFadeOut(localTagHTMLAviso, HtmlInicioAviso, HtmlFinalAviso, Msg, tempoFadeIn, tempoFadeOut, tempoWait){
	$(localTagHTMLAviso).html(HtmlInicioAviso + Msg + HtmlFinalAviso).hide();
	$(localTagHTMLAviso).fadeIn(tempoFadeIn);
	$(localTagHTMLAviso).delay(tempoWait).fadeTo(tempoFadeOut);
	$(localTagHTMLAviso).slideUp(2000);
}

//Função faz ScrollTop com velocidade definida para Avisos
function scrollTopComVelocidade(tagHTMLReferencia, localTagHTMLAviso, tempoScroll){	
	$(tagHTMLReferencia).animate({scrollTop:$('#'+localTagHTMLAviso).position().top/2+$('#'+localTagHTMLAviso).position().top/2.4}, tempoScroll);
}


// ################################
// ###### Funções de Máscara ######
// ################################

//Função que Limita Textarea
function limitaTextarea(elemento, idContador, limiteCarac) {
	quant = limiteCarac; /* Total de caracteres */
	total = elemento.value.length;

	if(total <= quant) {
		resto = quant - total;
		document.getElementById(idContador).innerHTML = resto;
	} else {
		elemento.value = elemento.value.substr(0,quant);
	}
}

//Função Mascara de Vetor de Elementos Input Tipo Tel
function mascaraElementosTypeTel(documento, tagElemento, atributoElemento, tipoElemento){
	listaElementos = listaElementosPorTag(documento, tagElemento);
	for(var i=0; i<listaElementos.length-1; i++){
		if(listaElementos[i].getAttribute(atributoElemento) === tipoElemento){
			mascaraTelefone(listaElementos[i]);
		}
	}
}

//Função Mascara de Telefone
function mascaraTelefone(elemento){
	elemento.value = elemento.value.replace('_', '');
	if(elemento.value.length <= 14){
		jQuery(elemento).mask("(99) 9999-9999?9");
	}else{
		jQuery(elemento).mask("(99) 99999-999?9");
	}
}

//Função Mascara de CPF
function mascaraCPF(elemento){
	jQuery(elemento).mask("999.999.999-99");
}

//Função Mascara de Data de Nascimento
function mascaraDataNasc(elemento){
	jQuery(elemento).mask("99/99/9999");
}

//Função Mascara de CEP
function mascaraCEP(elemento){
	jQuery(elemento).mask("99999-999");
}

// ################################
// ### Funções de Elementos DOM ###
// ################################

//Função Lista de Elementos por Tag
function listaElementosPorTag(documento, tagElemento){
	return documento.getElementsByTagName(tagElemento);
}

//Função Lista de Elementos por Nome
function listaElementosPorNome(documento, nomeElemento){
	return documento.getElementsByName(nomeElemento);
}

//Função Busca de Elemento por Tag, pelo Atributo e o Valor do Atributo
function buscaElementoPorTagAtributoEValorAtributo(documento, tagElemento, atributoTag, valorAtributoTag){
	listaElementos = document.getElementsByTagName(tagElemento);
	for(var i=0; i<listaElementos.length; i++){
		if(listaElementos[i].getAttribute(atributoTag) == valorAtributoTag){
			return listaElementos[i];
		}
	}
	return null;
}



//Função Altera Value Elemento
function alteraValueElemento(elemento, valor){
	elemento.value = valor;
}

//Função Limpa Formulários
function limparFormulario(elemento){
	limparInputs(elemento, ['input'], ['type'], ['text', 'tel', 'hidden', 'password']);
	limparTextareas(elemento);
}

//Função Limpa Inputs
function limparInputs(elemento, vetorTagsElementos, vetorAtributosInput, vetorTiposAtributosInput){
	for(var i=0; i<vetorTagsElementos.length; i++){
		listaElementos = listaElementosPorTag(elemento, vetorTagsElementos[i]);
		for(var j=0; j<listaElementos.length; j++){
			for(var k=0; k<vetorAtributosInput.length; k++){
				for(var l=0; l<vetorTiposAtributosInput.length; l++){
					if((listaElementos[j] != null) && listaElementos[j].getAttribute(vetorAtributosInput[k]) == vetorTiposAtributosInput[l]){
						listaElementos[j].value = '';
					}
				}
			}
		}
	}
}

//Função Limpa Textareas
function limparTextareas(elemento){
	listaElementos = listaElementosPorTag(elemento, 'textarea');
	for(var i=0; i<listaElementos.length; i++){
		listaElementos[i].value = '';
	}
}

//Função que Foca em Elementos Input e Textarea
function focusInputTextarea(elemento){
	elemento.focus();
}

//Função que Foca em Elementos Input e Textarea
function substituirCaracteresEmString(string, arrayCaracteresParaTrocar, caracterSubstituto){
	for(var i=0; i<arrayCaracteresParaTrocar.length; i++){
		string = string.replace(arrayCaracteresParaTrocar[i], caracterSubstituto);
	}
	return string;
}

//Função que Divide String em Array de String a partir de um Caracter
function dividirString(string, caracterDivisor){
	return string.split(caracterDivisor);
}

function teste(){
	//alert(jQuery('input[type=tel]').length);
	//limparFormulario(document.enviarmensagem);
	//alteraValueElemento(document.enviarmensagem.telefone, '(67) 97941-5646');
	//alert(buscaElementoPorTagAtributoEValorAtributo(document, 'textarea', 'name', 'mensagem').getAttribute('name'));
	//alert(buscaElementoPorTagAtributoEValorAtributo(document, 'span', 'id', 'contcaracter').getAttribute('id'));
	//alert(buscaElementoPorTagAtributoEValorAtributo(document, 'div', 'id', 'estado').getAttribute('id'));
	//num = '6732914691';
	//alert(num.substring(2, 3)); //9º digito de 11 num
	//alert(num.substring(0, 1)); //1º dígito
	//alert(num.substring(3, 4)); //4º digito de 11 num
	//alert(num.substring(2, 4)); //3º e 4º digito de 10 num
	//alert(num.substring(2, 3)); //3º digito de 9 num
	//alert(num.substring(0, 1)); //3º digito de 9 num
	//alert(validaCelularBrasil(num));
	//alert(validaTelefoneFixoBrasil(num));
	//alert(validaInputRadio(listaElementosPorNome(document, 'sexo')));
	//alert(substituirCaracteresEmString(buscaElementoPorTagAtributoEValorAtributo(document, 'input', 'name', 'telefone').value, ['(', ')', '-', ' ', '_', '_', '_', ' '], ''));
	//alert(dividirString('Vagner da Silva', " ").length);
	//alert(validaCheckBox(buscaElementoPorTagAtributoEValorAtributo(document, 'input', 'name', 'termosdeuso')));
	
}

/*
 *  Javascript makeslug()
 *    by J. Santos <jefrey[at]jefrey[dot]ml>
 */
 
/*
  Usage:
    string makeslug( string val [, string replaceBy = "-" ] )

  Example:
    makeslug("   This is a string fuLL of Ã¡ccÃªntÃ¨d and InVaLid (?) cHaRs!!!   ");
  Will return:
    this-is-a-string-full-of-accented-and-invalid-chars
  
  And:
    makeslug("   This is a string fuLL of Ã¡ccÃªntÃ¨d and InVaLid (?) cHaRs!!!   ", "_");
  Will return:
    this_is_a_string_full_of_accented_and_invalid_chars
*/


function makeslug(val, replaceBy) {

  replaceBy = replaceBy || '-';
  var mapaAcentosHex 	= { // by @marioluan and @lelotnk
  	a : /[\xE0-\xE6]/g,
  	A : /[\xC0-\xC6]/g,
  	e : /[\xE8-\xEB]/g, // if you're gonna echo this
  	E : /[\xC8-\xCB]/g, // JS code through PHP, do
  	i : /[\xEC-\xEF]/g, // not forget to escape these
  	I : /[\xCC-\xCF]/g, // backslashes (\), by repeating
  	o : /[\xF2-\xF6]/g, // them (\\)
  	O : /[\xD2-\xD6]/g,
  	u : /[\xF9-\xFC]/g,
  	U : /[\xD9-\xDC]/g,
  	c : /\xE7/g,
  	C : /\xC7/g,
  	n : /\xF1/g,
  	N : /\xD1/g,
  };
  
  for ( var letra in mapaAcentosHex ) {
  	var expressaoRegular = mapaAcentosHex[letra];
  	val = val.replace( expressaoRegular, letra );
  }
  
  val = val.toLowerCase();
  val = val.replace(/[^a-z0-9\-]/g, " ");
  
  do {
  	val = val.replace('  ', ' ');
  } while (val.indexOf('  ')>-1);
    
  val = val.trim();    
  val = val.replace(/\s/g, replaceBy);
  
  return val;
}

function criarCodigo(elemento, elementoCodigo){
	elementoCodigo = listaElementosPorTag(document, 'input');
	for(var i=0; i<elementoCodigo.length; i++){
		if(elementoCodigo[i].id.search("codigo") > 0){
			elementoCodigo = elementoCodigo[i];
			i = elementoCodigo.length;
		}
	}
	valor = elemento.value;
	if(valor){
		valor = makeslug(valor);
	}
	valor = valor.toUpperCase();
	elementoCodigo.value = valor;
}