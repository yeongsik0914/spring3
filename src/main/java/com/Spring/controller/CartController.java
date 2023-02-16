package com.Spring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	//doGet, doPost �� �Ѿ���� Ŭ���̾�Ʈ ��û�� process() �޼ҵ忡�� �Ѳ����� ó��
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Ŭ���̾�Ʈ ��û ������ �����ؾ���. path ������ �Ҵ�.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(uri);
		if (path.equals("cart.ca")) {
			System.out.println("cart.ca �� ��û �߽��ϴ�.");
		} else if (path.equals("cart_add.ca")) {
			System.out.println("cart_add.ca�� ��û�߽��ϴ�.");
		}
	}
}
