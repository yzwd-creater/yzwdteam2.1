package com.wut.cars.model;
/**
 * ��������ģ��dao�ࣨ����ֻ�ܶ�����������ݿ������صķ�����
 * @author Administrator
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class NewsDAO {


    /**
     * ���Ǹ��ݳ���id��ѯ�����һ�����ŵ�dao����
     * @return
     */
    public  News  getNewsDetailById(int newsId){
        News news=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useUnicode=true&characterEncoding=UTF8","root","root");

            QueryRunner run = new QueryRunner();
            ResultSetHandler<News> h = new BeanHandler<News>(News.class);
            news= run.query(connection, "select * from News  where newsId=?",news,h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    /**
     * ���ǲ�ѯ����������ŵ�dao����
     * @return
     */
    public  List<News>  listAllNews(){
        List<News> news=new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useUnicode=true&characterEncoding=UTF8","root","root");

            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<News>> h = new BeanListHandler<News>(News.class);
            news= run.query(connection, "select * from News order by newsId desc", h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return news;
    }
    /**
     * ����ɾ��������Ϣ��dao����
     * @param
     * @return
     */
    public boolean  deleteNews(int newsId) {
        boolean result=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useUnicode=true&characterEncoding=UTF-8","root","root");

            QueryRunner run = new QueryRunner();
            int count=run.update(connection,"delete from News where newsId=?",newsId);
            result=count>0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * �������������Ϣ��dao����
     * @param
     * @return
     */
    public boolean  addNews(News n) {
        boolean result=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useUnicode=true&characterEncoding=UTF-8","root","root");

            QueryRunner run = new QueryRunner();
            int count=run.update(connection,"insert into  News(newsId,author,time,local,title,readCount,content)  values(?,?,?,?,?,?,?)",n.getNewsId(),n.getAuthor(),n.getTime(),n.getLocal(),n.getTitle(),n.getReadCount(),n.getContent());
            result=count>0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * �����޸�������Ϣ��dao����
     * @param
     * @return
     */
    public boolean  updateNews(News n) {
        boolean result=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useUnicode=true&characterEncoding=UTF-8","root","root");

            QueryRunner run = new QueryRunner();
            int count=run.update(connection,"update   News  set  newsId=?,author=?,time=?,local=?,title=?,readCount=?,content=?  where newsId=?",n.getNewsId(),n.getAuthor(),n.getTime(),n.getLocal(),n.getTitle(),n.getReadCount(),n.getContent());
            result=count>0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
