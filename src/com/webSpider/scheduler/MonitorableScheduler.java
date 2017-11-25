package com.webSpider.scheduler;

import com.webSpider.base.Task;

public interface MonitorableScheduler extends Scheduler {

    public int getLeftRequestsCount(Task task);

    public int getTotalRequestsCount(Task task);

}