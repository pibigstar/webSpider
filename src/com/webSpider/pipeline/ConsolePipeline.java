package com.webSpider.pipeline;

import java.util.Map;

import com.webSpider.base.ResultItems;
import com.webSpider.base.Task;

/**
 * 结果输出到控制台
 * @author pibigstar
 */
public class ConsolePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }
    }
}
