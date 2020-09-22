package com.coffee.spider.service;

public interface DataAcquire {

    void execute();

    /**
     * 根据采集规则, 执行数据采集
     *
     * @param runner
     *            表达式执行器
     * @param context
     *            变量注入器
     * @param ruleConfig
     *            采集规则
     */
    /*void execute(ExpressRunner runner, IExpressContext<String, Object> context,
                 SpiderRequestConfigBean ruleConfig);*/

}
