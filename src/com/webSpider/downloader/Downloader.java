package com.webSpider.downloader;

import com.webSpider.base.Page;
import com.webSpider.base.Request;
import com.webSpider.base.Task;

/**
 * @author pibigstar
 */
public interface Downloader {

  
    public Page download(Request request, Task task);

    public void setThread(int threadNum);
}
