package com.yitongjia.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.AbstractDocument.Content;

import com.yitongjia.dao.UserDao;
import com.yitongjia.message.resp.Article;
import com.yitongjia.message.resp.CustomMessage;
import com.yitongjia.message.resp.NewsMessage;
import com.yitongjia.message.resp.TextMessage;
import com.yitongjia.model.User;
import com.yitongjia.util.MessageUtil;

/**
 * 
 * @author bym @date 2018年7月4日
 */
public class CoreService {
	/** 生成订阅时的图文消息
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @return */
	public static String processRequest(HttpServletRequest request) {
	
		String respXml = null;
	
		String respContent = "请求处理异常，请稍候尝试！";
		try {
			
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			String content = requestMap.get("Content");
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//利空客服回复,回复多条消息
				/*CustomMessage custom=new CustomMessage();
		         custom.setToUserName(fromUserName);
		         custom.setFromUserName(toUserName);
		         custom.setCreateTime(new Date().getTime());
		         custom.setMsgType("transfer_customer_service");
		          if("custom".equals(content)){
		        	  return MessageUtil.customMessageToXml(custom);  
		          }
		         */

				/**
				 * 从数据库获取关键词的方式
				 */
			/*	KeyWord kw=KeyWordDao.findKeyWord(content);
				if(kw!=null){
					textMessage.setContent(kw.getContent());
				}else{
					User user= UserDao.findUser(fromUserName);
			    	if(user.getStatus()==1){
			    		textMessage.setContent("您好,"+fromUserName+"\n我是:"+toUserName+"\n您发送的消息类型为:"+msgType+
								"\n您发送的时间为:"+textMessage.getCreateTime()+"\n我回复的时间为:"+textMessage.getCreateTime()+
								"您发送的内容是:"+content);
			    		textMessage.setContent("咨询、投诉、投稿、提意见？客官请慢等，小统一般在线时间为周一至周五9:00-18:00，未能及时回复请见谅，您可以先留言，小统得到消息将第一时间回复！");
						respContent=textMessage.getContent();
						UserDao.updateUser(fromUserName);
			    	}else{
			    		return "";
			    	}
				}
				
				respXml = MessageUtil.messageToXml(textMessage);*/
				
				 /*     respContent = "您发送的是文本消息！"; */
				  
	                // 接收用户发送的文本消息内容  
	               // String content = requestMap.get("Content");  
	  
	                // 创建图文消息  
	                NewsMessage newsMessage = new NewsMessage();  
	                newsMessage.setToUserName(fromUserName);  
	                newsMessage.setFromUserName(toUserName);  
	                newsMessage.setCreateTime(new Date().getTime());  
	                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                // newsMessage.setFuncFlag(0);  
	  
	                List<Article> articleList = new ArrayList<Article>();  
	             /*   // 单图文消息  
	               if ("a".equals(content)) {  
	                    Article article = new Article();  
	                    article.setTitle("微信公众帐号开发教程Java版");  
	                    article.setDescription("JAVA程序员广义上是指一群以JAVA为谋生手段的软件开发人员。狭义的说，是指拥有SUN公司JAVA认证的程序员。Sun Java认证分为两个级别：Sun 认证Java程序员和Sun 认证Java开发员。通常要求程序员精通java基础，java高级编程，及常用java设计模式，并深入理解mvc编程模式，了解uml相关知识!！");  
	                    article.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");  
	                    article.setUrl("http://blog.csdn.net/lyq8479?toUserName=" + fromUserName + "&toUserName="  
	                                    + toUserName);  
	                    articleList.add(article);  
	                    // 设置图文消息个数  
	                    newsMessage.setArticleCount(articleList.size());  
	                    // 设置图文消息包含的图文集合  
	                    newsMessage.setArticles(articleList);  
	                    // 将图文消息对象转换成xml字符串  
	                    respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
	                }
	               // 单图文消息---不含图片
	               else if ("b".equals(content)) {
						Article article = new Article();
						article.setTitle("微信公众帐号开发教程Java版");
						// 图文消息中可以使用QQ表情、符号表情
						article.setDescription("柳峰，80后，" + emoji(0x1F6B9)
								+ "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
						// 将图片置为空
						article.setPicUrl("");
						article.setUrl("http://blog.csdn.net/lyq8479");
						articleList.add(article);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}
					// 多图文消息
					else if ("c".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("微信公众帐号开发教程\n引言");
						article1.setDescription("");
						article1.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");
	 
						Article article2 = new Article();
						article2.setTitle("第2篇\n微信公众帐号的类型");
						article2.setDescription("");
						article2.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");
	 
						Article article3 = new Article();
						article3.setTitle("第3篇\n开发模式启用及接口配置");
						article3.setDescription("");
						article3.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");
	 
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}
					// 多图文消息---首条消息不含图片
					else if ("d".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("微信公众帐号开发教程Java版");
						article1.setDescription("");
						// 将图片置为空
						article1.setPicUrl("");
						article1.setUrl("http://blog.csdn.net/lyq8479");
	 
						Article article2 = new Article();
						article2.setTitle("第4篇\n消息及消息处理工具的封装");
						article2.setDescription("");
						article2.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");
	 
						Article article3 = new Article();
						article3.setTitle("第5篇\n各种消息的接收与响应");
						article3.setDescription("");
						article3.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");
	 
						Article article4 = new Article();
						article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
						article4.setDescription("");
						article4.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");
	 
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}
					// 多图文消息---最后一条消息不含图片
					else if ("e".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("第7篇\n文本消息中换行符的使用");
						article1.setDescription("");
						article1.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");
	 
						Article article2 = new Article();
						article2.setTitle("第8篇\n文本消息中使用网页超链接");
						article2.setDescription("");
						article2.setPicUrl("http://img1.yitongjia.com/file/image/1947266642/b9fd1703f59ccf60ba19a824adf53c0a.jpg");
						article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");
	 
						Article article3 = new Article();
						article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
						article3.setDescription("");
						// 将图片置为空
						article3.setPicUrl("");
						article3.setUrl("http://blog.csdn.net/lyq8479");
	 
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;*/
	             
						// 多图文消息
						if ("样板间".contains(content)) {
							Article article1 = new Article();
							article1.setTitle("【一统样板间实例】南京碧桂园如山湖城别墅 奏响美式古典家具的大气之歌");
							article1.setDescription("");
							article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDfibYKBcbCutGnibaZGc8zpAR6sZibYOUhqXPicSXbVXOetgbzeBXvJyPJnpueW1ESkHyHLySh5KUghBDA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
							article1.setUrl("https://mp.weixin.qq.com/s/d6FLUwU84FYQifSOACFcag");
		 
							Article article2 = new Article();
							article2.setTitle("南京仙林美林墅样板间 法式的浪漫娓娓道来");
							article2.setDescription("");
							article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDf90icTZ386zZDicH4718yXub3JJywfDxbrFWT4pEN8jaayVzYAFKE0IMZVtBEktiaHnn7TR45KtWRGRA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
							article2.setUrl("https://mp.weixin.qq.com/s/4Zj0EpVotHijICcU7tCxkA");
		 
							Article article3 = new Article();
							article3.setTitle("南昌博泰生命树别墅 演绎美式宫廷的尊贵与奢华");
							article3.setDescription("");
							article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDf9D9VAwXMTvI03AsSI6rA4SoAttIe1KFic04F9FdsXZicNDvpicRBRcGzV6xlYbJDZPfbam6OFLnov2A/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
							article3.setUrl("https://mp.weixin.qq.com/s/FF6TfaMriK1NapwZtB1SVw");
		 
							Article article4 = new Article();
							article4.setTitle("淡雅空间里的法式风 吹出浪漫悠闲的生活节奏");
							article4.setDescription("");
							article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDficpVrG7APJ4R4sjia2RFzwatyiasEDicOqlXiakXqs9XNrxpESiaQElv6hhbzd5Zxy4rAL1NA4DOf1IcMQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
							article4.setUrl("https://mp.weixin.qq.com/s/GjZrUuESOMz6Pj0MJyikeA");
							
							Article article5 = new Article();
							article5.setTitle("郑州万达公馆业主美家 绿意盎然的法式优雅");
							article5.setDescription("");
							article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDficTAyK2x5FE2b7UEzgCM04UhsrlYsRvibHDK5d8l4pRvrAJicPYgbsl2hscSkP7ms0mRKZqHniaIODOw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
							article5.setUrl("https://mp.weixin.qq.com/s/PvrCNKNwXZrcRqIjEsrKGg");
							
							Article article6 = new Article();
							article6.setTitle("杭州华盛达阅城90平居室 书写化繁为简的美洲故事");
							article6.setDescription("");
							article6.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDfibYMCX6xfriaLqQDc82VedGRHk5I0HT81elVEnEzTEawIJXxbdgfA87aYdpYiadwDgS4a1n8ULIBX2A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
							article6.setUrl("https://mp.weixin.qq.com/s/uXlDga6c_Omu8lV0_xITlg");
							
							Article article7 = new Article();
							article7.setTitle("业主爱家中式海棠木 浸染朴实清雅的美");
							article7.setDescription("");
							article7.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDf9MtwiaAMkWo6eTmh8CrIzOMspia3E6GRkkEpF8qJxJ0TAPl0X8RsmndaWziac6dUfknvl0tgPc2p89g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
							article7.setUrl("https://mp.weixin.qq.com/s/Th3CFjuR3YOYsuVLZ4pKbw");
								
							articleList.add(article1);
							articleList.add(article2);
							articleList.add(article3);
							articleList.add(article4);
							articleList.add(article5);
							articleList.add(article6);
							articleList.add(article7);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);
							respContent = MessageUtil.messageToXml(newsMessage);
							return respContent;
					}else if ("整装定制, 厨房, 客厅, 卧室, 书房, 餐厅, 背景墙, 定制".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("一统国际家居定制样板间实例-整装定制·厨房、卧室");
						article1.setDescription("");
						article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf9wOpicECL05VnxOn9G8kBkngiaDhGc69pk1iamLhdibyyapmWLjibiaVvUI8LW8P6oUybkGt9R8iaSnT3sg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s?__biz=MzAxODMzODE5OQ==&tempkey=OTY0X2swVFR2YVhuM0xJZlZ2UkFuZ1N2R0VkemJ6UE9KcFY3UFQwT05zdkxhbXFWa3N6VG1LNXhoSUlCMFotRzJvaXBoa1pqVTRMeTVBaWRUVUl5Unl2bDliN0hseV9OQWRrdEpBZ3pBNkhfazVfMXNYZjBKYVB0enFIUElqejhYX1E1REtSQTBhRDcybkp2RFVVLThTb1pTeVYwVGpwTDREakNZeXRISUF%2Bfg%3D%3D&chksm=0030beb1374737a70d96e3d79b3bee244bbe25a984711b80f7692647149ee3dbc72ad32e44fc#rd");
	 
						Article article2 = new Article();
						article2.setTitle("一统国际家居定制样板间实例-整装定制·厨房、餐厅");
						article2.setDescription("");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8K4jMaXicTw5846rIzxtHtWVaoEnE6PJP19IIYjHfcvVY60Alezr9EF5L5upp5ThZthRMIUCf1o8w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("https://mp.weixin.qq.com/s/_Yo6NFM95kXA8kL_9n86dQ");
	 
						Article article3 = new Article();
						article3.setTitle("一统国际家居定制样板间实例-整装定制·背景墙");
						article3.setDescription("");
						article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf9QNDfDEXCMN3MuRB4HyUopsJUuHtUKStvQROUTEhcMqx6nncibtJxlgAYwRwnJujDUsKwjsIr06CQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("https://mp.weixin.qq.com/s/eaSg5B-wKRQv3Ancl28fAg");
	 
						Article article4 = new Article();
						article4.setTitle("一统国际家居定制样板间实例-整装定制·客厅、餐厅");
						article4.setDescription("");
						article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8sUzm70CibRwQia9bBaC6cBQ3DEv9VybqNF8icCbLuK0tQicpcX6YM7jW4HpTLQQknuywIaUIgvWHOow/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("https://mp.weixin.qq.com/s/lt-EFemlTNiLXcA_5SIlNA");
	
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}else if ("美洲故事, 美式仿古, 夏威夷, 北美生活, 美式家具, 美式古典实木, 美式新古典".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("一统国际家居美式样板间实例-美洲故事");
						article1.setDescription("");
						article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDficMCVS3Ieus8cpMEhx8ibRwJib5Gmhd9jicuvnSYhuDsYD0JAGmbsia5dvMtzYJBdFGQGHzUwG7svHHsg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s/bJYlJgiwPy3Uq69zvp8xlA");
	 
						Article article2 = new Article();
						article2.setTitle("一统国际家居美式样板间实例-美式家具");
						article2.setDescription("");
						article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDf8HfbyPlSLh5tb6fnu2YZJD3FhEuRQ9sMc9ekbNrbN2rYH313oMs3gFsSqnKoibCthn4Wy0uLAJA3A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("https://mp.weixin.qq.com/s/65QFT5vjFuo0hKZqLvf6-w");
	 
						Article article3 = new Article();
						article3.setTitle("一统国际家居美式样板间实例-美洲故事、法式纯白");
						article3.setDescription("");
						article3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDfic1zPY4fZAPLHVoclWmluVHVbdLfgl1Gk7bffQKfx7HLvpZlr6NSj2nkoVe1p1EqpalcbrKwOJgKw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("https://mp.weixin.qq.com/s/vKT9HzAAtDRRJEXGTW8cjA");
	 
						Article article4 = new Article();
						article4.setTitle("一统国际家居美式样板间实例-夏威夷");
						article4.setDescription("");
						article4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDficqFdh5KQFxiaxvls2ejOtOektnI2ChByy68fcicCZBctegdIGHFaNnl8jNealHgOwibAajicGF2DYiaug/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("https://mp.weixin.qq.com/s/LZB4dVeQtcCP6_t7oZtLmA");
						
						Article article5 = new Article();
						article5.setTitle("一统国际家居美式样板间实例-美式仿古、美洲故事");
						article5.setDescription("");
						article5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/U896Oo9XDf9CfxqYAU49eQjUfBmYJqc4OTvaMX2ek2WMpq1pMdqbia81RyrTRhMaicV3TFlv3rTxEhnjmk63qQoA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("https://mp.weixin.qq.com/s/4HDUy2VMETs0ndg3khXRBA");
						
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}else if ("法式, 法莱蒂, 法瑞琪, 法式纯白, 小白欧, 法梦娜, 田园地中海, 枫丹白露, 香榭丽, 欧式".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("一统国际家居法式样板间实例-法式纯白·欧式古典");
						article1.setDescription("");
						article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8NLlgYHowzPebNJTAqNicNsLh9cg9icfjxxB3Vqxl9SfXGUzWJwfYtIzeIfoyV2YbIPeFLIkMg8Ttw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s/UuFhWvRDZmWFdm6O5SK3IQ");
	 
						Article article2 = new Article();
						article2.setTitle("一统国际家居法式样板间实例-法式纯白·欧式古典");
						article2.setDescription("");
						article2.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDficXA051h91MD9d0VGorBNlpn0tXncyuIWo6zr8nFILW7oPjL72kgtEVn28hnPboOOfIV4qibficzZJQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("https://mp.weixin.qq.com/s/jb9bFrQgD1Jx8hVcxI8A_w");
	 
						Article article3 = new Article();
						article3.setTitle("一统国际家居法式样板间实例-法瑞琪");
						article3.setDescription("");
						article3.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8iaCs7W29J6MszwszicvGqSaXq8rBmBI4VicNT53G7glsfxeOQpqLc5ia5ddd3FVtYGzkbCktrYsYMQg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("https://mp.weixin.qq.com/s/YJ2vL8s_c4blmE_CcEcH3w");
	 
						Article article4 = new Article();
						article4.setTitle("一统国际家居法式样板间实例-法瑞琪、法梦娜");
						article4.setDescription("");
						article4.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDfibBUZFBfHGg697KO0WuObPFq7iciaeHSYChMObV0ksKMzBSFzYuzOfQkAwVMwCGCrNGBQTXhVSFefkA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("https://mp.weixin.qq.com/s/EuXRQlfochWHLwGO7O6eCg");
	
						Article article5 = new Article();
						article5.setTitle("一统国际家居法式样板间实例-法式纯白·欧式古典");
						article5.setDescription("");
						article5.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf8fLkyKMBghEAgPLPL5RlYc8oxu6wXXic2sfTRibkgiaqibqVFRm1vWoC1uqJXLia62g0XRE4Z703mT3JQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("https://mp.weixin.qq.com/s/8O-Jya4HvqpOaaJ2v9kcmg");
						
						Article article6 = new Article();
						article6.setTitle("一统国际家居法式样板间实例-法莱蒂");
						article6.setDescription("");
						article6.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf9Ne9q8yHz8OW0m2gDclVgWCEWBeiaib3hmkUPH1fIW2XOtuO2vPvt0TOtdibzReHMq7P0spyGuk89zA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article6.setUrl("https://mp.weixin.qq.com/s/jyzIIgqyPjl4txByQMwmSw");
						
						Article article7 = new Article();
						article7.setTitle("一统国际家居法式样板间实例-法梦娜");
						article7.setDescription("");
						article7.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf93TustjH2tvaPOaTgxxcJP4JYIxjB84yPaUMfuH2erj2BMKMXN8AaxXtdJ0Rv1Q0iaTmoD8IKovIw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article7.setUrl("https://mp.weixin.qq.com/s/FHSqbYrNkzcVrvfXgc_WRw");
						
						Article article8 = new Article();
						article8.setTitle("一统国际家居法式样板间实例-法莱蒂");
						article8.setDescription("");
						article8.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8E1C6IpwoNzCDLKXUFJA21xCZiaQyNv8zlo8d1ARmD76iakXDiaYib8yoo7FWA7RDxTficIZGBDJ7XpiaQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article8.setUrl("https://mp.weixin.qq.com/s/Twvb4HwmrML6XjWdjImeSg");
						
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						articleList.add(article6);
						articleList.add(article7);
						articleList.add(article8);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}else if ("意式, 意式后现代".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("一统样板间｜意式后现代：浮华生活里不落俗套的大奢雅");
						article1.setDescription("");
						article1.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/U896Oo9XDf9jAk897tL7gmuEW1h7rPmthib7T1I8egbtcGhvHlWcyEgXuPicxI19vvxGA6sge9uicUS0poD2zDLQA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s/bRxr2wNFjDk4PxskO2MBcg");
	 
						articleList.add(article1);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}else if ("中式, 刺猬紫檀, 水墨江南, 盛世梨香, 缅甸花梨, 老榆木, 乌金木, 海棠木".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("一统国际家居中式样板间实例-水墨江南·刺猬紫檀");
						article1.setDescription("");
						article1.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf810G9eLJgreNwuClZtujglaEVcoD3LP25ckF89nywuztUbcSriaXmQkia2mk38JedWmdGRqWMwia4iaQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s/X1kz41Ri228jGvBLQCclzw");
	 
						Article article2 = new Article();
						article2.setTitle("一统国际家居中式样板间实例-水墨江南·刺猬紫檀");
						article2.setDescription("");
						article2.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/U896Oo9XDf9gIG8921dZhicvIIf6emhrI8d2o49QRtmQXpF7CVcA4ZErwBicJPVW0zjHiasaoDrXhVWjEagTLNVicw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("https://mp.weixin.qq.com/s/XOuuSwS18p2Hlyrm7sW6gg");
	 
						Article article3 = new Article();
						article3.setTitle("一统国际家居中式样板间实例-盛世梨香·缅甸花梨");
						article3.setDescription("");
						article3.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/U896Oo9XDf9NzRSLERUL3WwRYwuzibXa3YiaRiby2ibAdibWwwiaDK03fsPtAXdv7KDVsVPeyzlznB5fe98ia5rxLrxDA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("https://mp.weixin.qq.com/s/U3LyOuaSMeyjdGVFepE-0g");
	 
						Article article4 = new Article();
						article4.setTitle("一统国际家居中式样板间实例-盛世梨香·缅甸花梨");
						article4.setDescription("");
						article4.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf9RsjSD95x1Y0plnTtQWlP3zhuXPhgoyeAMnsibiapfBwRMv2QcMzagFNs0mqbibspfia6HyH6HQxZxzg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("https://mp.weixin.qq.com/s/rQ1Cy08VGckc18fongdKUA");
						
						Article article5 = new Article();
						article5.setTitle("一统国际家居中式样板间实例-老榆木·明清·混搭");
						article5.setDescription("");
						article5.setPicUrl("https://mmbiz.qpic.cn/mmbiz_jpg/U896Oo9XDfibmbyKibVOKw8mo3QF2Ft3uImsqriaIsOyq8cYtYNbhrGOj2rGaibft33ibSxD0e9tF5cnlYcUmOjPh7A/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("https://mp.weixin.qq.com/s/USxxPjbvVuHGz2-_6B0Yuw");
						
						Article article6 = new Article();
						article6.setTitle("一统国际家居中式样板间实例-盛世梨香·缅甸花梨");
						article6.setDescription("");
						article6.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf9RsjSD95x1Y0plnTtQWlP3zhuXPhgoyeAMnsibiapfBwRMv2QcMzagFNs0mqbibspfia6HyH6HQxZxzg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article6.setUrl("https://mp.weixin.qq.com/s/rQ1Cy08VGckc18fongdKUA");
						
						Article article7 = new Article();
						article7.setTitle("一统国际家居中式样板间实例-乌金木等");
						article7.setDescription("");
						article7.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8K4jMaXicTw5846rIzxtHtWVaoEnE6PJP19IIYjHfcvVY60Alezr9EF5L5upp5ThZthRMIUCf1o8w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article7.setUrl("https://mp.weixin.qq.com/s/-aujBP8DiRmbPlRxaQEWxQ");
						
						Article article8 = new Article();
						article8.setTitle("一统国际家居中式样板间实例-海棠木");
						article8.setDescription("");
						article8.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8mdvIq6uDiclm67flziclu3aTo3wqYh5f8ZEfibYJGuec5knLoVyuzNWl6tAZF5mNP8Ic4yZmCY4NwA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article8.setUrl("https://mp.weixin.qq.com/s/0t8IJqOPrHaknDvqTY2jjA");
						
	
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						articleList.add(article6);
						articleList.add(article7);
						articleList.add(article8);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}else if ("地中海, 田园".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("精品赏析|迎着地中海之风 随一统家居赏圣托里尼岛之美");
						article1.setDescription("");
						article1.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDfibrLEKiaetzmdObuprkV4TVbShWicCXMtGmKoclyEoDNPkFoicD2AWz4PWVibr2Jh2scnAG1IIcT2ibQAA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s/oEZcaTVEE9bZp067KsbN0Q");
	 
						Article article2 = new Article();
						article2.setTitle("精品赏析|一统蓝色系枫丹白露 清新田园的自然归属");
						article2.setDescription("");
						article2.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf9FurvPPzHWUHb2AXu6tYghaefewY6MhxMdanG6wfowKDbfJMZ1UOMC4xjhiaFSAJborffGhE9vvibA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("https://mp.weixin.qq.com/s/PARnX0_SiedSV07XycXfzw");
	 
						Article article3 = new Article();
						article3.setTitle("精品赏析|一统国际家居韩式风，成就田园气息的小户型爱巢");
						article3.setDescription("");
						article3.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDficV3yaBQQAuV8xb5xFyWUsSsnic4IYvjt9NICa8CnibZV2OkEnNhUe7J5vWSFvxOZ92mWIdgElBOGxg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("https://mp.weixin.qq.com/s/lKPCR5PwNREL22b9TSJRkg");
	 
						Article article4 = new Article();
						article4.setTitle("设计案例| 融化在自然里，赏析8个田园风格卧室");
						article4.setDescription("");
						article4.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf8SrJYibUy3H8uvj1cB73O4yMQmumWdnFXJvicxkJHqW9WbryQNePxqWyDqu3uV9FMejtia00WySB3xA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("https://mp.weixin.qq.com/s/2C19k0bOwiF71WbFX-zzqA");
						
						Article article5 = new Article();
						article5.setTitle("设计案例|田园静谧，找寻心灵的归宿");
						article5.setDescription("");
						article5.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf9hKfWZWUZRU9I7vxGsov7sod5lpXZoFmd99GDPBA6vUfayCDOLVgY2adrSM8uRwtRV4SnvZ3OIlg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("https://mp.weixin.qq.com/s/yi4WCScWJrBqlIZqXwHf-Q");
	
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}
					else if ("美".contains(content)) {
						Article article1 = new Article();
						article1.setTitle("精品赏析|加州小镇系列家具篇 演绎一统国际家居的美式田园风");
						article1.setDescription("");
						article1.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf8Bsh2j1KvY90TU4iatEFlcKFvs9iawN096NBU0sfoDpbaicicAkLsLce9jb9MIsRsc37TMXXNicFcrsEw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article1.setUrl("https://mp.weixin.qq.com/s/oDguM-GSpL0TJD1fUeWJJw");
	 
						Article article2 = new Article();
						article2.setTitle("精品赏析|纯正美式·圣地亚哥系列餐厅套系之餐边柜de纯美");
						article2.setDescription("");
						article2.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf94O9RfErOMWj3VMfeeWvYAe4cRS8XPrB6Jib3uhM1TkdUarXvDiaETBOHia3ZrxkbdW2wv11bT1tmLQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article2.setUrl("https://mp.weixin.qq.com/s/_ZJJidcc1hSBMSig3jbOPA");
	 
						Article article3 = new Article();
						article3.setTitle("实例样板间|南京碧桂园如山湖城别墅 奏响美式古典家具的大气之歌");
						article3.setDescription("");
						article3.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDfibYKBcbCutGnibaZGc8zpAR6sZibYOUhqXPicSXbVXOetgbzeBXvJyPJnpueW1ESkHyHLySh5KUghBDA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article3.setUrl("https://mp.weixin.qq.com/s/Coo2T_88HTUMBP1wePcWZA");
	 
						Article article4 = new Article();
						article4.setTitle("实例样板间|太原星河湾样，领略美式风格的磅礴荡气");
						article4.setDescription("");
						article4.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDficCaBoYL1hiaic8SwIrSECkdESp0Bg1AjqIuCa053qH2mnmr5aN87eAxNX5zWunKNRpGJrAXAh4DcBA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article4.setUrl("https://mp.weixin.qq.com/s/50kh-XrSD9FcElsdW8S-iw");
	
						Article article5 = new Article();
						article5.setTitle("设计案例|260平复式公寓设计 美式风格里的小清新");
						article5.setDescription("");
						article5.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/U896Oo9XDf8TrJiapWsWIejIg6sM8Z54ImIHfMXTW9RsW4dV2ZmO8HUPicmzTUrldJYh1ev9pKsd37rYyoqjQHvA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1");
						article5.setUrl("https://mp.weixin.qq.com/s/zaM4yHKgxLdA6Hbh2--Slg");
						
						Article article6 = new Article();
						article6.setTitle("设计案例|90㎡简约美式家 温馨时尚的文艺范儿");
						article6.setDescription("");
						article6.setPicUrl("https://mmbiz.qpic.cn/mmbiz/U896Oo9XDf8Bsh2j1KvY90TU4iatEFlcKOIj6n2iceuIbEBZCn6hCAiac2CfS5TCN59ibqmslmUpKuX6ibqoBFTh4lQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
						article6.setUrl("https://mp.weixin.qq.com/s/oTdyoJ5r-v9r7zkwBzEH8Q");
						
						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						articleList.add(article5);
						articleList.add(article6);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
						return respContent;
					}
					else if("1".equals(content)){
				    	textMessage.setContent("一统国际家居集团总部位于北京，工厂直营体验店遍布全国大、中城市。"
				    				+ "是集家居产品的设计研发、生产制造和配套安装、服务为一体，以实木产品、品牌直营、独立卖场、物流直发为核心竞争力的大型家居集团。减掉渠道商、卖场的中间费用，直接为您让利。"+
				    				"产品覆盖欧式、美式、意式、中式、田园、现代、古典、红木、智能、儿童家具等所有风格，"
				    				+ "包含家具、木门、楼梯、实木整装定制、板式全屋定制窗帘、壁纸、饰品等全产品线。一站式家居购物，为您节省挑选时间。");
				    }else if("2".equals(content)){
				    	textMessage.setContent("一统国际家居全国限量征集“零利润”样板间，以最低价格做口碑宣传！\n"+
				    							"样板间征集要求：\n"+
				    							"1.属于参与地区分店匹配小区和匹配户型；\n"+
				    							"2.样板间必须选择在一统品牌配套3个空间以上；\n"+
				    							"3.签订样板间价格保密协议，按照软式设计师要求搭配窗帘、壁纸，允许一统家居送货后拍照；\n"+
				    							"4.达到以上要求可申请样板间折扣，且不再参加店内任何活动。\n"+
				    							"更多详情请拨打免费热线4008898666进行咨询！");
				    	
				    }else if("3".equals(content)){
				    	textMessage.setContent("点击<a href=\"http://www.yitongjia.com\">一统家居商城</a>，官网注册会员，即可领取500元现金抵价券！此等好事，机不可失，还等什么？");
				    }else if("4".equals(content)){
				    	textMessage.setContent("亲，无需购物进店就有礼品送！更多优惠活动请拨打一统官方免费热线4008180688进行咨询！记得说是官微小统推荐哦~");
				    }else if("杭州".contains(content)){
				    	textMessage.setContent("一统国际家居古墩路店地址：杭州市西湖区古墩路656号\n 联系电话：0571-58111699");
				    }else if("株洲".contains(content)){
				    	textMessage.setContent("一统国际家居新店地址：株洲市天元区湘山路大江官邸4栋商铺广汽本田4S店隔壁\n 联系电话：0731-22883658");
				    }else if("泉州, 福建".contains(content)){
				    	textMessage.setContent("一统国际家居福建省泉州市新店地址：泉州市晋江世纪大道518号宝龙城市广场二楼2043 、2044 、2050商铺一统国际家居\n 联系电话：0595 82117152");
				    }else if("潍坊".contains(content)){
				    	textMessage.setContent("地址：山东省潍坊市高新区中动大厦一至三层");
				    }else if("衡阳".contains(content)){
				    	textMessage.setContent("一统国际家居衡阳店地址：湖南省衡阳市石鼓区华源支路雅士林欣城商业街一统国际家居\n联系电话：0734-8131778");
				    }else if("宜昌".contains(content)){
				    	textMessage.setContent("一统国际家居宜昌合益店地址：宜昌市伍家岗区合益路88号\n联系电话：0717-6555762");
				    }else if("绍兴".contains(content)){
				    	textMessage.setContent("一统国际家居绍兴华城店地址：浙江省绍兴市越城区袍江世纪街华城汇A栋30-7/38-8 \n联系电话：0575-89105076");
				    }else if("宁波".contains(content)){
				    	textMessage.setContent("一统国际家居宁波传奇店地址：宁波市鄞州区中河街道麦德龙路68号传奇广场一楼\n联系电话：0574-88325858");
				    }else if("银川".contains(content)){
				    	textMessage.setContent("一统国际家居银川宝湖店地址：银川市金凤区长城路以南正源街以东宝湖海悦嘉园10号商业楼102（复式）室\n联系电话：0951-3855778");
				    }else if("湖北".contains(content)){
				    	textMessage.setContent("一统国际家居襄阳九悦店地址：襄阳市樊城区大庆西路65号九月天城1-2楼\n"+
				    							"联系电话：0710-3802930"+"\n"+
				    							"一统国际家居武汉唐家墩店地址：武汉市汉口发展大道299号顶绣晶城5栋（唐家墩苏宁旁）\n"+
				    							"联系电话：027-85609986"+"\n"+
				    							"一统国际家居新店地址：湖北省宜昌市伍家岗区合益路88号\n"+
				    							"一统国际家居新店地址：湖北省十堰市茅箭区浙江路M天下松石城\n");
				    }else if("襄阳".contains(content)){
				    	textMessage.setContent("一统国际家居襄阳九悦店地址：襄阳市樊城区大庆西路九悦天城1楼\n联系电话：0710-3802930");
				    }else if("济宁".contains(content)){
				    	textMessage.setContent("一统国际家居济宁金宇店地址：山东济宁市任城区金宇路与琵琶山路交叉口路南一统国际家居\n联系电话：15650425111");
				    }else if("芜湖,合肥,新海大道,瑶海区,安徽".contains(content)){
				    	textMessage.setContent("一统国际家居合肥新海店地址：安徽省合肥市瑶海区新海大道与当涂北路交叉口景隆现代城 \n联系电话：0551-62657135 \n"+    
				    							"一统国际家居芜湖伟星店地址：安徽省芜湖市鸠江区伟星城一期亚夏驾校对面1-8-4 "+"\n"+
				    							"联系电话：0553-8314849"+
				    							"一统国际家居合肥新海店地址：安徽省合肥市瑶海区新海大道与当涂北路交口\n联系电话：0551-62657135");
				    }else if("唐山, 龙泽".contains(content)){
				    	textMessage.setContent("一统国际家居唐山龙泽店地址：高新技术开发区龙华道北侧龙泽北路西侧（龙泽北路544号）\n联系电话：0315-5777500");
				    }else if("广西".contains(content)){
				    	textMessage.setContent("一统国际家居柳州长业店地址：广西省柳州市柳北区胜利路3号天江城2栋\n"+
				    							"联系电话：0772-2999683\n"+
				    							"一统国际家居南宁东盟店地址：南宁市青秀区东盟自由贸易区合作路9号\n"+
				    							"联系电话：0771-5386828");
				    }else if("柳州".contains(content)){
				    	textMessage.setContent("一统国际家居柳州长业店地址：广西省柳州市柳北区胜利路天江城小区2栋一统国际家居\n联系电话：0772-2999683");
				    }else if("淮安".contains(content)){
				    	textMessage.setContent("一统国际家居淮安浩源店\n"+
				    							"店面地址：江苏省淮安市水渡口大道浩源汽配城4号楼二层\n"+
				    							"电话：0517-86292979\n"+
				    							"    18252338055");
				    }else if("无锡".contains(content)){
				    	textMessage.setContent("一统国际家居无锡太湖店地址：江苏省无锡市梁溪区清扬路91-99号 \n联系电话：0510-85059919");
				    }else if("湘潭".contains(content)){
				    	textMessage.setContent("一统国际家居湘潭华夏佳园店地址：湖南省湘潭市岳塘区宝塔街道吉安路179号云盘华夏家园二期15栋0103020号\n联系电话：0731-55880706");
				    }else if("乌鲁木齐, 新疆".contains(content)){
				    	textMessage.setContent("一统国际家居乌鲁木齐德海店：乌鲁木齐新市区木材厂德海大厦一楼阿勒泰路2626号 一统国际家居\n联系方式：0991-5071333");
				    }else if("西宁, 青海".contains(content)){
				    	textMessage.setContent("一统国际家居西宁安泰店地址：青海省西宁市海湖新区五四西路文苑路西南角安泰华庭东座底商一层二层\n联系电话：0971-5115580");
				    }else if("岳阳".contains(content)){
				    	textMessage.setContent("一统国际家居岳阳巴陵店地址：岳阳市八字门巴陵东路与通海北路交叉口199号巴陵首府美年大体检中心旁边\n联系电话：0730-8279755");
				    }else if("长沙".contains(content)){
				    	textMessage.setContent("一统国际家居长沙芙蓉店地址：长沙市天心区芙蓉南路481号上林国际2栋一统国际家居\n"
				    							+"联系电话：0731—84224567\n"
				    							+"一统国际家居长沙佐拉店地址：湖南省长沙市天心区正塘坡路188号友谊综合大楼2、3楼\n"
				    							+"联系电话：0731-89800337\n"
				    							+"一统国际家居长沙新店地址：长沙市岳麓区金星南路29号中一九骏5栋尚欧国际家居\n"
				    							+"联系电话：0731-88631966");
				    }else if("宁波".contains(content)){
				    	textMessage.setContent("一统国际家居宁波传奇店地址：宁波市鄞州区麦德龙路4号传奇广场一楼\n联系电话：0574-88325858");
				    }else if("常州, 江苏".contains(content)){
				    	textMessage.setContent("一统国际家居常州环球店：江苏省常州市新北区黄河东路95号\n联系电话：0519-88998002");
				    }else if("南京".contains(content)){
				    	textMessage.setContent("一统国际家居南京万达店地址：南京市建邺区云锦路河西万达金街49号\n"
				    							+"联系电话：025-58822163\n"
				    							+"一统国际家居南京佐拉店地址：南京市雨花台区花神大道11-35佐拉国际家居\n"
				    							+"联系电话：025-84445800");
				    }else if("洛阳".contains(content)){
				    	textMessage.setContent("一统国际家居洛阳牡丹店地址：洛阳市洛龙区牡丹大道与通济街交叉口东北角 \n联系电话：18703846888");
				    }else if("郑州, 郑东新区".contains(content)){
				    	textMessage.setContent("一统国际家居郑州黄河路店地址：郑州市郑东新区商都路与黄河南路交叉口向东500米路北\n联系电话：0371-63796799");
				    }else if("遵义".contains(content)){
				    	textMessage.setContent("贵州一统国际家居遵义麓湖园店地址：贵州省遵义市播州区马家湾天池大道水果市场斜对面\n联系电话：0851-27351046");
				    }else if("贵阳".contains(content)){
				    	textMessage.setContent("贵州一统国际家居贵阳碧海店地址：贵州省贵阳市观山湖区碧海南路与林城西路交汇处（金阳沃尔玛对面，建堪大厦一楼）\n联系电话：0851-84847084");
				    }else if("青岛".contains(content)){
				    	textMessage.setContent("山东一统国际家居青岛和达店地址：青岛市市北区哈尔滨路88号和达中心城A座\n联系电话：0532-66010310");
				    }else if("山东".contains(content)){
				    	textMessage.setContent("山东一统国际家居济南泉城店地址：济南市历下区泺源大街29号圣凯财富广场一统国际家居(银座大厦对面)\n"
				    							+"联系电话：13361070720\n"
				    							+"山东一统国际家居青岛和达店地址：青岛市市北区哈尔滨路88号和达中心城A座\n"
				    							+"联系电话：0532-66010310\n"
				    							+"山东一统国际家居济宁金宇店地址：济宁市任城区金宇路与琵琶山路交汇处东南角\n"
				    							+"联系电话：15650425111\n"
				    							+"山东一统国际家居潍坊新店地址：潍坊市高新区中动大厦一至三层");
				    }else if("天津, 奥体".contains(content)){
				    	textMessage.setContent("一统国际家居天津奥体店地址：天津市南开区红旗南路106号海天国际底商一统国际家居（体育馆斜对面）\n联系电话：022-83809736");
				    }else if("福州".contains(content)){
				    	textMessage.setContent("一统国际家居福州金山店地址： 福州市仓山区金山正祥广场负一楼\n联系电话：0591-87277226");
				    }else if("济南".contains(content)){
				    	textMessage.setContent("一统国际家居济南泉城店地址：济南市历下区泺源大29号圣凯财富广场一统国际家居\n联系电话：13361070720");
				    }else if("石家庄,河北".contains(content)){
				    	textMessage.setContent("一统国际家居石家庄友谊店地址：石家庄市桥西区新石北路与友谊南大街交叉口西北角\n联系电话：0311-85135185/85135186");
				    }else if("兰州, 甘肃, 高科高新大厦".contains(content)){
				    	textMessage.setContent("一统国际家居兰州南河店地址：甘肃省兰州市城关区高新南河北路956号高科高新大厦\n联系电话：0931-8232288");
				    }else if("贵州".contains(content)){
				    	textMessage.setContent("贵州一统国际家居贵阳旗舰店地址：贵阳市观山湖区碧海南路101号一统国际家居碧海南路旗舰店\n"
				    							+"联系电话：0851-84847084\n"
				    							+"贵州一统国际家居遵义精品馆地址：贵州省遵义市播州区麓湖园二期24栋\n"
				    							+"联系电话：0851-27351046");
				    }else if("深圳, 新浩城, 宝安".contains(content)){
				    	textMessage.setContent("一统国际家居深圳新浩城店地址：深圳市福田区香蜜湖街道侨城东东路与深南大道交汇处新浩成花园裙楼1C-3\n"
				    							+"联系电话：0755-26948759\n"
				    							+"一统国际家居深圳佐拉店地址：深圳市宝安区新湖路万盛居建材广场三楼佐拉店\n"
				    							+"联系电话： 0755-32959831");
				    }else if("南宁".contains(content)){
				    	textMessage.setContent("一统国际家居南宁东盟店地址：南宁市青秀区东盟自由贸易区合作路9号\n联系电话：0771-5386828");
				    }else if("南昌, 红谷滩, 江西".contains(content)){
				    	textMessage.setContent("一统国际家居南昌红谷滩店地址：江西省南昌市红谷滩新区未来谷3c广场a馆2号门\n联系电话：0791-88287001");
				    }else if("哈尔滨, 道里区, 黑龙江, 远大购物广场".contains(content)){
				    	textMessage.setContent("一统国际家居哈尔滨远大店地址：哈尔滨市群力大道远大购物广场A栋1606号A1-8\n联系电话：0451-53923888");
				    }else if("昆明, 盘龙区, 云南".contains(content)){
				    	textMessage.setContent("一统国际家居昆明伟龙店地址：云南省昆明市盘龙区金星小区伟龙广场A幢1-2楼一统国际家居\n"
				    							+"联系电话：0871-65693251\n"
				    							+"一统国际家居昆明二店地址：昆明市西山区滇池路1045号颐庆园小区综合楼四层\n");
				    }else if("重庆, 澜湖郡, 江北".contains(content)){
				    	textMessage.setContent("一统国际家居重庆蓝湖郡店地址：重庆市渝北区北部新区金开大道蓝湖郡商业街\n"
				    							+"联系电话：023-63213512\n"
				    							+"一统国际家居重庆江北店地址：重庆市江北区黄泥磅洋河花园34.35号\n"
				    							+"联系电话：023-67863015");
				    }else if("武汉, 唐家墩, 湖北".contains(content)){
				    	textMessage.setContent("一统国际家居武汉唐家墩店地址：武汉市江汉区发展大道299号\n"
				    							+"联系电话：027-85609986\n"
				    							+"一统国际家居武昌尚欧店地址：武汉市青山区建二大厦3楼（武商众圆广场旁）\n"
				    							+"联系电话：18162306968");
				    }else if("河南".contains(content)){
				    	textMessage.setContent("一统国际家居郑州黄河路店地址：河南省郑州市郑东新区商都路和黄河南路交叉口向东200米路北\n"
				    							+"联系电话：0371-63796799\n"
				    							+"一统国际家居洛阳牡丹店地址：河南省洛阳市洛龙区金城寨街78号\n");
				    }else if("西安, 碑林区, 西安南二环, 陕西, 未央区".contains(content)){
				    	textMessage.setContent("一统国际家居西安兴庆桥店地址：西安市碑林区武警医院向东200米\n"
				    							+"联系电话：029-82230651\n"
				    							+"一统国际家居西安尚欧店地址：西安市未央区浐灞二路西段泘沱社区华东时代商场三楼\n"
				    							+"联系电话:029-89870608");
				    }else if("太原, 山西".contains(content)){
				    	textMessage.setContent("一统国际家居太原龙城店地址：太原市长治路与学府街交汇天天渔港北50m\n"
				    							+"联系电话：0351-7025601");
				    }else if("上海, 闵行".contains(content)){
				    	textMessage.setContent("一统国际家居上海闵行店地址：上海市闵行区吴中路618号吴中大厦1楼\n"
				    							+"联系电话：021-60830103");
				    }else if("沈阳, 铁西区, 北一路, 辽宁".contains(content)){
				    	textMessage.setContent("一统国际家居沈阳万达店地址：沈阳市铁西区北一路万达百货四层、五层\n"
				    							+"联系电话：024-31227100");
				    }else if("江苏".contains(content)){
				    	textMessage.setContent("南京一统国际家居南京万达店地址：南京市云锦路河西万达金街49-53号\n"
				    							+"联系电话：025-58822163\n"
				    							+"南京一统国际家居南京佐拉店地址：南京雨花台区软件大道佐拉国际家居(原上水庭院售楼处)\n"
				    							+"联系电话：025-84445800\n"
				    							+"常州一统国际家居常州环球店地址：江苏省常州市新北区黄河东路95号\n"
				    							+"联系电话：0519-88998002\n"
				    							+"淮安一统国际家居淮安浩源店地址：淮安市清江浦区水渡口大道121号浩源汽车博览城4号楼201室\n"
				    							+"联系电话：0517-86292979");
				    }else if("浙江".contains(content)){
				    	textMessage.setContent("一统国际家居宁波传奇店地址：宁波市鄞州区麦德龙路4号传奇广场一楼\n"
				    							+"联系电话：0574-88325858\n"
				    							+"一统国际家居绍兴华城店地址：越城区袍江世纪街华城汇A座30-7/38-8\n" 
				    							+"联系电话：0575-89105076");
				    }else if("成都, 青羊区, 四川".contains(content)){
				    	textMessage.setContent("一统国际家居成都文殊坊店地址：成都市青羊区草市街69号\n"
				    							+"联系电话：028-86920803");
				    }else if("湖南".contains(content)){
				    	textMessage.setContent("一统国际家居长沙芙蓉店地址：长沙市天心区芙蓉南路481号上林国际2栋一二楼（中南林科大西门南侧）\n"
				    							+"联系电话：0731—84224567\n"
				    							+"一统国际家居长沙佐拉店地址：长沙市天心区正塘坡路188号友谊综合楼2-3楼佐拉国际家居 联系电话：0731-89800337\n"
				    							+"一统国际家居岳阳巴陵店地址：岳阳市岳阳楼区八字门巴陵首府楼下199号\n"
				    							+"联系电话：0730-8279655\n"
				    							+"一统国际家居湘潭华夏家园店地址：湖南省湘潭市岳塘区宝塔街道吉安路179号云盘华夏家园二期15栋0103020号\n"
				    							+"联系电话：0731-55880706\n"
				    							+"一统国际家居新店地址：株洲市天元区湘山路大江官邸4栋商铺广汽本田4S店隔壁");
				    }else if("北京, 远大路, 西超, 十八里店, 十里河, 来广营".contains(content)){
				    	textMessage.setContent("一统国际家居北京西超市店地址：北京市海淀区远大路39-1号青清商厦一层405号\n"
				    							+"联系电话：010-52778218\n"
				    							+"一统国际家居北京十八里新店地址：北京市朝阳区十八里店北桥边四环西侧\n"
				    							+"联系电话：400-8180-688\n"
				    							+"一统国际家居北京十里河店地址：北京市朝阳区十里河大羊坊路新华国际广场C座401\n"
				    							+"联系电话：400-8180-688\n"
				    							+"一统国际家居北京来广营店地址：北京市朝阳区北五环来广营香宾路66—1号（北五环一統国际家居）\n"
				    							+"联系电话：（010）8492 0695");
				    }else if("广州, 番禺, 广东".contains(content)){
				    	textMessage.setContent("一统国际家居广州番禺店地址：广东省广州市番禺区迎宾路115号惠信商业圆一楼一统国际家具\n"
				    							+"联系电话：020-31141325/39184501");
				    }else{
				    	ResultSet rs=UserDao.checkOpenid(fromUserName);
				    	//判断用户表是否有这个用户,没有就保存
				    	if(rs.next()==false){
				    		UserDao.saveUser(fromUserName);
				    	}
				    	User user= UserDao.findUser(fromUserName);
				    	if(user.getStatus()==1){
				    		/*textMessage.setContent("您好,"+fromUserName+"\n我是:"+toUserName+"\n您发送的消息类型为:"+msgType+
									"\n您发送的时间为:"+textMessage.getCreateTime()+"\n我回复的时间为:"+textMessage.getCreateTime()+
									"您发送的内容是:"+content);*/
				    		textMessage.setContent("咨询、投诉、投稿、提意见？客官请慢等，小统一般在线时间为周一至周五9:00-18:00，未能及时回复请见谅，您可以先留言，小统得到消息将第一时间回复！");
							respContent=textMessage.getContent();
							UserDao.updateUser(fromUserName);
				    	}else{
				    		return "";
				    	}
				    }
				   return MessageUtil.messageToXml(textMessage);
			}
			
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				/*respContent = "您发送的是图片消息！";*/
				return "";
			}
	
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				/*respContent = "您发送的是音频消息！";*/
				return "";
			}
		
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				/*respContent = "您发送的是视频消息！";*/
				return "";
			}
			
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				/*respContent = "您发送的是地理位置消息！";*/
				return "";
			}
			
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				/*respContent = "您发送的是链接消息！";*/
				return "";
			}
	
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			
				String eventType = requestMap.get("Event");
			
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				textMessage.setContent("您好！一统国际家居等您好久啦！查看历史消息看精彩内容~\n"+
						"【1】了解一统\n"+
						"【2】全国限量“零利润”征集样板间\n"+
						"【3】现金抵价券在线免费领\n"+
						"【4】无需购物进店就有礼品送！更多优惠活动请拨打一统官方免费热线4008180688进行咨询！\n"+
						"亲~回复【】中数字，自动给您好看！\n"+
						"官网地址：http://www.yitongjia.com");
							respContent=textMessage.getContent();
					       UserDao.saveUser(fromUserName);
					
				}
				
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					UserDao.delUser(fromUserName);
				}
				
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					
				}
		
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					
				}
				
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				
				}
			}
			textMessage.setContent(respContent);
	
			respXml = MessageUtil.messageToXml(textMessage);
			}catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
		}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
}