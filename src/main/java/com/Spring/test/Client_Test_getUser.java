package com.Spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.Spring.users.UserDTO;
import com.Spring.users.UserService;

public class Client_Test_getUser {

	public static void main(String[] args) {

		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// ������ �����̳ʷκ��� Bean�� ȣ��  : UserService : �������̽�
		UserService userService = (UserService) factory.getBean("userService");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId("admin");
		userDTO.setPassword("1234");
		
		//service �������̽� �޼ҵ� ȣ��� �������� UserDTO
		UserDTO user =  userService.getUser(userDTO);
		System.out.println(user);
	}

}
