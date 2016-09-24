package com.sicdlib.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by maninit on 2016/9/7.
 */
public class DBUtils {
    public static Connection getConnection(String url, String userName, String pwd) {
         Connection conn= null;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(url, userName, pwd);
            return conn;
         }
         catch(Exception e){
            System.out.println("连接失败！");
            return null;
         }
    }
}
