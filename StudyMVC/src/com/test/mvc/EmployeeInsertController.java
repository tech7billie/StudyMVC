/*=================================================
  #19. EmployeeInsertController.java
  		- 사용자 컨트롤러 클래스
  		- 직원데이터 입력 액션 수행 및 해당 액션 수행 이후
  		  『employeelist.action』을 요청할 수 있도록 처리.
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeInsertController implements Controller
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
		
		// 데이터 수신(EmployeeInsertForm.jsp 로부터... 사용자 입력값 수신)
		// request.setCharacterEncoding("UTF-8");
		//! web.xml 에서 인코딩 필터 설정했기 때문에 더이상 필요 없다. 
		
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
		
		System.out.println(name);
		System.out.println(ssn1);
		System.out.println(ssn2);
		System.out.println(birthday);
		System.out.println(lunar);
		System.out.println(telephone);
		System.out.println(regionId);
		System.out.println(departmentId);
		System.out.println(positionId);
		System.out.println(basicPay);
		System.out.println(extraPay);
		
		try
		{
			
			Employee employee = new Employee(); 
			
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
			  
			dao.employeeAdd(employee);
			 
			
			mav.setViewName("redirect:employeelist.action"); 
			//! 『EmployeeList.jsp』 를 요청하는게 아니다. 『EmployeeListController』 에 가게 해야한다.
			//  사용자에게 이 페이지로 가도록 하는거니까 리다이렉트                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
}
