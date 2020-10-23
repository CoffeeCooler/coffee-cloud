package threadtest.test;

import com.coffee.database.JdbcUtil;
import com.coffee.generator.ChineseNameGenerator;
import com.coffee.generator.IDCardGenerator;
import com.google.common.collect.Maps;
import com.sun.xml.internal.stream.Entity;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.*;

public class productData {

    static String[] sexArr={"男","女"};

    public static void product(PeopleBean peopleBean, Connection conn){
        String sql = "insert into people (name,sex,birthday) value(?,?,?)";
        try {
            PreparedStatement preState = conn.prepareStatement(sql);
            preState.setString(1,peopleBean.getName());
            preState.setString(2,peopleBean.getSex());
            preState.setDate(3, new Date(peopleBean.getBirthday().getTime()));
            preState.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Connection conn, Map<String, String> map){
        String sqlQ = "select * from people where idCard is null limit 100";
        String sqlU = "update people set idCard=?,signIssueOrg = ? where id=?";
        try {
            PreparedStatement preState = conn.prepareStatement(sqlQ);
            ResultSet qr = preState.executeQuery();
            while (qr.next()){
                int id= qr.getInt("id");
                Date birthday = qr.getDate("birthday");
                String sex = qr.getString("sex");
                String idCard = IDCardGenerator.generate(birthday, null, sex);
                PreparedStatement updateState = conn.prepareStatement(sqlU);
                updateState.setString(1,idCard);
                updateState.setString(2,map.get(StringUtils.substring(idCard,0,6)));
                updateState.setInt(3,id);
                updateState.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doProduct(){
        final Map<String, String> map   =IDCardGenerator.getAreaCode();
        Connection conn = JdbcUtil.getConnection();
        BlockingQueue queue = new ArrayBlockingQueue(10);
        RejectedExecutionHandler hander = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 12, 0L, TimeUnit.DAYS, queue,hander);
        ChineseNameGenerator chineseNameGenerator = new ChineseNameGenerator();
        for (int i = 0; i < 100000000; i++) {
            /*PeopleBean peopleBean = new PeopleBean();
            peopleBean.setName(chineseNameGenerator.generate());
            peopleBean.setSex(sexArr[RandomUtils.nextInt(0,2)]);
            peopleBean.setBirthday(IDCardGenerator.randomDate());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        product(peopleBean);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            threadPoolExecutor.execute(thread);*/
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        update(conn,map);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            threadPoolExecutor.execute(thread);
        }

    }


}
