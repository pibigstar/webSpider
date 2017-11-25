package test;

import java.util.List;

import com.webSpider.Spider;
import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.processor.PageProcessor;
import com.webSpider.selector.Selectable;
/**
 * 吾爱破解热门帖子爬取
 * @author pibigstar
 */
public class WuAiProcessor implements PageProcessor{
	private Config config = Config.me().addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)").setRetryTimes(5).setSleepTime(5000).setTimeOut(10000);
	@Override
	public void process(Page page) {
		//page.addTargetRequest("page.getHtml().css("#ct > div > div:nth-child(5) > div > a.nxt").links().toString());
		List<Selectable> nodes = page.getHtml().css(".bm_c > table > tbody").nodes();
		for (Selectable node : nodes) {
			System.out.println(node.css("tr > th > a").text());
			System.out.println(node.css("tr > th > a").links().toString());
		}
		
		
	}

	@Override
	public Config getSite() {
		return config;
	}

	public static void main(String[] args) {
		Spider.create(new WuAiProcessor()).addUrl("https://www.52pojie.cn/forum.php?mod=guide&view=hot").thread(3).run();
	}
}
