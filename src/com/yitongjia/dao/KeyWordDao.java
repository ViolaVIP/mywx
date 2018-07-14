package com.yitongjia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yitongjia.model.KeyWord;
import com.yitongjia.util.DBUtil;
/**
 * 关键词回复
 *@author bym @date 2018年7月4日
 */
@Repository
public class KeyWordDao {
	static Connection conn=null;
    static PreparedStatement pstmt=null;
    static ResultSet rs=null;
    public static KeyWord findKeyWord(String keyword) {
		String sql="select * from KeyWord where keyword like ? ";
		Object []params={ keyword};
		List<KeyWord> kw=findBySQL(sql,params);
		if(kw==null){
		return null;
		}
		return kw.get(0);
	}
	
	public static List<KeyWord> findBySQL(String sql,Object[]params){
		List<KeyWord> KeyWords=new ArrayList<KeyWord>();
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
		while(rs.next()){
		KeyWord kw =new KeyWord(rs.getString("keyword"),rs.getString("content"));
		KeyWords.add(kw);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.closeAll();
		}
		return KeyWords;
		}
}
