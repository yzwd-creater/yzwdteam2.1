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
@MultipartConfig //�������ķ�ʽ�ύ������
public class NewsServlet extends HttpServlet {
	private NewsDAO dao = new NewsDAO();
	/**
	 * Servlet����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ֻҪ����NewsServlet ���������������� Ȼ���ٸ����û���������method����������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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
				//1.��ȡ��ҳ�����û���д������
				String newsId = request.getParameter("newsId");
				String author = request.getParameter("author");
				String time = request.getParameter("time");
				String local = request.getParameter("local");
				String title = request.getParameter("title");
				String readCount = request.getParameter("readCount");
				String content = request.getParameter("content");

				//2.����Щ���Է�װ��һ��java����
				News news = new News();
				news.setNewsId(Integer.parseInt(newsId));
				news.setAuthor(author);
				news.setTime(time);
				news.setLocal(local);
				news.setTitle(title);
                news.setReadCount(Integer.parseInt(readCount));
                news.setContent(content);

				//3.����dao���������������ӵ�������Ϣ���뵽���ݿ����
				boolean result=dao.addNews(news);

				//4.������ӽ����תҳ��
				if(result) {
					request.getRequestDispatcher("NewsServlet?method=listAll").forward(request, response);
				}else {
					request.getRequestDispatcher("NewsAdd.jsp").forward(request, response);
				}
				break;
			}
			case "getNewsInfo":
			{
				System.out.println("�޸�ǰ�Ĳ�ѯ");
				//1.��ȡ�û������Ӵ����������ű��
				String newsId=request.getParameter("newsid");
				//2.����dao��ѯ�����������Ϣ
					News news=dao.getNewsDetailById(Integer.parseInt(newsId));
				//3.����ѯ�����������洢��request��Χ��
				request.setAttribute("news", news);
				//4.��ת���޸ĵ�jsp����ʾ����������ݹ��û��޸Ĳ���
				request.getRequestDispatcher("NewsEdit.jsp").forward(request, response);
				break;
			}
			case "update":
			{
				System.out.println("�޸�");
				//1.�����Ȼ�ȡҳ�����û��޸ĺ�ĳ�����Ϣ
				String newsId = request.getParameter("newsId");
				String author = request.getParameter("author");
				String time = request.getParameter("time");
				String local = request.getParameter("local");
				String title = request.getParameter("title");
				String readCount = request.getParameter("readCount");
				String content = request.getParameter("content");

				//2.����Щ���Է�װ��һ��java����
				News news = new News();
				news.setNewsId(Integer.parseInt(newsId));
				news.setAuthor(author);
				news.setTime(time);
				news.setLocal(local);
				news.setTitle(title);
				news.setReadCount(Integer.parseInt(readCount));
				news.setContent(content);

				//3.����dao���������������ӵ�������Ϣ���뵽���ݿ����
				boolean result=dao.updateNews(news);

				//4.�����޸Ľ����תҳ��
				if(result) {
					request.getRequestDispatcher("NewsServlet?method=listAll").forward(request, response);
				}else {
					request.getRequestDispatcher("newsEdit.jsp").forward(request, response);
				}
				break;
			}
			case "delete":
			{
				System.out.println("ɾ��");
				//1.��ȡ�û������Ӵ����������ű��
				String newsId=request.getParameter("newsId");
				//2.����dao�����������ŵ�����ɾ����
				boolean result=dao.deleteNews(Integer.parseInt(newsId));
				//3.��ɾ���Ľ���洢��request��Χ�ڣ�Ȼ����jsp���жϽ����ʾ�û��������
				request.setAttribute("deleteResult", result);
				//4.��ת���б�ҳ��
				request.getRequestDispatcher("NewsServlet?method=listAll").forward(request, response);
				break;
			}

		}
	}
}
