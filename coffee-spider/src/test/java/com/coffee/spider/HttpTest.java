package com.coffee.spider;

import com.coffee.httpclientutil.HttpClientUtil;
import com.coffee.httpclientutil.common.HttpConfig;
import com.coffee.httpclientutil.exception.HttpProcessException;

public class HttpTest {
    public static void main(String[] args) throws HttpProcessException {
        HttpConfig httpConfig = HttpConfig.custom();
        httpConfig.encoding("utf-8");
        httpConfig.url("https://web.phb123.com/city/renkou/rk.html");
        String ss = HttpClientUtil.post(httpConfig);
        System.out.println(ss);
    }
}
