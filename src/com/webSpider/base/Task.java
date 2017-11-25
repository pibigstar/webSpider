package com.webSpider.base;

/**
 * 区分任务
 * @author pibigstar
 */
public interface Task {

    /**
     * unique id for a task.
     * @return uuid
     */
    public String getUUID();

    /**
     * site of a task
     *
     * @return site
     */
    public Config getSite();

}
