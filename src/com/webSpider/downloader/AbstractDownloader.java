package com.webSpider.downloader;

import com.webSpider.base.Config;
import com.webSpider.base.Page;
import com.webSpider.base.Request;
import com.webSpider.selector.Html;

/**
 * @author pibigstar
 */
public abstract class AbstractDownloader implements Downloader {

 
    public Html download(String url) {
        return download(url, null);
    }

   
    public Html download(String url, String charset) {
        Page page = download(new Request(url), Config.me().setCharset(charset).toTask());
        return (Html) page.getHtml();
    }

    protected void onSuccess(Request request) {
    }

    protected void onError(Request request) {
    }

    protected Page addToCycleRetry(Request request, Config site) {
        Page page = new Page();
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes >= site.getCycleRetryTimes()) {
                return null;
            }
            page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
        }
        page.setNeedCycleRetry(true);
        return page;
    }
}
