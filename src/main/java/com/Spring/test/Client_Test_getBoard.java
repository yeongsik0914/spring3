package com.Spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.Spring.board.BoardDTO;
import com.Spring.board.BoardService;

public class Client_Test_getBoard {

	public static void main(String[] args) {
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 스프링 컨테이너로부터 Bean을 호출  : BoardService : 인터페이스
		BoardService boardService = (BoardService) factory.getBean("boardService");
				
		//DTO 객체를 생성 후에 Setter 주입으로 DTO 각 필드의 값을 입력
		BoardDTO boardDTO = new BoardDTO();
		
		//글 상세 보기 : 1개의 레코드만 출력 , 
		boardDTO.setSeq(3);
		
		//getBoard(boardDTO) ==> 리턴으로 DTO
		BoardDTO dbDTO = boardService.getBoard(boardDTO);
		
		//toString () 재정의 되어 있음
		System.out.println(dbDTO);
		
	}

}
