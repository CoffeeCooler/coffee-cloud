package com.coffee.spider.code.code.population;

import cn.dreampie.orm.Record;
import com.coffee.httpclientutil.HttpClientUtil;
import com.coffee.httpclientutil.common.HttpConfig;
import com.coffee.spider.service.DataAcquire;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorldPopulation implements DataAcquire {


    @Override
    public void execute() {
        String table="tb_base_population";
        HttpConfig httpConfig = HttpConfig.custom();
        httpConfig.encoding("utf-8");
        int i=1;
        try {
            do {
                if(i==1){
                    httpConfig.url("https://web.phb123.com/city/renkou/rk.html");
                }else{
                    httpConfig.url("https://web.phb123.com/city/renkou/rk_"+i+".html");
                }
                String html = HttpClientUtil.post(httpConfig);
                Document document = Jsoup.parse(html, "https://web.phb123.com");
                Elements trs = document.select(".tb-box .rank-table tbody tr");
                for (Element tr : trs) {
                    Elements tds = tr.select("td");
                    if(tds.size()==0){
                        continue;
                    }
                    // 存储数据
                    Record record = new Record(table);
                    record.reNew();
                    record.set("id", System.currentTimeMillis())
                            .set("rank", tds.get(0).text())
                            .set("country", tds.get(1).text())
                            .set("population", NumberUtils.toInt(StringUtils.replace(tds.get(2).text(),",","")))
                            .set("increase_rate", NumberUtils.toDouble(StringUtils.replace(tds.get(3).text(),"%",""))/100)
                            .set("population_density", tds.get(4).text())
                            .set("date",new Date());
                    record.save();
                }

                if(i>50){
                    break;
                }
                i++;
            }while (Boolean.TRUE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
