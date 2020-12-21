/*=================================================
  #16. EmployeeInsertFormController.java
  		- 사용자 컨트롤러 클래스
  		- 직원 데이터 입력폼 요청에 대한 액션 처리.
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

public class EmployeeInsertFormController implements Controller
{
	//! EmployeeUpdateFormController.java 와 비교
	// 인터페이스형 자료형
	private IEmployeeDAO dao;
	
	// setter
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	// 메소드 오버라이딩 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		//! web.xml 에서 인코딩 필터를 설정했기 때문에 사용하지 않아도 된다.
		//request.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();
		
		// 세션 처리 과정 추가 (로그인 여부, 권한 확인 과정 추가) -------------------------------------
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)// 로그인 안된 상황 
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		// 로그인 된 상황 
		
		else if(session.getAttribute("admin")==null) // 관리자가 아닌 상황 
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		//-----------------------세션 처리 과정 추가(로그인에 대한 확인 과정 추가)
		
		ArrayList<Region> regionList = new ArrayList<Region>();
		ArrayList<Department> departmentList = new ArrayList<Department>();
		ArrayList<Position> positionList = new ArrayList<Position>();
		
		try
		{
			// ! 직원 정보 입력폼에서 보여주려고
			regionList = dao.regionList();
			departmentList = dao.departmentList();
			positionList = dao.positionList();
			
			mav.addObject("regionList",regionList);
			mav.addObject("departmentList", departmentList);
			mav.addObject("positionList", positionList);
			
			//mav.setViewName("/WEB-INF/views/EmployeeInsertForm.jsp");
			mav.setViewName("EmployeeInsertForm");
			
		} catch (Exception e)
		{
			e.toString();
		}
		
		
		return mav;
	}

}
