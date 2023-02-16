package com.Spring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Actioncontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Actioncontroller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		if (path.equals("/login.action")) {
			System.out.println("�α��� �׼��� ȣ���߽��ϴ�.");
		} else if (path.equals("/logout.action")) {
			System.out.println("�α׾ƿ� �׼��� ȣ���߽��ϴ�.");
		}
	}

}
