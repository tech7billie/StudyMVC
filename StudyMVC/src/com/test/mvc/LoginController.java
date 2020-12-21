/*======================================================================
  #26. LoginController.java
  - 사용자 정의 컨트롤러 클래스
  - 로그인 액션 처리 전용 클래스.
  - 로그인 액션 처리 이후 
    『employeelist.action』/『emplist.action』을 요청할 수 있도록 처리.
  -  DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
     → 인터페이스 자료형, setter 메소드 정의.
=======================================================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginController implements Controller
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
		
		// 데이터 수신(LoginForm.jsp로부터... id, pw, admin 수신)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String admin = request.getParameter("admin");
		
		String name = null;
		
		try
		{
			// 로그인 처리 → 대상에 따른 로그인 처리 방식 구분(분기)
			if(admin==null)
			{
				// 일반 사원 로그인
				name = dao.login(id, pw);
			}
			else
			{
				// 관리자 로그인
				name = dao.loginAdmin(id, pw);
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		// 로그인 성공 여부에 따른 구분(분기) 
		if(name==null)
		{
			// 로그인 실패 → 로그인 폼을 다시 요청할 수 있도록 안내
			mav.setViewName("redirect:loginform.action");
		}
		else
		{
			// 로그인 성공
			// → 세션 구성 
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			
			
			if(admin==null)		// 일반 사원으로 로그인 성공한 상황...
			{
				// → emplist.action 페이지를 요청할 수 있도록 안내
				mav.setViewName("redirect:emplist.action");
			}
			else				// 관리자로 로그인 성공한 상황... 
			{
				// → admin 속성을 세션에 추가..
				// → employeelist.action 페이지를 요청할 수 있도록 안내
				session.setAttribute("admin", ""); //! admin이라는 속성 자체를 갖고 있도록..
				mav.setViewName("redirect:employeelist.action");
			}
			
		}
		
		return mav;
	}
	
}
