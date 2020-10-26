package com.coffee.coffeeweb.test;

import com.coffee.coffeeweb.bean.PeopleBean;
import com.coffee.coffeeweb.dao.PeopleDao;
import com.coffee.coffeeweb.redis.RedisUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Test
    @Ignore
    public void contextLoads() {
    }

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PeopleDao peopleDao;

    /**
     * 操作String
     */
    @Test
    @Ignore
    public void test1() {
        //获取操作String'类型的对象
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //存储数据
        try {
            valueOperations.set("user1", "添哥");
            valueOperations.set("user2", "小添哥哥");
            System.out.println("存储数据成功");
            //获取数据
            String user1 = (String) valueOperations.get("user1");
            String user2 = (String) valueOperations.get("user2");
            System.out.println(user1 + "==>" + user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 操作list
     */
    @Test
    @Ignore
    public void test2() {

        ListOperations operations = redisTemplate.opsForList();

        Long num = operations.leftPushAll("l1", "l11", "l22", "l33", "l44");
        System.out.println("插入数据个数===》" + num);
        //取值
        List list = operations.range("l1", 0, -1);
        for (Object o : list) {
            System.out.println("list---》" + o);
        }

    }

    /**
     * 操作set
     */
    @Test
    @Ignore
    public void test3() {

        SetOperations operations = redisTemplate.opsForSet();

        Long num = operations.add("s1", "s11", "s22", "s33", "s44", "s11", "s22", "s55");
        System.out.println(num);
        //获取
        Set set = operations.members("s1");
        for (Object o : set) {
            System.out.println(o);
        }
    }

    /**
     * 操作zset
     */
    @Test
    @Ignore
    public void test4() {
        ZSetOperations operations = redisTemplate.opsForZSet();
        operations.add("z1", "z10", 100.00);
        operations.add("z1", "z20", 200.00);
        operations.add("z1", "z5", 50.00);
        //获取值
        Set set = operations.range("z1", 0, -1);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    /**
     * 操作Hash
     */
    @Test
    @Ignore
    public void test5() {
        HashOperations operations = redisTemplate.opsForHash();
        //存储学生1
        operations.put("stu:1:info", "name", "小添");
        operations.put("stu:1:info", "classRoom", "快刀班");
        //存储学生2
        HashMap<String, Object> map = new HashMap<>();
        //添加学生信息
        map.put("name", "晓添");
        map.put("classRoom", "吃瓜班");
        operations.putAll("stu:2:info", map);

        //获取数据1
        Object name = operations.get("stu:1:info", "name");
        Object classRoom = operations.get("stu:1:info", "classRoom");
        System.out.println("学生1==》" + name + "==>" + classRoom);
        //获取数据2
        Map entries = operations.entries("stu:2:info");
        //遍历map
        Set set = entries.keySet();
        for (Object o : set) {
            System.out.println(o + "==>" + entries.get(o));
        }
    }

    /**
     * 对key的操作
     */
    @Test
    @Ignore
    public void test6() {
        //判断key是否存在
        Boolean hasKey = redisTemplate.hasKey("stu:2:info");
        System.out.println("stu:2:info===>" + hasKey);
        //设置过期时间,k3键10秒过期
        redisTemplate.expire("k3", 10, TimeUnit.SECONDS);
        //设置l1键在2020 5 13 7点过期
        redisTemplate.expireAt("l1", new Date(2020, 5, 13, 7, 00, 00));
    }


    //下方两个测试是使用RedisUtil中封装使用boundHashOps方式操作
    @Test
    public void t1() {
        PeopleBean peopleBean = new PeopleBean();
        List<PeopleBean> peopleBeanList = peopleDao.findAll();
        for(PeopleBean p:peopleBeanList){
            redisUtil.putSome(PeopleBean.class, "people", String.valueOf(p.getId()), p);
        }

    }

    @Test
    @Ignore
    public void t2() {
        List<PeopleBean> studentList = new ArrayList<>();
        Cursor<Map.Entry<String, Object>> peoples = redisUtil.getSome("people", PeopleBean.class);
        while (peoples.hasNext()) {
            Map.Entry<String, Object> next = peoples.next();
            PeopleBean student1 = (PeopleBean) next.getValue();
            studentList.add(student1);
        }
        for (PeopleBean student1 : studentList) {
            System.out.println(student1);
        }
    }
}


    
