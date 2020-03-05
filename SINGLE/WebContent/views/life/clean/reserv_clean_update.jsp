<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- START :: include header -->
<%@include file="/views/form/header.jsp" %>
<!-- END :: include header -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>청소 도우미</title>

<!-- START :: CSS -->
<!-- END :: CSS -->

<!-- START :: JAVASCRIPT -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/life/clean/date.js"></script>
<script type="text/javascript">
$(function() {
	
	
	$("#first_pre").text("내일,"+printFirst);
	$("#second_pre").text("모레,"+printSecond);
	$("#third_pre").text(printThird);
	$("#fourth_pre").text(printFourth);
	 
	
		
	
	});

function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("CLEAN_DETAIL_ADDRESS").value = extraAddr.replace(/^\s*/, "") +" ";
            
            } else {
                document.getElementById("CLEAN_DETAIL_ADDRESS").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('CLEAN_POSTCODE').value = data.zonecode;
            document.getElementById("CLEAN_ADDRESS").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("CLEAN_DETAIL_ADDRESS").focus();
        }
    }).open();
}
	//form 전송
	function formSubmit(){
		
		 $("input[name=time_radio]:checked").each(function() {
			   var CLEAN_CODE = $(this).val(); 
			   $("#CLEAN_TIME").val(setTime(CLEAN_CODE));
		});
		 
		 $("input[name=manager_radio]:checked").each(function() {
			   var CLEAN_MANAGER = $(this).val(); 
			   $("#CLEAN_MANAGER").val(CLEAN_MANAGER);
		});
		
		
		
		
		if($("#CLEAN_POSTCODE").val()=="" || $("#CLEAN_ADDRESS").val()==""){
			alert("주소를 입력해주세요"); 
			execDaumPostcode();
			return false;
		}
		if($("#CLEAN_DETAIL_ADDRESS").val() == ""){
			alert("상세주소를 입력해주세요.");
			document.getElementById("CLEAN_DETAIL_ADDRESS").focus();
			return false;
		}
		if($("#CLEAN_TIME").val() ==""){
			alert("예약시간을 선택해주세요.");
			return false;
		}
		
		if($("#CLEAN_MANAGER").val() ==""){
			alert("매니저를 선택해주세요.");
			return false;
		}
			
			
			$("#reserv_form").submit();
		
		
	}
	//목록으로 돌아가기
	function goEdit(){
		location.href="/SINGLE/life/wash/edit.do";
	}
	
</script>
<!-- END :: JAVASCRIPT -->

</head>
<body>
	
	<div class="container">
		<!-- TEXT AREA / BUTTON AREA-->
		<div class="address">
			<h1>주소 입력</h1> <BR> 
			
			<form id="reserv_form" method="post" action="/SINGLE/life/clean/update.do">
				<input type="hidden" id="CLEAN_CODE" name="CLEAN_CODE" value="${clean_info.CLEAN_CODE}">
				<input type="hidden" id="CLEAN_TIME" name="CLEAN_TIME">
				<input type="hidden" id="CLEAN_MANAGER" name="CLEAN_MANAGER">
				<input class="btn btn-info" type="button" onclick="execDaumPostcode()" value="주소수정"><br><br>
				<input class="form-control" type="text" id="CLEAN_POSTCODE" name="CLEAN_POSTCODE" placeholder="우편번호" value="${clean_info.CLEAN_POSTCODE }" onclick="execDaumPostcode()">
				
				<input class="form-control" type="text" id="CLEAN_ADDRESS" name="CLEAN_ADDRESS" placeholder="주소" style="width:400px;" value="${clean_info.CLEAN_ADDRESS }" onclick="execDaumPostcode()"><br>
				<!-- <input type="text" id="extraAddress" placeholder="참고항목"> -->
				<input class="form-control" type="text" id="CLEAN_DETAIL_ADDRESS" name="CLEAN_DETAIL_ADDRESS" placeholder="상세주소" style="width:400px;" value="${clean_info.CLEAN_DETAIL_ADDRESS }">
			</form>
		</div>
		
		<div class="time_div" id="time_div">	
		<div class="btn-group-vertical">		
			<div class="first" >
				<pre id="first_pre"></pre>
				<input type="radio" name="time_radio" value="1"> <input type="button" class="btn btn-outline-dark" value="오후 4시 ~ 6시"  readonly="readonly" onclick="setWashTime(1)" ><br>
			    <input type="radio" name="time_radio" value="2"> <input type="button" class="btn btn-outline-dark" value="오후 6시 ~ 8시"  readonly="readonly" onclick="setWashTime(2)" ><br>
			    <input type="radio" name="time_radio" value="3"> <input type="button" class="btn btn-outline-dark" value="오후 8시 ~ 10시" readonly="readonly" onclick="setWashTime(3)" ><br>
			    <input type="radio" name="time_radio" value="4"> <input type="button" class="btn btn-outline-dark" value="오후 10시 ~ 자정" readonly="readonly" onclick="setWashTime(4)" ><br>
			</div>
			
			<div class="second" style="margin-top: 25px;">
				<pre id="second_pre"></pre>
				<input type="radio" name="time_radio" value="5"> <input type="button" class="btn btn-outline-dark" value="오후 4시 ~ 6시"  readonly="readonly" onclick="setWashTime(5)" ><br>
				<input type="radio" name="time_radio" value="6"> <input type="button" class="btn btn-outline-dark" value="오후 6시 ~ 8시"  readonly="readonly" onclick="setWashTime(6)" ><br>
				<input type="radio" name="time_radio" value="7"> <input type="button" class="btn btn-outline-dark" value="오후 8시 ~ 10시" readonly="readonly" onclick="setWashTime(7)" ><br>
				<input type="radio" name="time_radio" value="8"> <input type="button" class="btn btn-outline-dark" value="오후 10시 ~ 자정" readonly="readonly" onclick="setWashTime(8)" ><br>
			</div>                                               
			                                                     
			<div class="third" style="margin-top: 50px;">        
				<pre id="third_pre"></pre>                       
				<input type="radio" name="time_radio" value="9"> <input type="button" class="btn btn-outline-dark" value="오후 4시 ~ 6시"  readonly="readonly" onclick="setWashTime(9)" ><br>
				<input type="radio" name="time_radio" value="10"> <input type="button" class="btn btn-outline-dark" value="오후 6시 ~ 8시"  readonly="readonly" onclick="setWashTime(10)" ><br>
				<input type="radio" name="time_radio" value="11"> <input type="button" class="btn btn-outline-dark" value="오후 8시 ~ 10시" readonly="readonly" onclick="setWashTime(11)" ><br>
				<input type="radio" name="time_radio" value="12"> <input type="button" class="btn btn-outline-dark" value="오후 10시 ~ 자정" readonly="readonly" onclick="setWashTime(12)" ><br>
			</div>                                               
			                                                     
			<div class="fourth" style="margin-top: 25px;">       
				<pre id="fourth_pre"></pre>                      
				<input type="radio" name="time_radio" value="13"> <input type="button" class="btn btn-outline-dark" value="오후 4시 ~ 6시"  readonly="readonly" onclick="setWashTime(13)" ><br>
				<input type="radio" name="time_radio" value="14"> <input type="button" class="btn btn-outline-dark" value="오후 6시 ~ 8시"  readonly="readonly" onclick="setWashTime(14)" ><br>
				<input type="radio" name="time_radio" value="15"> <input type="button" class="btn btn-outline-dark" value="오후 8시 ~ 10시" readonly="readonly" onclick="setWashTime(15)" ><br>
				<input type="radio" name="time_radio" value="16"> <input type="button" class="btn btn-outline-dark" value="오후 10시 ~ 자정" readonly="readonly" onclick="setWashTime(16)" ><br>
			</div>
			</div>
		</div>
	 
		 
		 <div class="manager" id="manager" >
		 	<input type="radio" name="manager_radio" value="1" <c:if test="${clean_info.CLEAN_MANAGER eq '1'}">checked="checked"</c:if> ><img id="img1" alt="웃음 아이콘" src="${pageContext.request.contextPath}/resources/images/wash/smile.png" style="width:60px;height:60px">
		 	클린닥터
		 	<BR><BR>
		 	<input type="radio" name="manager_radio" value="2" <c:if test="${clean_info.CLEAN_MANAGER eq '2'}">checked="checked"</c:if>><img id="img2" alt="세탁기 아이콘 첫번째" src="${pageContext.request.contextPath}/resources/images/wash/washer.png" style="width:60px;height:60px">
		 	세탁연구소	
		 	<BR><BR>
		 	<input type="radio" name="manager_radio" value="3" <c:if test="${clean_info.CLEAN_MANAGER eq '3'}">checked="checked"</c:if>><img id="img3" alt="세탁기 아이콘 두번째" src="${pageContext.request.contextPath}/resources/images/wash/washer2.png" style="width:60px;height:60px">
		 	일원세탁
		 	<BR><BR>
		 <input class="btn btn-info" type="button" value="수정하기" onclick="formSubmit()"><BR><BR>
		 <input class="btn btn-info" type="button" value="돌아가기" onclick="goEdit()">
		 
		 </div>
	</div>
	


<!-- START :: include footer -->
<%@include file="/views/form/footer.jsp" %>
<!-- END :: include footer -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>