package com.Spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.Spring.board.BoardDAO;
import com.Spring.board.BoardDTO;
import com.Spring.board.BoardService;
import com.Spring.users.UserDTO;
import com.Spring.users.UserService;

/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	AbstractApplicationContext factory =
			new GenericXmlApplicationContext("applicationContext.xml");
	
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }

    //doGet���� �Ѿ���� ��û�� process () �޼ҵ忡�� ó���ϵ��� �ѱ�
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Post ������� ������ ���� �ѱ� �� �ѱ� ���� ���� ó��
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	// doGet, doPost �� ��� ��û�� ó���ϴ� �޼ҵ�
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// URL : hhtp://localhost:8080/boardweb/getBoardList.do
		// URI : boardweb/getBoardList.do
		
		
		//1. Ŭ���̾�Ʈ�� ��û path ������ ���� �Ѵ�.
		String url = request.getRequestURL().toString();	//URL ������ �Դ��� .toString()
		System.out.println(url);
		
		String uri = request.getRequestURI();			//boardweb/getBoardList.do
			System.out.println("uri : " + uri);
		String path = uri.substring(uri.lastIndexOf("/"));	//getBoardList.do
	
		//2. Ŭ���̾�Ʈ�� ��û ������ ���� �����ϰ� �б� ó����.
		
		if (path.equals("/login.do")) {
			
			// Ŭ���̾�Ʈ ��û�� ���ؼ� : /login.do ��û
			//1. Model : Service (����Ͻ� ������ ó��) , (DTO, DAO)
			//2. View �������� ���� : *.jsp ����
			
			System.out.println("����� ���� ó��");
			System.out.println("/login.do ��û�� ���½��ϴ�.");
			
			// 1. Ŭ���̾�Ʈ���� ������ ���� ���� �޾Ƽ� ������ ����
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			System.out.println("������ �ѱ� ���� id �� ��� : " + id);
			System.out.println("������ �ѱ� ���� password �� ��� : " + password);
			
			//2. Ŭ���̾�Ʈ���� �ѱ� �������� �޾Ƽ� ����� ������ DTO�� Setter ����
			UserDTO dto = new UserDTO();
			dto.setId(id);
			dto.setPassword(password);
			
			//3. ����Ͻ� ������ ó���ϴ� �������̽��� dto�� �����ؼ� ����Ͻ� ������ ó���մϴ�.
			UserService user = (UserService) factory.getBean("userService");
			UserDTO userD = user.getUser(dto);
			
			//DB�� Ŭ���̾�Ʈ���� �ѱ� ID �� Password�� select �ؼ� �� ���� DTO�� ��Ƽ� ����
			System.out.println(userD);
			
			//4. �鿣���� ������ ��� ó���� View �������� �̵�
			if ((userD.getId() != null) || (userD.getPassword() != null)) {	//Ŭ���̾�Ʈ���� ������ ID�� Pass�� DB�� ���� ��ġ�� ��
				response.sendRedirect("getBoardList.do");
				System.out.println("���̵�� �н����� ��ġ");
			}else { //Client���� ������ ID�� Pass�� ��ġ���� ���� ��
				response.sendRedirect("login.jsp");
				System.out.println("���̵�� �н����� ����ġ");
			}
			
		} else if (path.equals("/getBoardList.do")) {
			System.out.println("�Խ��� ���� ���");
			
			//1. Client �� ���� /getBoardList.do ��û�� ����. (�Խ��� ������ ����� �޶�� ��û)
			
			//2. ����Ͻ� ���� ó��
			BoardDTO dto = new BoardDTO();
			//BoardDAO dao = new BoardDAO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			//boardList ���� DB���� ������ ���ڵ带 ���� DTO ��ü�� ����Ǿ� �ִ�.
			List<BoardDTO> boardList = service.getBoardList(dto);
			
			//3. Ŭ���̾�Ʈ���� boardList�� �����ؾ� �Ѵ�. 
			//(���� ��ü�� boardList ��ü�� ��Ƽ� ���� ��Ŵ)
			// ������ ������ RAM�� �����.
			// ��Ű: Ŭ���̾�Ʈ �ý����� HDD�� ������ ������.
			HttpSession session = request.getSession();
			//session ��ü�� ���� ���� , setAttribute("������", ��ü);
			//session ��ü�� ���� ������ �� �� , getAttribute("������"); 
			session.setAttribute("boardList", boardList);
			
			
			//4. ���������� �̵�
			response.sendRedirect("getBoardList.jsp");
			
		} else if (path.equals("/insertBoard.do")) {
			
			System.out.println("board ���̺��� ���� ����");
			//1. Ŭ���̾�Ʈ���� �Ѿ���� ���� ���� �޾Ƽ� ���ο� ������ ����
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. ����Ͻ� ���� ó�� (Ŭ���̾�Ʈ�� ������ dto �� ������ service�� insertBoard(dto)
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			//dto �� setter �޼ҵ� ȣ��� Ŭ���̾�Ʈ���� �Ѿ���� ������ �Ҵ�.
			dto.setTitle(title);
			dto.setWriter(writer);
			dto.setContent(content);
			
			service.insertBoard(dto);	//DB�� Insert �� �Ϸ��. Insert/ Update / delete ���� �� ����
			
			//3. view �������� ����
			response.sendRedirect("getBoardList.do");
			
			
		} else if (path.equals("/getBoard.do")) {
			
			System.out.println("�Խ��� �� ���� ���� - /getBoard.do ��û��");
			
			//1. Ŭ���̾�Ʈ�� �ѱ� ���� �� �ޱ� ("seq")
			String seq = request.getParameter("seq");	//getParameter �� �Ѿ���� ���� ��� String
			System.out.println("seq ������ : " + seq);
			
			//2. ����Ͻ� ���� ó�� : �Ķ���ͷ� ���� ���� DTO�� ������ getBoard(dto) �޼ҵ� ȣ��
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
		
			//Ŭ���̾�Ʈ���� ���� ���� dto�� setter ����
			dto.setSeq(Integer.parseInt(seq));
			
			//������ �޾ƿ´�.
			BoardDTO board = service.getBoard(dto);
			
			//DB�� ���� ����� DTO (board) �� session ������ �Ҵ��ؼ� ���������� ����
			HttpSession session = request.getSession();
			
			session.setAttribute("board", board);
			
			//3. �� �������� ����
			response.sendRedirect("getBoard.jsp");
			
		} else if (path.equals("/updateBoard.do")) {
			
			System.out.println("�� ���� ó��");
			
			//1. Ŭ���̾�Ʈ���� �Ѿ���� ������ ����.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			/*
			System.out.println(seq);
			System.out.println(title);
			System.out.println(content);
			*/
			
			//2. DTO, Service ��ü�� ����ؼ� ����Ͻ� ���� ó��
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			dto.setSeq(Integer.parseInt(seq));
			dto.setContent(content);
			dto.setTitle(title);
			
			service.updateBoard(dto);
			
			//3. �鿣���� ������ ��� ó���� client ���� View �������� �̵�
			response.sendRedirect("getBoardList.do");
		} else if (path.equals("/deleteBoard.do")) {
			
			System.out.println("�� ���� ó��");
			
			//1. Ŭ���̾�Ʈ���� �ѱ� seq �� �޾Ƽ� ������ ������.
			String seq = request.getParameter("seq");
			
			//2. DTO, Service�� ���� ó�� (�鿣���� ����Ͻ� ���� ó��)
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			dto.setSeq(Integer.parseInt(seq));
			
			service.deleteBoard(dto);
			
			//3. ����Ͻ� ���� ó�� �Ϸ��� View �������� �̵�
			response.sendRedirect("getBoardList.do");
		} else if (path.equals("/logout.do")) {
			
			System.out.println("����� �α� �ƿ� ó��");
		}
		
		
	}
}
