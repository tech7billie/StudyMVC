<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegionList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mainStyle.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	
	// jquery(document).ready();
	// $(document).ready();
	// jquery();
	$(function() 
	{
		
		// 테스트
		// alert("확인");
		
		// 수정 버튼 클릭 시 액션 처리 
		$(".updateBtn").click(function() 
		{
			// 테스트
			// alert("수정 버튼 클릭~!!");
			// alert($(this).val());
			
			$(location).attr("href","regionupdateform.action?regionId=" + $(this).val());
			//																  -------------
			//															! updateBtn 을 클래스로 가지고 있는 
			//															! 엘리먼트의 value 
			
		});
		
		// 삭제 버튼 클릭 시 액션 처리
		$(".deleteBtn").click(function() 
		{
			// 테스트
			// alert("삭제 버튼 클릭~!!");
			// alert($(this).val());
			
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href","regiondelete.action?regionId=" + $(this).val());
			}
			
		});
		
	});

</script>

</head>
<body>
<!--------------------------------------------------------------------------------
	#30. RegionList.jsp
	- 지역 리스트 출력 페이지
	- 관리자가 접근하는 직원 리스트 출력 페이지
	  (일반 직원이 접근하는 직원 리스트 출력 페이지는 RegList.jsp 로 구성할 예정)
---------------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[지역 관리] > [지역 리스트]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="지역 추가" class="btn"
				 onclick="location.href='regioninsertform.action'" />
			</form>
		</div>
		
		<br><br>
		<!------------------------------------------------------------
		  EMPLOYEEID NAME SSN BIRTHDAY LUNAR LUNARNAME TELEPHONE
		  DEPARTMENTNAME POSITIONNAME REGIONNAME
		  BASICPAY EXTRAPAY PAY GRADE
		 ------------------------------------------------------------>
		
		<table id="customers" class="table">
			<tr>
				<th>번호</th>
				<th>이름</th>
				
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<!-- 
			<tr>
				<td>1</td>
				<td>서울</td>
				
				<td><button type="button" class="btn">수정</button></td>
				<td><button type="button" class="btn">삭제</button></td>
			</tr>
			 -->
			<!-- ! JSTL 과 EL 사용. 
				   EL : getParameter() 대신으로는 ${param.xxx} 사용
				        getAttribute() 대신으로는 ${xxx} 사용  
				        employee.employeeId 는 employee.getEmployeeId() 와 같다? -->
				        
			<c:forEach var="region" items="${regionList }">
				<tr>
					<td>${region.regionId }</td>
					<td>${region.regionName} </td>
					
					<td><button type="button" class="btn updateBtn"
					value="${region.regionId }">수정</button></td>
					<td><button type="button" class="btn deleteBtn"
					value="${region.regionId }" 
					${region.delCheck > 0 ? "disabled='disabled'" : "" }
					>삭제</button></td>
				</tr>
			</c:forEach>		
					
		</table>
	</div>
	
	<!-- 회사 소개 및 애플리케이션 소개 -->
	<div id="footer">
	
	</div>

</div>

</body>
</html>