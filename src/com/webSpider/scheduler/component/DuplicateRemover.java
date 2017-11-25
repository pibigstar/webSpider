package com.webSpider.scheduler.component;

import com.webSpider.base.Request;
import com.webSpider.base.Task;


public interface DuplicateRemover {

    public boolean isDuplicate(Request request, Task task);

 
    public void resetDuplicateCheck(Task task);

    
    public int getTotalRequestsCount(Task task);

}
