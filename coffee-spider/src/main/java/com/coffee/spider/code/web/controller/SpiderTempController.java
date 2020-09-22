package com.coffee.spider.code.web.controller;

import com.coffee.spider.code.code.population.WorldPopulation;
import com.coffee.spider.code.web.repository.TempRepository;
import com.coffee.spider.code.web.entity.TbBasePopulation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "spider")
public class SpiderTempController {

    private static final Logger logger = Logger.getLogger("定制化爬虫测试controller");

    @Autowired
    private WorldPopulation worldPopulation;
    @Autowired
    private TempRepository tempRepository;


    @RequestMapping(value="ssss")
    public void excute(){
        List<TbBasePopulation> result = tempRepository.findAll();
        worldPopulation.execute();
        logger.info(result.size());
    }
}
