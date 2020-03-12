var Financeiro = Financeiro || {};

Financeiro.PesquisaRapidaCidade = (function() {
	
	function PesquisaRapidaCidade() {
		this.nomeInput = $('#nomeCidadeModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-cidades-btn'); 
		this.mensagemErro = $('#mensagem-erro');
		this.showMostraTabelaCidade = $('#tabela_cidade');
		this.showMostraTabelaCidade.hide();
		this.mensagemErro.hide();
	}
	
	PesquisaRapidaCidade.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		$('#pesquisarCidade').on('shown.bs.modal', onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		$.ajax({
			url: '/rest/cidade/buscarCidade',
			method: 'GET',
			contentType: 'application/json',
			dataType: 'json',
			data: {
				nome: this.nomeInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.hide();
		this.showMostraTabelaCidade.show();
		var tabelaCidade = $('#tabelaCidade').DataTable({
		
			select : true,
			destroy: true,
			data : resultado,
			lengthMenu : [ 5, 10, 25 ],
			searching : true,
     		paging : true,
			aaSorting : [ [ 1, 'desc' ],[ 2, 'asc' ] ], // Optional
			responsive : true,
			bAutoWidth : true,
			orderable : true,
			pagingType : "full_numbers",
			order : [ [ 1, 'asc' ] ],
			columns : [{"data":"id"},{"data":"nome"},{"data":"estado"},{"data":"sigla"},
				       {"data":"ddd"},{"data":"ibge"},{"data":"idestado"},],
			
			"language": {
				"sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por página",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Próximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Último"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    }
	        }
			
			
		});
		$('#tabelaCidade').on('click', 'tr', function() {
		    var cidade = tabelaCidade.row(this).data();
		    $('#nomeCidade').val(cidade.nome);
		    $('#nomeEstado').val(cidade.estado);
			$('#codigoCidade').val(cidade.id);
			$('#sigla').val(cidade.sigla);
			$('#codigoEstado').val(cidade.idestado);
			$('#nomeCidadeModal').val('');
			$('#pesquisarCidade').modal('hide');
			tabelaCidade.clear();
		});

	} 
	
	function onErroPesquisa() {
		this.mensagemErro.show();
	}
	
	return PesquisaRapidaCidade;
	
}());

$(function() {
	var pesquisaRapidaCidade = new Financeiro.PesquisaRapidaCidade();
	pesquisaRapidaCidade.iniciar();
});