/*=================================================
  #42. DepartmentInsertController.java
  		- 사용자 컨트롤러 클래스
  		- 부서 데이터 입력 액션 수행 및 해당 액션 수행 이후
  		  『departmentlist.action』을 요청할 수 있도록 처리.
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DepartmentInsertController implements Controller
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
		
		String departmentName = request.getParameter("departmentName");
		
		try
		{
			Department department = new Department(); 
			
			department.setDepartmentName(departmentName);
			  
			dao.add(department);
			 
			
			mav.setViewName("redirect:departmentlist.action"); 
			//! 『EmployeeList.jsp』 를 요청하는게 아니다. 『EmployeeListController』 에 가게 해야한다.
			//  사용자에게 이 페이지로 가도록 하는거니까 리다이렉트                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
}
