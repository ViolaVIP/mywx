package com.yitongjia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.yitongjia.model.User;
import com.yitongjia.util.DBUtil;
/**
 * 用户信息
 * @author bym @date 2018年7月4日
 *
 */


@Repository
public class UserDao {
	
		static Connection conn=null;
	    static PreparedStatement pstmt=null;
	    static ResultSet rs=null;	   
//插入用户信息
	public static void saveUser(String openid){
		   String sql="insert into user(openid,status) values (?,1)";
	        try {
	            conn=DBUtil.getConnection();
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1, openid);
	          
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally{
	            DBUtil.closeAll();
	        }
	}
//更新用户信息	
	public static void updateUser(String openid){
		String sql="update user set status ='2' where openid=?";
        try {
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, openid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeAll();
        }
	}
	public static void delUser(String openid) {
		String sql="delete from user where openid=?";
        try {
            conn=DBUtil.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, openid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeAll();
        }
		
	}
	public static ResultSet checkOpenid(String openid) {
		String sql="select * from user where openid =?";
		 try {
	            conn=DBUtil.getConnection();
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1, openid);
	          
	            rs=pstmt.executeQuery();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally{
	            DBUtil.closeAll();
	        }
		 return rs;
	}
	public static User findUser(String openid) {
		String sql="select * from user where openid =?";
		Object []params={openid};
		List<User> u=findBySQL(sql,params);
		if(u==null){
		return null;
		}else{
			return u.get(0);
		}	

	}
	
	public static List<User> findBySQL(String sql,Object[]params){
		List<User> Users=new ArrayList<User>();
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
		while(rs.next()){
		User User=new User(rs.getString("openid"),rs.getInt("status"));
		Users.add(User);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.closeAll();
		}
		return Users;
		}
	
}
