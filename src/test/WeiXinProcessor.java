package test;

import java.util.List;

import com.webSpider.Spider;
import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.downloader.HttpClientDownloader;
import com.webSpider.model.WeiXin;
import com.webSpider.processor.PageProcessor;
import com.webSpider.selector.Html;
import com.webSpider.selector.Selectable;
/**
 * 微信精选文章爬取
 * @author pibigstar
 *
 */
public class WeiXinProcessor implements PageProcessor{
	private Config config = Config.me().addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)")
								.setRetryTimes(5)
								.setSleepTime(5000)
								.setTimeOut(10000);
	@Override
	public void process(Page page) {
		//定义下一页的地址
		String next = page.getHtml().css("body > div:nth-child(2) > div:nth-child(2) > div > div > div > div > div.main.e_col.w4_5.main_col > div > div > div > div.w4_5 > a:nth-child(3)").links().toString();
		page.addTargetRequest(next);
		
		//解析内容
		List<Selectable> nodes = page.getHtml().css("body > div:nth-child(2) > div:nth-child(2) > div > div > div > div > div.main.e_col.w4_5.main_col > div > div > div > div.feed_body > div > div > div > div.home_feed_item_row > div > div > h2").nodes();
		//遍历结点
		for (Selectable node : nodes) {
			WeiXin weiXin = new WeiXin();
			weiXin.setTitle(node.text());
			weiXin.setUrl(node.links().toString());
			weiXin.setContent(getContent(node.links().toString()));
			System.out.println(weiXin);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Config getSite() {
		return config;
	}
	
	public static void main(String[] args) {
		Spider.create(new WeiXinProcessor()).addUrl("http://chuansong.me?start=25").thread(3).run();
	}
	
	/**
	 * 获得详细内容
	 * @param url
	 * @return
	 */
	public String getContent(String url) {
		HttpClientDownloader downloader = new HttpClientDownloader();
		Html html = downloader.download(url);
		return html.css("#img-content").text();
	}

}
