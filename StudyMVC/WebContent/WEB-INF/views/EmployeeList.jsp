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
<!-- - #14. EmployeeList.jsp		→ 직원 정보 출력 페이지. -->
<title>EmployeeList.jsp</title>
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
			
			$(location).attr("href","employeeupdateform.action?employeeId=" + $(this).val());
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
				$(location).attr("href","employeedelete.action?employeeId=" + $(this).val());
			}
			
		});
		
	});

</script>

</head>
<body>
<!--------------------------------------------------------------------------------
	#14. EmployeeList.jsp
	- 직원 리스트 출력 페이지
	- 관리자가 접근하는 직원 리스트 출력 페이지
	  (일반 직원이 접근하는 직원 리스트 출력 페이지는 EmpList.jsp 로 구성할 예정)
---------------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[직원 관리] > [직원 리스트]</h1>
		<hr>
		
		<div>
			<form action="">
				<input type="button" value="직원 추가" class="btn"
				 onclick="location.href='employeeinsertform.action'" />
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
				<th>주민번호</th>
				<th>생년월일</th>
				<th>양/음력</th>
				<th>전화번호</th>
				<th>지역</th>
				<th>부서</th>
				<th>직위</th>
				<th>기본급</th>
				<th>수당</th>
				<th>급여</th>
				<th>등급</th>
				
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<!-- 
			<tr>
				<td>1</td>
				<td>임효림</td>
				<td>960730</td>
				<td>1996-07-30</td>
				<td>양력</td>
				<td>010-3326-2488</td>
				<td>서울</td>
				<td>개발부</td>
				<td>사원</td>
				<td>1500000</td>
				<td>150000</td>
				<td>1650000</td>
				<td>관리자</td>
				
				<td><button type="button" class="btn">수정</button></td>
				<td><button type="button" class="btn">삭제</button></td>
			</tr>
			 -->
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
					<%-- <td>${employee.basicPay }</td> --%>
					<td>
						<fmt:formatNumber value="${employee.basicPay }" groupingUsed="true"></fmt:formatNumber>
					</td>

					<%-- <td>${employee.extraPay }</td> --%>
					<td>
						<fmt:formatNumber value="${employee.extraPay }" groupingUsed="true"></fmt:formatNumber>
					</td>
					<%-- <td>${employee.pay }</td> --%>
					<td>
						<fmt:formatNumber value="${employee.pay }" groupingUsed="true"></fmt:formatNumber>	
					</td>
					<td>${employee.grade==0 ? "관리자" : "일반사원" }</td>
					
					<td><button type="button" class="btn updateBtn"
					value="${employee.employeeId }">수정</button></td>
					<td><button type="button" class="btn deleteBtn"
					value="${employee.employeeId }">삭제</button></td>
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