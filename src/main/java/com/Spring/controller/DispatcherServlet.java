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

    //doGet으로 넘어오는 요청을 process () 메소드에서 처리하도록 넘김
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Post 방식으로 변수의 값을 넘길 때 한글 깨짐 방지 처리
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	// doGet, doPost 의 모든 요청을 처리하는 메소드
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// URL : hhtp://localhost:8080/boardweb/getBoardList.do
		// URI : boardweb/getBoardList.do
		
		
		//1. 클라이언트의 요청 path 정보를 추출 한다.
		String url = request.getRequestURL().toString();	//URL 정보를 게더링 .toString()
		System.out.println(url);
		
		String uri = request.getRequestURI();			//boardweb/getBoardList.do
			System.out.println("uri : " + uri);
		String path = uri.substring(uri.lastIndexOf("/"));	//getBoardList.do
	
		//2. 클라이언트의 요청 정보에 따라서 적절하게 분기 처리함.
		
		if (path.equals("/login.do")) {
			
			// 클라이언트 요청에 대해서 : /login.do 요청
			//1. Model : Service (비즈니스 로직을 처리) , (DTO, DAO)
			//2. View 페이지로 전달 : *.jsp 파일
			
			System.out.println("사용자 정보 처리");
			System.out.println("/login.do 요청을 보냈습니다.");
			
			// 1. 클라이언트에서 보내는 변수 값을 받아서 변수에 저장
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			System.out.println("폼에서 넘긴 변수 id 값 출력 : " + id);
			System.out.println("폼에서 넘긴 변수 password 값 출력 : " + password);
			
			//2. 클라이언트에서 넘긴 변수값을 받아서 저장된 변수를 DTO에 Setter 주입
			UserDTO dto = new UserDTO();
			dto.setId(id);
			dto.setPassword(password);
			
			//3. 비즈니스 로직을 처리하는 인터페이스에 dto를 주입해서 비즈니스 로직을 처리합니다.
			UserService user = (UserService) factory.getBean("userService");
			UserDTO userD = user.getUser(dto);
			
			//DB의 클라이언트에서 넘긴 ID 와 Password를 select 해서 그 값을 DTO에 담아서 리턴
			System.out.println(userD);
			
			//4. 백엔드의 로직을 모두 처리후 View 페이지로 이동
			if ((userD.getId() != null) || (userD.getPassword() != null)) {	//클라이언트에게 전송한 ID와 Pass가 DB의 값과 일치할 때
				response.sendRedirect("getBoardList.do");
				System.out.println("아이디와 패스워드 일치");
			}else { //Client에게 전송한 ID와 Pass중 일치하지 않을 때
				response.sendRedirect("login.jsp");
				System.out.println("아이디와 패스워드 불일치");
			}
			
		} else if (path.equals("/getBoardList.do")) {
			System.out.println("게시판 정보 출력");
			
			//1. Client 로 부터 /getBoardList.do 요청을 받음. (게시판 정보를 출력해 달라고 요청)
			
			//2. 비즈니스 로직 처리
			BoardDTO dto = new BoardDTO();
			//BoardDAO dao = new BoardDAO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			//boardList 에는 DB에서 쿼리한 레코드를 담은 DTO 객체가 내장되어 있다.
			List<BoardDTO> boardList = service.getBoardList(dto);
			
			//3. 클라이언트에게 boardList를 전달해야 한다. 
			//(세션 객체에 boardList 객체를 담아서 전송 시킴)
			// 세션은 서버의 RAM에 저장됨.
			// 쿠키: 클라이언트 시스템의 HDD에 정보를 저장함.
			HttpSession session = request.getSession();
			//session 객체에 값을 저장 , setAttribute("변수명", 객체);
			//session 객체에 값을 가지고 올 떄 , getAttribute("변수명"); 
			session.setAttribute("boardList", boardList);
			
			
			//4. 뷰페이지로 이동
			response.sendRedirect("getBoardList.jsp");
			
		} else if (path.equals("/insertBoard.do")) {
			
			System.out.println("board 테이블의 값을 저장");
			//1. 클라이언트에서 넘어오는 변수 값을 받아서 새로운 변수에 저장
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. 비즈니스 로직 처리 (클라이언트의 변수를 dto 에 저장후 service의 insertBoard(dto)
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			//dto 의 setter 메소드 호출시 클라이언트에게 넘어오는 변수를 할당.
			dto.setTitle(title);
			dto.setWriter(writer);
			dto.setContent(content);
			
			service.insertBoard(dto);	//DB에 Insert 가 완료됨. Insert/ Update / delete 리턴 값 없음
			
			//3. view 페이지를 전송
			response.sendRedirect("getBoardList.do");
			
			
		} else if (path.equals("/getBoard.do")) {
			
			System.out.println("게시판 상세 내용 보기 - /getBoard.do 요청함");
			
			//1. 클라이언트의 넘긴 변수 값 받기 ("seq")
			String seq = request.getParameter("seq");	//getParameter 로 넘어오는 값은 모두 String
			System.out.println("seq 변수값 : " + seq);
			
			//2. 비즈니스 로직 처리 : 파라미터로 받은 값을 DTO에 저장후 getBoard(dto) 메소드 호출
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
		
			//클라이언트에게 받은 값을 dto에 setter 주입
			dto.setSeq(Integer.parseInt(seq));
			
			//리턴을 받아온다.
			BoardDTO board = service.getBoard(dto);
			
			//DB의 값이 저장된 DTO (board) 를 session 변수에 할당해서 뷰페이지로 전달
			HttpSession session = request.getSession();
			
			session.setAttribute("board", board);
			
			//3. 뷰 페이지로 전달
			response.sendRedirect("getBoard.jsp");
			
		} else if (path.equals("/updateBoard.do")) {
			
			System.out.println("글 수정 처리");
			
			//1. 클라이언트에서 넘어오는 변수를 받음.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			/*
			System.out.println(seq);
			System.out.println(title);
			System.out.println(content);
			*/
			
			//2. DTO, Service 객체를 사용해서 비즈니스 로직 처리
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			dto.setSeq(Integer.parseInt(seq));
			dto.setContent(content);
			dto.setTitle(title);
			
			service.updateBoard(dto);
			
			//3. 백엔드의 로직을 모두 처리후 client 에게 View 페이지로 이동
			response.sendRedirect("getBoardList.do");
		} else if (path.equals("/deleteBoard.do")) {
			
			System.out.println("글 삭제 처리");
			
			//1. 클라이언트에서 넘긴 seq 를 받아서 변수에 저장함.
			String seq = request.getParameter("seq");
			
			//2. DTO, Service에 로직 처리 (백엔드의 비즈니스 로직 처리)
			BoardDTO dto = new BoardDTO();
			BoardService service = (BoardService) factory.getBean("boardService");
			
			dto.setSeq(Integer.parseInt(seq));
			
			service.deleteBoard(dto);
			
			//3. 비즈니스 로직 처리 완료후 View 페이지로 이동
			response.sendRedirect("getBoardList.do");
		} else if (path.equals("/logout.do")) {
			
			System.out.println("사용자 로그 아웃 처리");
		}
		
		
	}
}
