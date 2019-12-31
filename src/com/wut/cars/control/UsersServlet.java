package com.wut.cars.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wut.cars.model.User;
import com.wut.cars.model.UserDAO;


/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao = new UserDAO();
       
    public UsersServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String method=request.getParameter("method");
		switch (method) {
			case "listAll":
			{
				System.out.println("查看所有的");
				//查询数据库获得所有新闻信息然后返回到页面上显示
				List<News> news=dao.listAllNews();

				//讲查询的数据存储到request范围内
				request.setAttribute("news", news);

				//数据存储完毕，跳转到页面上准备在页面上显示所有二手车信息
				request.getRequestDispatcher("newsList.jsp").forward(request, response);

				break;
			}
			case "add":
			{
				System.out.println("添加");
				String userid = request.getParameter("userid");
				String password = request.getParameter("password");
				String birthday = request.getParameter("birthday");
				String personalid = request.getParameter("personalid");
				String email = request.getParameter("email");
				
				User user = new User();
				user.setBirthday(birthday);
				user.setEmail(email);
				user.setPassword(password);
				user.setUserid(userid);
				user.setPersonalid(personalid);
				
				boolean result = dao.adduser(user);
				
				if(result) {
					request.getRequestDispatcher("CarSErvlet?method=add").forward(request, response);
				}else {
					request.getRequestDispatcher("UserAdd.jsp").forward(request, response);
						
					}
				break;
			}
			case "update":
			{
				System.out.println("淇敼");
				break;
			}
			case "delete":
			{
				System.out.println("鍒犻櫎");
				break;
			}
	
		}
	}

}
