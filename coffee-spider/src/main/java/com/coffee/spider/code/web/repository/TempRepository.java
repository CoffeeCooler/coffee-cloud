package com.coffee.spider.code.web.repository;

import com.coffee.spider.code.web.entity.TbBasePopulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<TbBasePopulation,Long> {

}
