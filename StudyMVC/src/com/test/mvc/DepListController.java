/*=================================================
  #59. DepListController.java
  		- 사용자 정의 컨트롤러 클래스
  		- 부서 리스트 페이지 요청에 대한 액션 처리.(일반직원)
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/
package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DepListController implements Controller
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

		// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)---------------------
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)	//-- 로그인이 되어있지 않은 상황
		{
			// 로그인이 되어 있지 않은 상황에서의 처리
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		
		//-----------------------세션 처리 과정 추가(로그인에 대한 확인 과정 추가)
		
		ArrayList<Department> departmentList = new ArrayList<Department>();
		
		try
		{
			departmentList = dao.list();
			
			mav.addObject("departmentList", departmentList);
			
			//mav.setViewName("/WEB-INF/views/EmpList.jsp");
			mav.setViewName("DepList");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
				
		
		return mav;
	}
	
	
}
