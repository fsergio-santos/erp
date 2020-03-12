/**
 * 
 */

$(function(){
	$('.js-data').mask('00/00/0000');
	$('.js-data').datepicker({
		orientation: 'bottom',
		language: 'pt-BR',
		autoclose: true,
		todayHighlight: true
	});
});
