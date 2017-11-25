package com.webSpider.pipeline;

import com.webSpider.base.ResultItems;
import com.webSpider.base.Task;

/**
 * 输出和持久化的接口
 *
 * @author pibigstar
 * @see ConsolePipeline
 * @see FilePipeline
 */
public interface Pipeline {

    /**
     * Process extracted results.
     *
     * @param resultItems resultItems
     * @param task task
     */
    public void process(ResultItems resultItems, Task task);
}
