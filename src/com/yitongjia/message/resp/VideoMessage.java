package com.yitongjia.message.resp;

/**
 * 视频消息
 * @author bym @date 2018年7月4日
 */
public class VideoMessage extends BaseMessage {
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
