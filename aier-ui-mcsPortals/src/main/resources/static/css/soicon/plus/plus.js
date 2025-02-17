var plusHtml =
//'<link rel="stylesheet" href="plus/plus.css">'+
'<div class="searchhead soform">'+
'<div class="main">'+
	'<div class="item-one item-group item-search"><label class="lab-item">过滤图标：</label><input class="txt" id="txt-search" type="text" name="keyword" value="" placeholder="输入字母过滤图标" />'+
	'<label class="lab-total">共<b id="b-total"></b>个图标</label></div>'+
	'<div class="item-one">'+
		'<label class="lab-item">图标大小：</label>'+
		'<label class="lab-val"><input class="rad rad-size" type="radio" value="24" name="iconsize" />24px</label>'+
		'<label class="lab-val"><input class="rad rad-size" type="radio" value="30" name="iconsize" checked="checked" />30px</label>'+
		'<label class="lab-val"><input class="rad rad-size" type="radio" value="50" name="iconsize" />50px</label>'+

		'<label class="mar-l40">屏宽：</label>'+
		'<label class="lab-val"><input class="rad rad-resize" type="radio" value="960px" name="resize" />960px</label>'+
		'<label class="lab-val"><input class="rad rad-resize" type="radio" value="1200px" checked="checked" name="resize" />1200px</label>'+
		'<label class="lab-val"><input class="rad rad-resize" type="radio" value="auto" name="resize" />宽屏</label>'+


		'<label class="lab-top-op"><a id="a-toRef" class="a-ref" href="#font-class-">引用说明</a>　<a class="a-ref" href="#">顶部</a></label>'+
	'</div>'+
	'</div>'+


'</div>';

function rangeTxt(el){
	var range = document.createRange();
	referenceNode = $(el).get(0);
	range.selectNodeContents(referenceNode);
	var selection = window.getSelection();
	selection.removeAllRanges();
	selection.addRange(range)
}

$(function () {
	$('body').prepend(plusHtml);

	setTimeout(()=>{
		$('#tabs .dib').eq(1).click();
	},200);


	$('.dib').click(function (){//选中文本
		let $name = $(this).find('.code-name');
		if($name.length){
			rangeTxt($name);
		}
	});

	$('.code-name').click(function() {//选中文本
		rangeTxt(this);
  });

	var aimIds = ['#unicode-','#font-class-','#symbol-'];

	$('#tabs .dib').bind('click',function (){
		var ix =  $('#tabs .dib').index(this);
		$('#a-toRef').attr('href',aimIds[ix]);
	});

	$('#b-total').html($('.font-class .dib').length);
	$('#txt-search').keyup(function(e) {
	  var _self = $(this);
	  var val = $.trim(_self.val());
	  if(val){
		$('.dib').addClass('none');
		var $cons = $('.dib:contains("'+val+'")');
		$cons.removeClass('none');
//		$('#b-total').html($('.font-class .dib:contains("'+val+'")').length);
	  }else{
		$('.dib').removeClass('none');
//		$('#b-total').html($('.font-class .dib').length);
	  }
	});

	$('.rad-size').click(function() {
//	  window.console&&console.log($(this).val());
		var v = $(this).val();
		// $('.icon_lists li').css({width:2*v+'px'});
		$('.icon_lists .icon').css({
			fontSize:v+'px',
			height:2*v+'px',
			lineHeight:2*v+'px'
		});
	});

	$('.rad-resize').click(function() {
//	  window.console&&console.log($(this).val());
		var v = $(this).val();
		$('.main').css({width:v});
	});

})
