package com.webSpider.processor;

import java.util.List;

import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.utils.UrlUtils;


 
public class SimplePageProcessor implements PageProcessor {

    private String urlPattern;

    private Config site;

    public SimplePageProcessor(String startUrl, String urlPattern) {
        this.site = Config.me().addStartUrl(startUrl).
                setDomain(UrlUtils.getDomain(startUrl));
        //compile "*" expression to regex
        this.urlPattern = "(" + urlPattern.replace(".", "\\.").replace("*", "[^\"'#]*") + ")";

    }

    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(urlPattern).all();
        page.addTargetRequests(requests);
        page.putField("title", page.getHtml().xpath("//title"));
        page.putField("html", page.getHtml().toString());
        page.putField("content", page.getHtml().smartContent());
    }

    @Override
    public Config getSite() {
        return site;
    }
}
