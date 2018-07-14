package com.yitongjia.message.resp;

/**
 * 语音消息
 * @author bym @date 2018年7月4日
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
