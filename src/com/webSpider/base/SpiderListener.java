package com.webSpider.base;

/**
 * 监听页面抓取
 * @author pibigstar
 */
public interface SpiderListener {

    public void onSuccess(Request request);

    public void onError(Request request);
}
