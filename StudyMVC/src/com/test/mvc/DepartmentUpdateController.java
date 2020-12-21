/*=================================================
	#45. DepartmentUpdateController.java
  		- 사용자 정의 컨트롤러 클래스
  		- 부서 데이터 수정 액션 수행 맻 해당 액션 수행 이후
  		  『departmentlist.action』을 요청할 수 있도록 처리.
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DepartmentUpdateController implements Controller
{
	private IDepartmentDAO dao;
	
	public void setDao(IDepartmentDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();

		// 데이터 수신(DepartmentUpdateForm.jsp 로부터... department 을 구성하는 속성들 수신
		String departmentId = request.getParameter("departmentId");
		String departmentName = request.getParameter("departmentName");
		
		try
		{
			Department department = new Department();
			department.setDepartmentId(departmentId);
			department.setDepartmentName(departmentName);
			
			dao.modify(department);
			
			mav.setViewName("redirect:departmentlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
				
		
		return mav;
	}
		
}
