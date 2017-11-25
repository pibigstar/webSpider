package com.webSpider.model;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;

public class WeiXin {
	
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
		return "WeiXin [title=" + title + ", url=" + url + ", content=" + content.substring(0, 30) + "]";
	}
	
	
}
