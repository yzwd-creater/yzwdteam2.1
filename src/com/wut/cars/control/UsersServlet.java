package com.wut.cars.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				System.out.println("查看用户信息");
				//
				List<Car> cars=dao.listAllCars();
				System.out.println(cars.size());
				
				//
				request.setAttribute("", );
				
				//
				request.getRequestDispatcher("").forward(request, response);
				
				
				break;
			}
			case "add":
			{
				System.out.println("娣诲姞");
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
