package com.coffee.coffeeweb.service.impl;

import com.coffee.coffeeweb.bean.PeopleBean;
import com.coffee.coffeeweb.dao.PeopleDao;
import com.coffee.coffeeweb.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService {


    @Override
    public PeopleBean list(PeopleBean peopleBean) {
        return peopleBean;
    }
}
