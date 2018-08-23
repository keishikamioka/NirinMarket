var 	Cnt 			= jQuery('#ContactInput'), //入力部分
			Cfm 			= jQuery('#ContactConfirm'), //確認部分
			BtCfm 		= '#BtConfirm', //確認ボタン
			BtSnd 		= jQuery('#BtSend input'),//送信ボタン
			BtCsl 		= '#BtCancel',//キャンセルボタン
			CntItem 	= jQuery('#ContactInput .CntItem'),//入力の包容タグ
			CfmItem 	= jQuery('#ContactConfirm .CfmItem div');//確認の包容タグ
/* 確認ボタン
------------------------------------------------------------------*/
$(document).on('click', BtCfm, function(){//確認ボタンを押したとき、
	alert("aaa");
	CfmItem.text(' '); //確認の中身をリセット
	$Error = ''; //エラーのリセット

	CntItem.ConfirmInput(); //.CntItemを対象として確認の関数を実行
	if($Error != 'error') { //エラーがない場合
		jQuery('#ContactInput').fadeOut();
		//(function(){Cfm.fadeIn();}); //入力部分と確認部分を切り替え
		jQuery('#ContactConfirm').fadeIn();//入力部分と確認部分を切り替え
		window.scrollTo(0,50);


	}
});
/* キャンセルボタン
------------------------------------------------------------------*/
$(document).on('click', BtCsl, function(){//修正ボタンを押したとき、
	jQuery('#ContactInput').fadeIn();
	jQuery('#ContactConfirm').fadeOut();
});


jQuery(document).on('change', 'input[type=file]', function () {
    var file = this.files[0];

    if (!file.type.match(/^image\/(bmp|png|jpeg|gif)$/)){
        alert("写真ファイルは拡張子 bmp png jpeg gif から選択してください。");
//        var obj = document.getElementById("checkimg");
//        file.valueOf = "";
        return;
    }
});
/* 確認画面へ反映させる関数
------------------------------------------------------------------*/
jQuery.fn.ConfirmInput = function(config){

    return jQuery('#ContactInput .CntItem').each(function(index, Element) { //.CntItemを一つずつ実行
    	/*
			入力されたものを変数「CntVal」に代入
			------------------------------------------------------------*/
			//<input type="text">の場合
			if($(this).hasClass('CntInput')){
				TargetForm = $(this).find('input');
				CntVal = TargetForm.val();


			//<select>の場合
			}
			else if($(this).hasClass('CntSelect')){
				TargetForm = $(this).find('option:selected');
				CntVal = TargetForm.val();

			//<textarea>の場合
			}else if($(this).hasClass('CntText')){
				TargetForm =$(this).find('textarea');
				CntVal2 = TargetForm.val();
				CntVal3 = CntVal2.replace(/\r?\n/g, '<br>');
				document.getElementById("Cfm10").innerHTML = CntVal3;
			}
			/*
			入力部分のIDから関連する確認部分のIDへ「CntVal」を挿入
			------------------------------------------------------------*/
			CntNum = $(this).attr('id').replace(/Cnt/g,''); //IDを取得後「Cnt」をカット

			if (CntNum == 6){
				$('#Cfm'+CntNum+' div').text(CntVal+"年");
			}
			else if (CntNum == 7){
				var CntVal2 = CntVal.replace(/(\d)(?=(\d{3})+$)/g , '$1,');
				$('#Cfm'+CntNum+' div').text(CntVal2 +"Km");
			}
			else if (CntNum == 9){
				var CntVal2 = CntVal.replace(/(\d)(?=(\d{3})+$)/g , '$1,');
				$('#Cfm'+CntNum+' div').text(CntVal2+"円");
			}else{
			$('#Cfm'+CntNum+' div').text(CntVal);
			//$('#Cfm'+CntNum+' div').html(CntVal3);
			}
			/*
			ユーザネーム欄の場合(.CntUserNameの場合)、形式をチェック
			------------------------------------------------------------*/
			if($(this).hasClass('CntUserName') && !CntVal.match(/^[a-zA-Z0-9]+$/)){
				alert('ユーザ名に特殊文字(例: ! > & \" { )は使用できません。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}
			else if($(this).hasClass('CntUserName') && !CntVal.match(/^([a-zA-Z0-9]{4,8})$/)){
				alert('ユーザ名は4文字以上8文字以下で入力してください。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}


			/*
			メールアドレスの欄の場合(.CntMailの場合)、形式をチェック
			------------------------------------------------------------*/
			else if($(this).hasClass('CntMail') && !CntVal.match(/^([a-zA-Z0-9])+([a-zA-Z0-9._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9._-]+)+$/)){
				alert('メールアドレスの形式が正しくありません。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}

			/*
			パスワード欄の場合(.CntPasswordの場合)、形式をチェック
			------------------------------------------------------------*/
			else if($(this).hasClass('CntPassword') && !CntVal.match(/^[a-zA-Z0-9]+$/)){
				alert('パスワードに特殊文字(例: ! > & \" { )は使用できません。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}
			else if($(this).hasClass('CntPassword') && !CntVal.match(/^([a-zA-Z0-9]{4,8})$/)){
				alert('パスワードは4文字以上8文字以下で入力してください。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}

			/*
			写真の場合、形式をチェック
			------------------------------------------------------------*/
			else if(!(document.getElementById("checkimg").files[0])){
				alert('写真を一枚以上選択してください。');
				$Error = 'error';
				return false;
			}
			else if(!document.getElementById("checkimg").files[0].type.match(/^image\/(bmp|png|jpeg|gif)$/)){
				alert('写真ファイルは拡張子 bmp png jpeg gif から選択してください。')
				$Error = 'error';
				return false;
			}
			/*
			走行距離欄の場合(.CntDisの場合)、形式をチェック
			------------------------------------------------------------*/
			else if($(this).hasClass('CntDis') && !CntVal.match(/^\d+$/)){
				alert('走行距離は半角数字で入力してください。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}

			/*
			価格欄の場合(.CntPriceの場合)、形式をチェック
			------------------------------------------------------------*/
			else if($(this).hasClass('CntPrice') && !CntVal.match(/^\d+$/)){
				alert('価格は半角数字で入力してください。');
				$Error = 'error'; //$Errorに中身を入れる
				return false;
			}

			/*
			必須(.Require)に中身が入っていない場合
			------------------------------------------------------------*/
			else if($(this).hasClass('Require') && CntVal==''){
				alert('必須項目が入力されていません。');
				$Error = 'error';
				return false;
			}
});
}
//main画像クリック
$(document).on('click', '#mainimg', function(){//確認ボタンを押したとき、

    // ③暗幕表示
    $('#back-curtain')
        .css({
            'width' : $(window).width(),    // ウィンドウ幅
            'height': $(window).height()    // 同 高さ
        })
        .show();

    $('#largeImg')
        .css({
            'position': 'absolute',
            'left'    : Math.floor(($(window).width() - 800) / 2) + 'px',
            'top'     : $(window).scrollTop() + 30 + 'px'
        })
        .fadeIn();
});
$(document).on('click', '#back-curtain, #largeImg',function() {
	$('#largeImg').fadeOut('slow', function() {$('#cover').hide();
	});
	$('#back-curtain').fadeOut('slow', function() {$('#cover').hide();
	});
});

//問い合わせform送信
$(document).ready(function() {
	  $('#submitForm').submit(function(e) {
	    alert($('#UserName').val());
	    return e.preventDefault();
	  });
	});
///* 確認ボタン
//------------------------------------------------------------------*/
//BtCfm.click(function(){//確認ボタンを押したとき、
//	alert("aaa");
//	CfmItem.text(' '); //確認の中身をリセット
//	$Error = ''; //エラーのリセット
//
//	CntItem.ConfirmInput(); //.CntItemを対象として確認の関数を実行
//	if($Error != 'error') { //エラーがない場合
//		Cnt.fadeOut(function(){Cfm.fadeIn();}); //入力部分と確認部分を切り替え
//	}
//});
///* キャンセルボタン
//------------------------------------------------------------------*/
//BtCsl.click(function(){ //キャンセルボタンを押したとき、
//	Cfm.fadeOut(function(){Cnt.fadeIn();}); //入力部分と確認部分を切り替え
//});
///* 確認画面へ反映させる関数
//------------------------------------------------------------------*/
//jQuery.fn.ConfirmInput = function(config){
//    return this.each(function(i, elem) { //.CntItemを一つずつ実行
//			/*
//			入力されたものを変数「CntVal」に代入
//			------------------------------------------------------------*/
//			//<input type="text">の場合
//			if($(this).hasClass('CntInput')){
//				TargetForm = $(this).find('input');
//				CntVal = TargetForm.val();
//			//<input type="radio">の場合
//			}else if($(this).hasClass('CntRadio')){
//				TargetForm = $(this).find('input:checked');
//				CntVal = TargetForm.val();
//			//<input type="checkbox">の場合
//			}else if($(this).hasClass('CntCheck')){
//				TargetForm = $(this).find('input:checked');
//				CntCheck = '';
//				for (var i=0; i<TargetForm.length; i++) {
//					if(i != 0){ CntCheck += '、'; }
//					CntCheck += '「'+TargetForm.eq(i).val()+'」';
//				}
//				CntVal = CntCheck;
//			//<textarea>の場合
//			}else if($(this).hasClass('CntText')){
//				TargetForm = $(this).find('textarea');
//				CntVal = TargetForm.val();
//			}
//			/*
//			入力部分のIDから関連する確認部分のIDへ「CntVal」を挿入
//			------------------------------------------------------------*/
//			CntNum = $(this).attr('id').replace(/Cnt/g,''); //IDを取得後「Cnt」をカット
//			$('#Cfm'+CntNum+' div').text(CntVal);
//			/*
//			メールアドレスの欄の場合(.CntMailの場合)、形式をチェック
//			------------------------------------------------------------*/
//			if($(this).hasClass('CntMail') && !CntVal.match(/^([a-zA-Z0-9])+([a-zA-Z0-9._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9._-]+)+$/)){
//				alert('メールアドレスの形式が正しくありません。');
//				$Error = 'error'; //$Errorに中身を入れる
//				return false;
//			}
//			/*
//			必須(.Require)に中身が入っていない場合
//			------------------------------------------------------------*/
//			else if($(this).hasClass('Require') && CntVal==''){
//				alert('必須項目が入力されていません。');
//				$Error = 'error';
//				return false;
//			}
//});
//}
