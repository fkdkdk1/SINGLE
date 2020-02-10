package com.single.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.single.model.biz.user.MemberBiz;
import com.single.model.biz.user.MemberBizImpl;
import com.single.model.dto.user.KakaoMemberDTO;
import com.single.model.dto.user.MemberDTO;

/*
 * Member Controller
 * ȸ�� ���� ����� ȣ���ϱ� ����
 */
@WebServlet(//
		name = "member", //
		urlPatterns = { //
				"joinpage.do", // join.jsp�� �̵�
				"join.do", // ȸ������ ó��
				"snsjoin.do", // SNS ȸ������ ó��
				"loginpage.do", // login.jsp�� �̵�
				"login.do", // �α��� ó��
				"logout.do" // �α׾ƿ� ó��
		})

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberBiz biz = new MemberBizImpl();

	HttpSession session;

	public MemberController() {
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getRequestURI();
		System.out.println("[ " + command + " ]");

		if (command.endsWith("/joinpage.do")) {
			joinpage(request, response);
		}

		else if (command.endsWith("/join.do")) {
			doJoin(request, response);
		}

		else if (command.endsWith("/snsjoin.do")) {
			doSNSJoin(request, response);
		}

		else if (command.endsWith("/loginpage.do")) {
			loginpage(request, response);
		}

		else if (command.endsWith("/login.do")) {
			doLogin(request, response);
		}

		else if (command.endsWith("/logout.do")) {
			doLogout(request, response);
		}

	}

	// ȸ������ �������� �̵�
	private void joinpage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatch("/views/user/join.jsp", request, response);
	}

	// ȸ������ ó��
	private void doJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int MEMBER_VERIFY = Integer.parseInt(request.getParameter("MEMBER_VERIFY"));
		String MEMBER_EMAIL = request.getParameter("MEMBER_EMAIL");
		String MEMBER_PASSWORD = request.getParameter("MEMBER_PASSWORD");
		String MEMBER_NAME = request.getParameter("MEMBER_NAME");
		String MEMBER_NICKNAME = request.getParameter("MEMBER_NICKNAME");
		String MEMBER_GENDER = request.getParameter("MEMBER_GENDER");

		MemberDTO new_member = new MemberDTO(MEMBER_VERIFY, MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NAME, MEMBER_NICKNAME,
				MEMBER_GENDER);

		int res = biz.memberJoin(new_member);

		if (res > 0) {
			jsResponse("ȸ������ ����", "/SINGLE/member/loginpage.do", response);
		} else {
			jsResponse("ȸ������ ����", "/SINGLE/member/joinpage.do", response);
		}
	}

	// SNS ȸ������ ó��
	private void doSNSJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int MEMBER_VERIFY = Integer.parseInt(request.getParameter("MEMBER_VERIFY"));
		String MEMBER_EMAIL = request.getParameter("MEMBER_EMAIL");
		String MEMBER_NAME = request.getParameter("MEMBER_NAME");
		String MEMBER_NICKNAME = request.getParameter("MEMBER_NICKNAME");
		String MEMBER_GENDER = request.getParameter("MEMBER_GENDER");

		String snsType = request.getParameter("snsType");
		String SNS_ID = request.getParameter("SNS_ID");
		String SNS_NICKNAME = request.getParameter("SNS_NICKNAME");
		String access_token = request.getParameter("access_token");

		System.out.println(snsType);

		if (snsType.equals("KAKAO")) {
			KakaoMemberDTO kakao_member = new KakaoMemberDTO(MEMBER_VERIFY, MEMBER_EMAIL, MEMBER_NAME, MEMBER_NICKNAME,
					MEMBER_GENDER, SNS_ID, SNS_NICKNAME);

			System.out.println(kakao_member);

			int res = biz.kakaoJoin(kakao_member);

			if (res > 0) {
				// ȸ������ �� �ڵ��α���
				session = request.getSession();
				session.setAttribute("loginKakao", kakao_member);
				session.setAttribute("access_token", access_token);
				jsResponse("KAKAO ȸ������ ����", "/SINGLE/main/mainpage.do", response);
			} else {
				jsResponse("KAKAO ȸ������ ����", "/SINGLE/member/joinpage.do", response);
			}
		}
	}

	// �α��� �������� �̵�
	private void loginpage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatch("/views/user/login.jsp", request, response);
	}

	// �α��� ó��
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		MemberDTO member = new MemberDTO();

		String MEMBER_EMAIL = request.getParameter("MEMBER_EMAIL");
		String MEMBER_PASSWORD = request.getParameter("MEMBER_PASSWORD");

		member.setMEMBER_EMAIL(MEMBER_EMAIL);
		member.setMEMBER_PASSWORD(MEMBER_PASSWORD);

		MemberDTO loginMember = biz.memberLogin(member);

		if (loginMember != null) {
			session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			jsResponse("�α��� ����", "/SINGLE/main/mainpage.do", response);
		} else {
			jsResponse("�α��� ����", "/SINGLE/member/loginpage.do", response);
		}

	}

	// �α׾ƿ� ó��
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		jsResponse("�α׾ƿ�", "/SINGLE/main/mainpage.do", response);
	}

	/*
	 * Servlet Basic Template : PLEASE DO NOT MODIFY !!!!!
	 */
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		processRequest(request, response);
	}
}
