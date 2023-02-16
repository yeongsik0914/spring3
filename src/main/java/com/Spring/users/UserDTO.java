package com.Spring.users;

public class UserDTO {
	//DTO (VO) : client ���� �ѱ� ������ ���� ��Ƽ� DAO�� ����
	//			DAO���� DB�� Select ���� DTO�� ��Ƽ� Ŭ���̾�Ʈ���� ����
	
	private String id;
	private String password;
	private String name;
	private String role;
	
	//�⺻ ������
	public UserDTO() {}

	//Getter /Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//toString() �޼ҵ� ������ : ��ü ��ü�� ��½� �ʵ��� ���� ���
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
	
	
	
	
	
}
