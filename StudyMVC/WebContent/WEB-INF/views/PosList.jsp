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
<title>PositionList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mainStyle.css">

</head>
<body>
<!--------------------------------------------------------------------------------
	#60. PosList.jsp
	- 직위 리스트 출력 페이지
	- 일반직원이 접근하는 직위 리스트 출력 페이지
	  (관리자가 접근하는 직위 리스트 출력 페이지는 PositionList.jsp 로 구성)
---------------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[직위 정보] > [직위 리스트]</h1>
		<hr>
		
		<table id="customers" class="table">
			<tr>
				<th>직위 번호</th>
				<th>직위 이름</th>
				<th>최소 기본급</th>
				
			</tr>
				        
			<c:forEach var="position" items="${positionList }">
				<tr>
					<td>${position.positionId }</td>
					<td>${position.positionName} </td>
					<td>${position.minBasicPay} </td>
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