<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeeInsertForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script><!-- 순서 유의 -->

<script type="text/javascript">

	//$();
	$(document).ready(function() 
	{
		// AJAX 요청 및 응답 처리
		ajaxRequest();
		
		// 에러(span 엘리먼트) 안내 초기화
		$("#err").css("display","none");
		
		//jquery-ui 캘린더(datepicker)를 불러오는 함수 처리
		$("#birthday").datepicker(
		{
			dateFormat: "yy-mm-dd" 
			, changeYear : true
			, changeMonth : true
		});
		
		// 직위의 선택된 내용이 변경되었을 경우 수행할 코드 처리
		$("#positionId").change(function() 
		{
			// AJAX 요청 및 응답 처리
			ajaxRequest();
		});
		
		// 직원 추가 버튼이 클릭되었을 때 수행할 코드 처리
		$("#submitBtn").click(function() 
		{
			//1. 데이터 검사
			//-- 공란(입력항목 누락)이 있는지에 대한 여부 확인
			if( $("#name").val()==""||$("#ssn1").val()=="" || $("#ssn2").val()=="" 
					|| $("#birthday").val()=="" || $("#telephone").val()=="" 
					|| $("#basicPay").val()=="" )
			{
				$("#err").html("입력 항목이 누락되었습니다.");
				$("#err").css("display","inline");
				return; //-- submit 액션 처리 중단
			}
			
			// 2. 최소 기본급 유효성 검사 
			//-- 입력한 기본급이 최소 기본급보다 작을 경우 체크
			if( parseInt($("#basicPay").val()) < parseInt($("#minBasicPay").text()) )
			//! 형변환, text() check~!!!
			//  val()은  value 속성의 값이고
			//  text()는 자식 태그들을 문자열로 출력
			//  html()은 태그나 문자열 따질 것 없이 전부 가져와서 적용.
			{
				$("#err").html("입력한 기본급이 최소 기본급보다 작습니다.");
				$("#err").css("display","inline");
			
				return; //-- submit 액션 처리 중단
			}
			
			//submit 액션 처리 수행
			$("#employeeForm").submit();
			
		});
		
		// 리스트 버튼이 클릭되었을 때 수행할 코드 처리
		$("#listBtn").click(function() 
		{
			location.href="employeelist.action";	
		});
		
	});
	
	
	
	function ajaxRequest()
	{
		// 『jQuery.post()』 / 『jQuery.get()』
		// 『$.post()』 / 『$.get()』
		//-- jQuery 에서 AJAX 를 써야 할 경우 지원해주는 함수.
		//   (서버측에서 요청한 데이터를 받아오는 기능의 함수)
		
		// ※ 이 함수(『$.post()』)의 사용 방법(방식)
		//-- 『$.post(요청주소, 전송데이터, 응답액션처리)』
		//   ·요청주소(url)
		//     → 데이터를 요청할 파일에 대한 정보
		//   ·전송데이터(data)
		//     → 서버 측에 요청하는 과정에서 내가 전달할 파라미터
		//   ·응답액션처리(function)
		//     → 응답을 받을 수 있는 함수
		//        여기서는 익명의 함수를 사용 → 단순 기능 구현 및 적용 
		// ※ 참고로 data 는 파라미터의 데이터타입을 그대로 취하게 되므로
		//    html 이든, 문자열이든 상관이 없다.
		
		$.post("ajax.action", {positionId : $("#positionId").val()}, function(data)//! data : 『Ajax.jsp』의 ${result}
		{
				$("#minBasicPay").html(data);
		});
		
	}
	
	
</script>


</head>
<body>

<!-----------------------------------------
 #17. EmployeeInsertForm.jsp	
 → 직원 정보 입력 폼(Form) 페이지
    AJAX, jQuery 기능 포함.
------------------------------------------->


<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[직원 관리] > [직원 정보 입력]</h1>
		<hr>
		
		<form action="employeeinsert.action" method="post" id="employeeForm" >
			<table>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" id="name" name="name" placeholder="이름"/>
					</td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td>
						<input type="text" id="ssn1" name="ssn1"
						style="width: 100px;"> -
						<input type="password" id="ssn2" name="ssn2"
						style="width: 110px;">
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text" id="birthday" name="birthday" placeholder="생년월일"> 
					</td>
				</tr>
				<tr>
					<th>양/음력</th>
					<td>
						<input type="radio" value="0" name="lunar" id="lunar0" checked="checked">
						<label for="lunar0">양력</label>
						<input type="radio" value="1" name="lunar" id="lunar1">
						<label for="lunar1">음력</label>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="tel" id="telephone" name="telephone"
						placeholder="ex)010-1234-1234"/>
					</td>
				</tr>
				<tr>
					<th>지역</th>
					<td>
						<select name="regionId" id="regionId">
							<!--
							<option value="1">서울</option>
							<option value="2">경기</option>
							<option value="3">인천</option>
							-->
							<c:forEach var="region" items="${regionList }">
								<option value="${region.regionId }"> ${region.regionName }</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr>
					<th>부서</th>
					<td>
						<select name="departmentId" id="departmentId">
							<!-- 
							<option value="1">개발부</option>
							<option value="2">기획부</option>
							<option value="3">영업부</option> 
							-->
							<c:forEach var="department" items="${departmentList }">
								<option value="${department.departmentId }" >${department.departmentName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>직위</th>
					<td>
						<select name="positionId" id="positionId"> 
						<!--  onchange="ajaxRequest()" -->
							<!-- 
							<option value="1">사원</option>
							<option value="2">대리</option>
							<option value="3">과장</option>
							 -->
							 <c:forEach var="position" items="${positionList }">
							 	<option value="${position.positionId }">${position.positionName }</option>
							 </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>기본급</th>
					<td>
						<input type="text" id="basicPay" name="basicPay">
						(최소 기본급
						<span id="minBasicPay" style="color: red; font-weight: bold;">0</span>원)
					</td>
				</tr>
				<tr>
					<th>수당</th>
					<td>
						<input type="text" id="extraPay" name="extraPay">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<br><br>
						
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%;">직원 추가</button>
						<button type="button" class="btn" id="listBtn"
						style="width: 40%;">직원 리스트</button>
						<br><br>
						
						<span id="err"
						 style="color: red; font-weight: bold; display: none;"></span>
						<br><br>
						
						
					</td>
				</tr>
			</table>
		</form>
		
	</div>
	
	<!-- 회사 소개 및 애플리케이션 소개 -->
	<div id="footer">
	
	</div>
</div>

</body>
</html>