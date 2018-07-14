package com.yitongjia.message.resp;

/**
 * 图片消息
 * @author bym @date 2018年7月4日
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
