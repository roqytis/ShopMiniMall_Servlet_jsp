package com.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
	  MemberDTO dto=(MemberDTO)session.getAttribute("login"); // 세션에 로그인 키로 set
	  System.out.println(dto);  // 로그인 여부 검사
	
	  String nextPage=null;
		if(dto!=null) {
	    	 nextPage="mypage.jsp";
	    	 String userid=dto.getUserid();     //dto에서 아이디를 꺼내와 Service와 DAO ,Mapper 전달
	    	 
	    	 MemberService service=new MemberService();
	    	 MemberDTO x=service.mypage(userid);
	    	 System.out.println(x);
	       }
		response.sendRedirect(nextPage);
	}
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
