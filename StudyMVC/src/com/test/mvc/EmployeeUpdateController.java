/*=================================================
  #22. EmployeeInsertFormController.java
  		- 사용자 정의 컨트롤러 클래스
  		- 직원 데이터 수정 액션 수행 맻 해당 액션 수행 이후
  		  『employeelist.action』을 요청할 수 있도록 처리.
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeUpdateController implements Controller
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();

		// 데이터 수신(EmployeeUpdateForm.jsp 로부터... Employee 를 구성하는 속성들 수신
		String employeeId = request.getParameter("employeeId");
		String name = request.getParameter("name");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");
		String birthday = request.getParameter("birthday");
		String lunar = request.getParameter("lunar");
		String telephone = request.getParameter("telephone");
		String regionId = request.getParameter("regionId");
		String departmentId = request.getParameter("departmentId");
		String positionId = request.getParameter("positionId");
		String basicPay = request.getParameter("basicPay");
		String extraPay = request.getParameter("extraPay");
		
		try
		{
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setName(name);
			employee.setSsn1(ssn1);
			employee.setSsn2(ssn2);
			employee.setBirthday(birthday);
			employee.setLunar(Integer.parseInt(lunar));
			employee.setTelephone(telephone);
			employee.setRegionId(regionId);
			employee.setDepartmentId(departmentId);
			employee.setPositionId(positionId);
			employee.setBasicPay(Integer.parseInt(basicPay));
			employee.setExtraPay(Integer.parseInt(extraPay));
			
			dao.modify(employee);
			
			mav.setViewName("redirect:employeelist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
				
		                                          
		
		
		return mav;
	}
		
}
