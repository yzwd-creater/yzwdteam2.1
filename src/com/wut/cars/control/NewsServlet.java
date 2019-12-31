package com.wut.cars.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.wut.cars.model.NewsDAO;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
@MultipartConfig //采用流的方式提交表单内容
public class NewsServlet extends HttpServlet {
	private NewsDAO dao = new NewsDAO();
	/**
	 * Servlet复用
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//只要调用NewsServlet 都会进入这个方法， 然后再根据用户传过来的method参数做分流
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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
				//1.获取表单页面上用户填写的数据
				String newsId = request.getParameter("newsId");
				String author = request.getParameter("author");
				String time = request.getParameter("time");
				String local = request.getParameter("local");
				String title = request.getParameter("title");
				String readCount = request.getParameter("readCount");
				String content = request.getParameter("content");

				//2.将这些属性封装成一个java对象
				News news = new News();
				news.setNewsId(Integer.parseInt(newsId));
				news.setAuthor(author);
				news.setTime(time);
				news.setLocal(local);
				news.setTitle(title);
                news.setReadCount(Integer.parseInt(readCount));
                news.setContent(content);

				//3.调用dao方法，讲这个新添加的新闻信息插入到数据库表中
				boolean result=dao.addNews(news);

				//4.根据添加结果跳转页面
				if(result) {
					request.getRequestDispatcher("NewsServlet?method=listAll").forward(request, response);
				}else {
					request.getRequestDispatcher("NewsAdd.jsp").forward(request, response);
				}
				break;
			}
			case "getNewsInfo":
			{
				System.out.println("修改前的查询");
				//1.获取用户超链接传过来的新闻编号
				String newsId=request.getParameter("newsid");
				//2.调用dao查询出这个新闻信息
					News news=dao.getNewsDetailById(Integer.parseInt(newsId));
				//3.将查询出来的汽车存储到request范围内
				request.setAttribute("news", news);
				//4.跳转到修改的jsp上显示这个车辆数据供用户修改操作
				request.getRequestDispatcher("NewsEdit.jsp").forward(request, response);
				break;
			}
			case "update":
			{
				System.out.println("修改");
				//1.还是先获取页面上用户修改后的车辆信息
				String newsId = request.getParameter("newsId");
				String author = request.getParameter("author");
				String time = request.getParameter("time");
				String local = request.getParameter("local");
				String title = request.getParameter("title");
				String readCount = request.getParameter("readCount");
				String content = request.getParameter("content");

				//2.将这些属性封装成一个java对象
				News news = new News();
				news.setNewsId(Integer.parseInt(newsId));
				news.setAuthor(author);
				news.setTime(time);
				news.setLocal(local);
				news.setTitle(title);
				news.setReadCount(Integer.parseInt(readCount));
				news.setContent(content);

				//3.调用dao方法，讲这个新添加的汽车信息插入到数据库表中
				boolean result=dao.updateNews(news);

				//4.根据修改结果跳转页面
				if(result) {
					request.getRequestDispatcher("NewsServlet?method=listAll").forward(request, response);
				}else {
					request.getRequestDispatcher("newsEdit.jsp").forward(request, response);
				}
				break;
			}
			case "delete":
			{
				System.out.println("删除");
				//1.获取用户超链接传过来的新闻编号
				String newsId=request.getParameter("newsId");
				//2.调用dao方法将这个编号的新闻删除掉
				boolean result=dao.deleteNews(Integer.parseInt(newsId));
				//3.将删除的结果存储到request范围内，然后再jsp上判断结果提示用户操作结果
				request.setAttribute("deleteResult", result);
				//4.跳转到列表页面
				request.getRequestDispatcher("NewsServlet?method=listAll").forward(request, response);
				break;
			}

		}
	}
}
