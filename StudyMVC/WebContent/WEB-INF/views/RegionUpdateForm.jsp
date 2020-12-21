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
<title>EmployeeUpdateForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script><!-- 순서 유의 -->

<script type="text/javascript">
	var check=0;
	var flag=0;
	
	//$();
	$(document).ready(function() 
	{
		
		// 에러(span 엘리먼트) 안내 초기화
		$("#err").css("display","none");
		
		// 중복(span 엘리먼트) 안내 초기화
		$("#err2").css("display","none");
		
 		// 지역 수정 버튼이 클릭되었을 때 수행할 코드 처리
		$("#submitBtn").click(function() 
		{
			//1. 데이터 검사
			//-- 공란(입력항목 누락)이 있는지에 대한 여부 확인
			if( !$("#regionName").val() )
			{
				//alert($("#name").val());
				
				$("#err").html("입력 항목이 누락되었습니다.");
				$("#err").css("display","inline");
				return; //-- submit 액션 처리 중단
			}
			
			if(flag==0)
			{
				//submit 액션 처리 수행
				$("#regionForm").submit();
			}
			
		});
 		
		//2. 중복 검사
		$("#confirmBtn").click(function() 
		{
			alert(check);
			check = ajaxRequest();
			alert(check);
		});
		
		
		// 리스트 버튼이 클릭되었을 때 수행할 코드 처리
		$("#listBtn").click(function() 
		{
			location.href="regionlist.action";	
		});
		
	});
	
	
	function ajaxRequest()
	{
		$.post("regionajax.action", {regionName: $("#regionName").val() }, function(check) 
		{
			if( Number(check) == 1)
			{
				
				$("#err2").html("이미 존재하는 지역입니다.");
				$("#err2").css("display","inline");
				flag =-1;
				
				
			}
			else if( Number(check) == 0 )
			{
				$("#err2").css("display","none");
				flag=0;
			}
		
			return Number(check);
		});
		
	}
		
</script>


</head>
<body>


<!-----------------------------------------
 #34. RegionUpdateForm.jsp	
 → 지역 정보 수정 폼(Form) 페이지
    AJAX, jQuery 기능 포함.
------------------------------------------->


<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[지역 관리] > [지역 정보 수정]</h1>
		<hr>
		
		<form action="regionupdate.action" method="post" id="regionForm" >
			<table>
				
				<tr>
					<th>지역번호</th>
					<td>
						<input type="text" id="regionId" name="regionId"
						value="${region.regionId }" readonly="readonly">
					</td>
				</tr>
			
				<tr>
					<th>지역이름</th>
					<td>
						<input type="text" id="regionName" name="regionName" 
						placeholder="이름을 입력하세요." value="${region.regionName }"/>
					</td>
					<td>
						<button type="button" class="btn" id="confirmBtn"
						style="width: 100px;">중복확인</button>
						<span id="err2" 
						 style="color: red; font-size:14px; font-weight: bold; display: none;"></span>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<br>
						
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%;">지역 수정</button>
						<button type="button" class="btn" id="listBtn"
						style="width: 40%;">지역 리스트</button>
						
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