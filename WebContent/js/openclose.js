/*
 * �u���b�N�J����
 * 2015/12/21 �{�^���摜�̔�\���������s��Ȃ�
 */
/*
 * Return the classList property of e, if it has one.
 * Otherwise, return an object that simulates the DOMTokenList API for e.
 * The returned object has contains(), add(), remove(), toggle() and toString()
 * methods for testing and altering the set of classes of the element e.
 * If the classList property is natively supported, the returned object is
 * array-like and has length and array index properties. The simulated
 * DOMTokenList is not array-like, but has a toArray() method that returns
 * a true-array snapshot of the element's class names.
 */

function classList(e) {
    if (e.classList) return e.classList;   // Return e.classList if it exists
    else return new CSSClassList(e);       // Otherwise try to fake it
}

// CSSClassList is a JavaScript class that simulates DOMTokenList
function CSSClassList(e) { this.e = e; }

// Return true if e.className contains the class c, false otherwise
CSSClassList.prototype.contains = function(c) {
    // Check that c is a valid class name
    if (c.length === 0 || c.indexOf(" ") != -1)
        throw new Error("Invalid class name: '" + c + "'");
    // Check common cases first
    var classes = this.e.className;
    if (!classes) return false;       // e has no classes at all
    if (classes === c) return true;   // e has one class that matches exactly

    // Otherwise, use a RegExp to search for c as a word by itself
    // \b in a regular expression requires a match at a word boundary.
    return classes.search("\\b" + c + "\\b") != -1;
};

// Add c to the e.className if it is not already present
CSSClassList.prototype.add = function(c) {
    if (this.contains(c)) return;            // Do nothing if already present
    var classes = this.e.className;
    if (classes && classes[classes.length-1] != " ")
        c = " " + c;                         // Add a space if we need one
    this.e.className += c;                   // Add c to the className
};

// Remove all occurrences of c from e.className
CSSClassList.prototype.remove = function(c) {
    // Make sure c is a valid class name
    if (c.length === 0 || c.indexOf(" ") != -1)
        throw new Error("Invalid class name: '" + c + "'");
    // Remove all occurances of c as a word, plus any trailing space
    var pattern = new RegExp("\\b" + c + "\\b\\s*", "g");
    this.e.className = this.e.className.replace(pattern, "");
};

// Add c to e.className if it is not already present and return true.
// Otherwise, remove all occurrences of c from e.className and return false.
CSSClassList.prototype.toggle = function(c) {
    if (this.contains(c)) {  // If e.className contains c
        this.remove(c);      // then remove it.
        return false;
    }
    else {                   // Otherwise:
        this.add(c);         // add it.
        return true;
    }
};

// Return e.className itself
CSSClassList.prototype.toString = function() { return this.e.className; };

// Return of the names in e.className
CSSClassList.prototype.toArray = function() {
    return this.e.className.match(/\b\w+\b/g) || [];
};
function do_onoff(hdr, item)
{
	var e = document.getElementById(hdr);
	var e2 = document.getElementById(item);
	if (e2.style.display == 'none') {
		e2.style.display = '';
		classList(e).remove('close');
		classList(e).add('open');
	} else {
		e2.style.display = 'none';
		classList(e).remove('open');
		classList(e).add('close');
	}
}
function open_close(hdr, item)
{
	var e = document.getElementById(hdr);
	var e2 = document.getElementById(item);
	e.addEventListener("click", function() { do_onoff(hdr, item); }, false);

	classList(e).remove('open');
	classList(e).add('close');
	if (classList(e).contains('open')) {
//		e.style.display = '';
		e2.style.display = '';
	}
	if (classList(e).contains('close')) {
//		e.style.display = '';
		e2.style.display = 'none';
	}

}
function OCisSmartPhone()
{
	return (
		(navigator.userAgent.indexOf('iPhone') > 0 && navigator.userAgent.indexOf('iPad') == -1) ||
		navigator.userAgent.indexOf('iPod') > 0 ||
		navigator.userAgent.indexOf('Android') > 0);
}
function OCdisplayWidth()
{
	return window.parent.screen.width;
}
function OCwindowWidth()
{
	if (window.screen.width < window.innerWidth) {
		return window.screen.width;
	}
	return window.innerWidth;
}

function OnFileSelect( inputElement,number )
{
    // ファイルリストを取得
    var fileList = inputElement.files;
    var num = number;

    // ファイルの数を取得
    var fileCount = fileList.length;

    var loadCompleteCount = 0;
    var imageList = "";
    var imageListcon = "";

    // 選択されたファイルの数だけ処理する
    for ( var i = 0; i < fileCount; i++ ) {

        // FileReaderを生成
        var fileReader = new FileReader();

        // ファイルを取得
        var file = fileList[ i ];

        // 読み込み完了時の処理を追加
        fileReader.onload = function() {
            // <li>,<img>タグの生成
        	//alert(this.result);
        	imageList += "<li><img src=\" "+ this.result +"\" width=\"193\" height=\"130\"></li>";

            // 選択されたファイルすべの処理が完了したら、<ul>タグに流し込む
            if ( ++loadCompleteCount == fileCount ) {

                // <ul>タグに<li>,<img>を流し込む

                document.getElementById( "ID00"+num ).innerHTML = imageList;
                if(num == 1){
                document.getElementById("CfmImg1").innerHTML = imageList;
                }
             }
        };

        // ファイルの読み込み(Data URI Schemeの取得)
        fileReader.readAsDataURL( file );
    }
}
var storage = localStorage;
function jumpphoto( Exnumber ){
	var num = ('0000' + Exnumber).slice(-5);
	var Nodeinfo = document.getElementById(num).childNodes;;
	var manu = Nodeinfo[5].innerHTML;
//	var startdate = Nodeinfo[3].textContent;
//	var dis = Nodeinfo[9].textContent;
//	var status = Nodeinfo[15].textContent;
//	var price = Nodeinfo[17].textContent;
//	var explain = Nodeinfo[19].textContent;
	storage.setItem("num", Exnumber);
	storage.setItem("manu", manu);
//	storage.setItem("startdate", startdate);
//	storage.setItem("dis", dis);
//	storage.setItem("status", status);
//	storage.setItem("price", price);
//	storage.setItem("explain", explain);
//
//
//	var purl = "motodetail.html?"+ Exnumber;
//	window.open(purl);
	document.submitForm.Number.value=Exnumber;
    document.submitForm.submit();


}
function setbikeinfo(){
	var oldmanu = storage.getItem("manu");
	var manu = oldmanu.replace('<br>', '/');
//	var olddate = storage.getItem("startdate");
//	var dis = storage.getItem("dis");
//	var startdate = olddate.slice(0, 4) + "/" + olddate.slice(4);
//	var status = storage.getItem("status");
//	var price = storage.getItem("price");
//	var explain = storage.getItem("explain");
//	var num = storage.getItem("num");
//
//	//text編集
	document.getElementById("daimei").textContent = manu;
//	document.getElementById("manu").textContent = manu;
//	document.getElementById("start").textContent = startdate;
//	document.getElementById("dis").textContent = dis;
//	document.getElementById("status").textContent = status;
//	document.getElementById("price").textContent = price;
//	document.getElementById("Wexplainbox2").textContent = explain;

	//img編集
	var num = storage.getItem("num");
	var imgel = document.getElementById("mainimg");
	var Limgel = document.getElementById("largemainimg");
	var imgsrc = "./image/"+ num +"\/"+num+"-1.jpg";
	imgel.setAttribute("src",imgsrc);
	Limgel.setAttribute("src",imgsrc);

	for (var i = 1 ; i<=9 ; i++){
	var aaa = "";
	//var subimgel = document.getElementById("subimg" + i);
	var imgsrc = "./image/"+ num +"\/"+num+"-"+i+".jpg";
	aaa = imagecheck(imgsrc, num ,i);

	}

	document.getElementById( "BaseNo" ).value = num;

}
//画像格納処理
function imagecheck(url ,num , i) {
	var newImage = new Image();
    newImage.src = url;

    // 画像があった時の処理
    newImage.onload = function() {
        var subimgel = document.getElementById("subimg" + i);
        var imgsrc = "./image/"+ num +"\/"+num+"-"+i+".jpg";
        subimgel.setAttribute("src",imgsrc);
    }
    // 画像がなかった時の処理
    newImage.onerror = function() {
    }

}
function imgchange( number ){
	var num = storage.getItem("num");
	var mainimgel = document.getElementById("mainimg");
	var Limgel = document.getElementById("largemainimg");

	//前へボタンを押したとき
	if (number == 0){
		var mainsrc = mainimgel.getAttribute("src");
		var mainnum = parseInt(mainsrc.slice( -5,-4 ));
		var nextnum	= mainnum - 1;
		if(nextnum != 0){
			var imgsrc = "./image/"+ num +"\/"+num+"-"+nextnum+".jpg";
			mainimgel.setAttribute("src",imgsrc);
			Limgel.setAttribute("src",imgsrc);

		}else{
		}
	//次へボタンを押したとき
	}else if(number == 10){
		var aaa = document.getElementsByClassName("imglink");
		var bbb = aaa.item(0).innerHTML;
		var ccc = parseInt(counter(bbb,"./image"));
		var maxnum = ccc+1;
		var mainsrc = mainimgel.getAttribute("src");
		var mainnum = parseInt(mainsrc.slice( -5,-4 ));
		var nextnum	= mainnum + 1;
		if(nextnum != maxnum){
			var imgsrc = "./image/"+ num +"\/"+num+"-"+nextnum+".jpg";
			mainimgel.setAttribute("src",imgsrc);
			Limgel.setAttribute("src",imgsrc);

		}else{
		}
	}else{

	var imgel = document.getElementById("subimg"+number);
	var imgsrc = "./image/"+ num +"\/"+num+"-"+number+".jpg";
	mainimgel.setAttribute("src",imgsrc);
	Limgel.setAttribute("src",imgsrc);
	}
}
var counter = function(str,seq){
    return str.split(seq).length - 1;
}


