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
				System.out.println("�鿴���е�");
				//��ѯ���ݿ�������������ϢȻ�󷵻ص�ҳ������ʾ
				List<News> news=dao.listAllNews();

				//����ѯ�����ݴ洢��request��Χ��
				request.setAttribute("news", news);

				//���ݴ洢��ϣ���ת��ҳ����׼����ҳ������ʾ���ж��ֳ���Ϣ
				request.getRequestDispatcher("newsList.jsp").forward(request, response);

				break;
			}
			case "add":
			{
				System.out.println("���");
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
				System.out.println("修改");
				break;
			}
			case "delete":
			{
				System.out.println("删除");
				break;
			}
	
		}
	}

}
