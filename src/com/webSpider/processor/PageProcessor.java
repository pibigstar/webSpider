package com.webSpider.processor;

import com.webSpider.base.Config;
import com.webSpider.base.Page;


public interface PageProcessor {

    public void process(Page page);

   
    public Config getSite();
}
