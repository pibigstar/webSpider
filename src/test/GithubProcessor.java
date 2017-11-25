package test;

import com.webSpider.Spider;
import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.processor.PageProcessor;

/**
 * Github内容爬取
 * @author pibigstar
 */
public class GithubProcessor implements PageProcessor {

    private Config config = Config.me().setRetryTimes(3).setSleepTime(0);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        //page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        System.out.println(page.getResultItems());
    }

    @Override
    public Config getSite() {
        return config;
    }

    public static void main(String[] args) {
        Spider.create(new GithubProcessor()).addUrl("https://github.com/jinhang").thread(5).run();
    }
}
