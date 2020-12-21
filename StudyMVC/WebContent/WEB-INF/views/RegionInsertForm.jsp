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
<title>RegionInsertForm.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script><!-- 순서 유의 -->

<script type="text/javascript">

	//$();
	$(document).ready(function() 
	{

		// 에러(span 엘리먼트) 안내 초기화
		$("#err").css("display","none");
	
		// 지역 추가 버튼이 클릭되었을 때 수행할 코드 처리
		$("#submitBtn").click(function() 
		{
			//1. 데이터 검사
			//-- 공란(입력항목 누락)이 있는지에 대한 여부 확인
			if( !$("#name").val() )
			{
				$("#err").html("입력 항목이 누락되었습니다.");
				$("#err").css("display","inline");
				return; //-- submit 액션 처리 중단
			}
			
			//submit 액션 처리 수행
			$("#regionForm").submit();
			
		});
		
		// 리스트 버튼이 클릭되었을 때 수행할 코드 처리
		$("#listBtn").click(function() 
		{
			location.href="regionlist.action";	
		});
		
	});

	
	
</script>


</head>
<body>

<!-----------------------------------------
 #32. RegionInsertForm.jsp	
 → 지역 정보 입력 폼(Form) 페이지
    jQuery 기능 포함.
------------------------------------------->


<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[지역 관리] > [지역 정보 입력]</h1>
		<hr>
		
		<form action="regioninsert.action" method="post" id="regionForm" >
			<table>
				<tr>
					<th>지역 이름</th>
					<td>
						<input type="text" id="name" name="name" placeholder="이름"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br>
						<button type="button" class="btn" id="submitBtn"
						style="width: 40%;">지역 추가</button>
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