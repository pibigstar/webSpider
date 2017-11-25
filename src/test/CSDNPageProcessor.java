package test;

import com.webSpider.Spider;
import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.processor.PageProcessor;

/**
 * CSDN博客爬取
 * @author pibigstar
 *
 */
public class CSDNPageProcessor implements PageProcessor {

	private Config config = Config.me().addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)").setRetryTimes(3).setSleepTime(1000).setTimeOut(5000);
	
	@Override
	public void process(Page page) {
		//page.addTargetRequests(page.getHtml().links().regex("http://www.cnblogs.com/acemonster/p/\\w*.html").all());
		System.out.println(page.getHtml().toString());
	}

	@Override
	public Config getSite() {
		
		return config;
	}

	public static void main(String[] args) {
		Spider.create(new CSDNPageProcessor()).addUrl("http://www.cnblogs.com/acemonster/p/7881083.html").thread(1).run();
	}
}
