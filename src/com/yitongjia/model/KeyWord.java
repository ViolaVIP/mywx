package com.yitongjia.model;
/**
 * 
 * @author bym @date 2018年7月4日
 *
 */
public class KeyWord {
private Integer id;
private String keyword;
private String content;
public KeyWord(String keyword, String content) {
	super();
	this.keyword = keyword;
	this.content = content;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getKeyWord() {
	return keyword;
}
public void setKeyWord(String keyWord) {
	this.keyword = keyWord;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

}
