package com.wut.cars.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wut.cars.model.User;

public class UserDAO {
	public  List<User>  listAllUsers(){
        List<News> news=new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/News?useUnicode=true&characterEncoding=UTF8","root","root");

            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<User>> h = new BeanListHandler<User>(User.class);
            user= run.query(connection, "select * from User order by userId desc", h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return news;
	public  boolean adduser(User u) {
		  boolean result = false;
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF8","root","root");
			
			QueryRunner run = new QueryRunner();
			int count = run.update(connection,"insert into User value(userid,password,birthday,personalid,email)",u.getUserid(),u.getPassword(),u.getBirthday(),u.getPersonalid(),u.getEmail());
			result = count>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return result;
	}

}



