package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.MemberService;

/**
 * Servlet implementation class CartOrderConfirmServelt
 */
@WebServlet("/CartOrderConfirmServelt")
public class CartOrderConfirmServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderConfirmServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//주문확인 confirm=>주문완료 done
		 HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("login");
		 String nextPage = null;
	      if(dto!=null) {

	    	  String num = request.getParameter("num");//CartDTO
	    	  System.out.println("CartOrderConfirmServlet num====="+num);
	    	  CartService cService = new CartService();
	    	  CartDTO cDTO = cService.cartbyNum(num);//Cart 정보조회
	    	  
	    	  
	    	  String userid =dto.getUserid(); //MemberDTO
	    	  MemberService mService= new MemberService();
	    	  MemberDTO mDTO = mService.mypage(userid);//사용자 정보조회

	          request.setAttribute("cDTO", cDTO);
	          request.setAttribute("mDTO", mDTO);
	          
	       
			nextPage = "orderConfirm.jsp";

	      }else {
			  nextPage = "LoginUIServlet";
			  session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		  }
		
			
			  RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			  dis.forward(request, response);
			 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
