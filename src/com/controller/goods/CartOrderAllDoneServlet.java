package com.controller.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.CartService;
import com.service.GoodsService;
import com.service.MemberService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/CartOrderAllDoneServlet")
public class CartOrderAllDoneServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage = null;
		if (dto != null) {
		
			String userid=dto.getUserid();
			String [] nums= request.getParameterValues("num");//num을 배열로 파싱
			System.out.println("doneServlet num"+ nums);
			List<String> list = Arrays.asList(nums); //주문번호만 list로 저장
			String orderName= request.getParameter("orderName");
			String post=request.getParameter("post");
			String addr1=request.getParameter("addr1");
			String addr2=request.getParameter("addr2");
			String phone=request.getParameter("phone");
			String payMethod=request.getParameter("payMethod");//회원정보 파싱
			
			CartService cService = new CartService();
			List<CartDTO> cList = cService.orderAllConfirm(list);//Cart 테이블에서 Cart 조회 저장
			
			//최종적으로 List<OrderDTO> 에 저장.
			List<OrderDTO> oList = new ArrayList<>();
			for(CartDTO cDTO : cList) {//Cart정보와 member정보를 이용하여 OrderDTO 객체 생성 list에 저장
				OrderDTO oDTO = new OrderDTO();
				oDTO.setUserid(userid);
				oDTO.setNum(cDTO.getNum());
				oDTO.setgCode(cDTO.getgCode());
				oDTO.setgName(cDTO.getgName());
				oDTO.setgPrice(cDTO.getgPrice());
				oDTO.setgAmount(cDTO.getgAmount());
				oDTO.setgSize(cDTO.getgSize());
				oDTO.setgColor(cDTO.getgColor());
				oDTO.setgImage(cDTO.getgImage());
				oDTO.setOrderName(orderName);
				oDTO.setPost(post);
				oDTO.setAddr1(addr1);
				oDTO.setAddr2(addr2);
				oDTO.setPhone(phone);
				oDTO.setPayMethod(payMethod);
				System.out.println(oDTO);
				oList.add(oDTO);
			}			
			int n =cService.orderAllDone(oList, list);//Orderlist 저장와 Cart번호만 list로 삭제			
			request.setAttribute("orderAllDone", oList);
			nextPage = "orderAllDone.jsp";

		} else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}

		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
