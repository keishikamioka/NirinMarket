<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title id="title">リダイレクト</title>
<!-- <META http-equiv="Refresh" content="5;URL=bikeinfodetail(<%=request.getAttribute("baseno") %>)">> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="ここにサイト説明を入れます">
<meta name="keywords" content="キーワード１,キーワード２,キーワード３,キーワード４,キーワード５">
<link rel="stylesheet" href="css/jump.css">
<script type="text/javascript" src="js/ddmenu_min.js"></script>
<script type="text/javascript" src="js/styleswitcher.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="js/formconfirm.js"></script>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

</HEAD>



<BODY bgcolor="#C0C0C0" onload="setbikeinfo()">

<div class="curtain">

<p class="submitinfo">出品No：<span id="number"><%=request.getAttribute("baseno") %></span><br>問い合わせ情報を登録しました。</p>

<form name="submitForm" method="POST" action="bikeinfodetail">

<input type="hidden" name="Number" id="BaseNo" value="" />
<input type="submit" class="close square_btn" value="商品ページに戻る。"/>

</form>

<script>

function setbikeinfo(){
    var num = document.getElementById("number").textContent;
    document.getElementById("BaseNo").value = num;
}
</script>



</div>

</BODY>
</HTML>