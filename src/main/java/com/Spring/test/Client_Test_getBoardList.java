package com.Spring.test;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.Spring.board.BoardDTO;
import com.Spring.board.BoardService;

public class Client_Test_getBoardList {

	public static void main(String[] args) {
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 스프링 컨테이너로부터 Bean을 호출  : BoardService : 인터페이스
		BoardService boardService = (BoardService) factory.getBean("boardService");
				
		//DTO 객체를 생성 후에 Setter 주입으로 DTO 각 필드의 값을 입력
		BoardDTO boardDTO = new BoardDTO();
		
		//List<BoardDTO> 리턴을 받아옴
		
		List<BoardDTO> boardList = boardService.getBoardList(boardDTO);
		
		for (BoardDTO board : boardList) {
			System.out.println(board);
		}
	}

}
