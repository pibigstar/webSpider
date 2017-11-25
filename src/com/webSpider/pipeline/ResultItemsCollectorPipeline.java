package com.webSpider.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.webSpider.base.ResultItems;
import com.webSpider.base.Task;


public class ResultItemsCollectorPipeline implements CollectorPipeline<ResultItems> {

    private List<ResultItems> collector = new ArrayList<ResultItems>();

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {
        collector.add(resultItems);
    }

    @Override
    public List<ResultItems> getCollected() {
        return collector;
    }
}
