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
<title>DepartmentList.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mainStyle.css">

</head>
<body>
<!--------------------------------------------------------------------------------
	#58. DepList.jsp
	- 부서 리스트 출력 페이지
	- 일반직원이 접근하는 부서 리스트 출력 페이지
	  (관리자가 접근하는 부서 리스트 출력 페이지는 DepartmentList.jsp 로 구성)
---------------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[부서 정보] > [부서 리스트]</h1>
		<hr>
		
		<!------------------------------------------------------------
		  EMPLOYEEID NAME SSN BIRTHDAY LUNAR LUNARNAME TELEPHONE
		  DEPARTMENTNAME POSITIONNAME REGIONNAME
		  BASICPAY EXTRAPAY PAY GRADE
		 ------------------------------------------------------------>
		
		<table id="customers" class="table">
			<tr>
				<th>부서 번호</th>
				<th>부서 이름</th>
				
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
				        
			<c:forEach var="department" items="${departmentList }">
				<tr>
					<td>${department.departmentId }</td>
					<td>${department.departmentName} </td>
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