package com.webSpider.model;

public class WuAi {

	private String title;
	private String url;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "WuAi [title=" + title + ", url=" + url + ", content=" + content.substring(0, 100) + "]";
	}
	
	
}
