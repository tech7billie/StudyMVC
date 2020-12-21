/*=================================================
  #35. RegionUpdateFormController.java
  		- 사용자 정의 컨트롤러 클래스
  		- 지역 데이터 수정폼 요청에 대한 액션 처리.
  		- DAO 객체에 대한 의존성 주입(DI)를 위한 준비.
  		  → 인터페이스 자료형, setter 메소드 정의.
===================================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionUpdateFormController implements Controller
{
	// 인터페이스 자료형을 기반으로 속성 구성
	private IRegionDAO dao;
	
	public void setDao(IRegionDAO dao)
	{
		this.dao = dao;
	}
	
	// 오버라이딩
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		//세션 처리 과정 추가(로그인에 대한 확인 과정 추가) -----------------------
		HttpSession session = request.getSession();
		if(session.getAttribute("name")==null)	//로그인 되지 않은 상황
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
		
		try
		{
			// 데이터 수신(RegionList.jsp 로부터...regionId 수신)
			String regionId = request.getParameter("regionId");
			
			Region region = new Region();
			
			region = dao.searchId(regionId);
			
			mav.addObject("region", region);
			
			//mav.setViewName("/WEB-INF/views/EmployeeUpdateForm.jsp");
			mav.setViewName("RegionUpdateForm");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
	
}
