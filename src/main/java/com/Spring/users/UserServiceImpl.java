package com.Spring.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")	//Spring Framework�� Bean ��� : userService (UserService,UserServiceImpl)
public class UserServiceImpl implements UserService {

	// ����Ͻ� ������ ó���ϴ� Ŭ����
	
	//@Autowired ������̼��� Spring Framework�� ������ Bean�� Ÿ���� ã�Ƽ� ���� (DI) 
	@Autowired	
	UserDAO userDAO;
	
	@Override
	public UserDTO getUser(UserDTO dto) {
		System.out.println(dto.getId());
		System.out.println(dto.getPassword());
		
		return userDAO.getUser(dto);
	}

}
