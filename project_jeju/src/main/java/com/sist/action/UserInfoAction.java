package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.UserDAO;

public class UserInfoAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = "userInfo.jsp";
		System.out.println("userinfo actio run");
		//String viewPage = "test.jsp";
		return viewPage;
	}

}
