package com.yitongjia.message.req;

/**
 * 文本消息
 * @author bym @date 2018年7月4日
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
