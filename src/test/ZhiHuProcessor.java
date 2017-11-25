package test;

import com.webSpider.Spider;
import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.processor.PageProcessor;

public class ZhiHuProcessor implements PageProcessor{
	private Config config = Config.me().addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)").setRetryTimes(5).setSleepTime(5000).setTimeOut(10000);
	@Override
	public void process(Page page) {
		String text = page.getHtml().css("#Popover-21313-83556-toggle > a").text();
		System.out.println(text);
	}

	@Override
	public Config getSite() {
		return config;
	}
	
	public static void main(String[] args) {
		//https://www.zhihu.com/topic/19550228/top-answers
		Spider.create(new ZhiHuProcessor()).addUrl("https://www.zhihu.com/people/pewu/followers").thread(1).run();
	}

}
