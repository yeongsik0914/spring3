package com.Spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.Spring.board.BoardDTO;
import com.Spring.board.BoardService;

public class Client_Test_delete {

	public static void main(String[] args) {
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// ������ �����̳ʷκ��� Bean�� ȣ��  : BoardService : �������̽�
		BoardService boardService = (BoardService) factory.getBean("boardService");
				
		//DTO ��ü�� ���� �Ŀ� Setter �������� DTO �� �ʵ��� ���� �Է�
		BoardDTO boardDTO = new BoardDTO();
		
		//deleteBoard() �޼ҵ� �׽�Ʈ
		
		//DTO�� seq �÷��� ���� �Ҵ� �� deleteBoard(dto) �޼ҵ� ȣ��
		boardDTO.setSeq(4);
		
		//deleteBoard(boardDTO)
		boardService.deleteBoard(boardDTO);
		
		
	}

}
