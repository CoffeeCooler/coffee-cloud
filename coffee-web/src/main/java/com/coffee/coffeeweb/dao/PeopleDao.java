package com.coffee.coffeeweb.dao;

import com.coffee.coffeeweb.bean.PeopleBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleDao extends JpaRepository<PeopleBean,Long> {

    public PeopleBean list(PeopleBean peopleBean);
}
