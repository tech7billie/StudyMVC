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
<!-- - #28. EmployeeList.jsp		→ 직원 정보 출력 페이지. -->
<title>EmployeeList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mainStyle.css">

</head>
<body>
<!--------------------------------------------------------------------------------
	#28. EmployeeList.jsp
	- 직원 리스트 출력 페이지
	- 일반직원이 접근하는 직원 리스트 출력 페이지
	  (관리자가 접근하는 직원 리스트 출력 페이지는 EmployeeList.jsp 로 구성)
---------------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[직원 정보] > [직원 리스트]</h1>
		<hr>

		<!------------------------------------------------------------
		  EMPLOYEEID NAME SSN BIRTHDAY LUNAR LUNARNAME TELEPHONE
		  DEPARTMENTNAME POSITIONNAME REGIONNAME
		 ------------------------------------------------------------>
		
		<table id="customers" class="table">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주민번호</th>
				<th>생년월일</th>
				<th>양/음력</th>
				<th>전화번호</th>
				<th>지역</th>
				<th>부서</th>
				<th>직위</th>
			</tr>
			 
			<!-- <tr>
				<td>5</td>
				<td>신재민</td>
				<td>940116</td>
				<td>1994-01-16</td>
				<td>양력</td>
				<td>010-2231-9316</td>
				<td>서울</td>
				<td>개발부</td>
				<td>사원</td>
			</tr> -->
			 
			<!-- ! JSTL 과 EL 사용. 
				   EL : getParameter() 대신으로는 ${param.xxx} 사용
				        getAttribute() 대신으로는 ${xxx} 사용  
				        employee.employeeId 는 employee.getEmployeeId() 와 같다? -->
				        
			<c:forEach var="employee" items="${employeeList }">
				<tr>
					<td>${employee.employeeId }</td>
					<td>${employee.name} </td>
					<td>${employee.ssn }-*******</td>
					<td>${employee.birthday }</td>
					<td>${employee.lunarName }</td>
					<td>${employee.telephone }</td>
					<td>${employee.regionName }</td>
					<td>${employee.departmentName }</td>
					<td>${employee.positionName }</td>
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