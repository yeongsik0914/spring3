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
		
		// ������ �����̳ʷκ��� Bean�� ȣ��  : BoardService : �������̽�
		BoardService boardService = (BoardService) factory.getBean("boardService");
				
		//DTO ��ü�� ���� �Ŀ� Setter �������� DTO �� �ʵ��� ���� �Է�
		BoardDTO boardDTO = new BoardDTO();
		
		//List<BoardDTO> ������ �޾ƿ�
		
		List<BoardDTO> boardList = boardService.getBoardList(boardDTO);
		
		for (BoardDTO board : boardList) {
			System.out.println(board);
		}
	}

}
