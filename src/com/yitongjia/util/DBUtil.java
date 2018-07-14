package com.yitongjia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库工具类
 * @author bym @date 2018年7月4日
 */
public class DBUtil {
	 static Connection conn=null;
     static PreparedStatement pstmt=null;
    static ResultSet rs=null;	 
    //获取数据库连接
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mywx", "myab33", "ssks@akeffdfd(sgWDgw%&2Sd*s!?s");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭所有资源 
    public static void closeAll(){
        try {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public static ResultSet executeQuery(String sql, Object[] params) {
		//获取连接
		Connection conn=getConnection();
		
		try {
			//获取PreparedStatement对象，通过连接对象获取
			pstmt=conn.prepareStatement(sql);
			//判断参数列表是否为空
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					//设置占位符
					pstmt.setObject(i+1, params[i]);
				}
			}
			//执行查询并返回结果集
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//返回结果集
		return rs;

	}
   
}