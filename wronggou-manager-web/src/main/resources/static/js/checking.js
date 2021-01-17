function checkInput(id){
	var val = $("#"+id).val().trim();
	if(val!=""){
		if($("#"+id+"Title")[0]){
			$("#"+id+"Title").html("");
		}
		return true;
	}else{
		if($("#"+id+"Title")[0]){
			$("#"+id+"Title").html("不能为空").css("color","red");
		} else {
			alert("不能为空");
		}
		return false;
	}
}

function checkLoginName(id){
	var val = $("#"+id).val().trim();

	//用户名正则，4到16位（字母，数字，下划线，减号）
	var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;

	if(uPattern.test(val)){
		if($("#"+id+"Title")[0]){
			$("#"+id+"Title").html("");
		}
		return true;
	}else{
		if($("#"+id+"Title")[0]){
			$("#"+id+"Title").html("4到16位（字母，数字，下划线，减号）").css("color","red");
		} else {
			alert("不能为空");
		}
		return false;
	}
}

