package com.coffee.generator;

import com.coffee.database.JdbcUtil;
import com.coffee.generator.base.GenericGenerator;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class IDCardGenerator implements GenericGenerator {

    public IDCardGenerator() {
    }

    static int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2}; // 十七位数字本体码权重
    static char[] validate = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; // mod11,对应校验码字符值

    static final Map<String, String> map =IDCardGenerator.getAreaCode();

    // 得到校验码
    public static char getCheckoutCode(String Idnumber17) {
        int sum = 0; // wi*Ai和
        int mod = 0; // 进行模11运算
        try {
            for (int i = 0; i < 17; i++) {
                sum += Integer.parseInt(String.valueOf(Idnumber17.charAt(i)))
                        * weight[i];
            }
        } catch (Exception e) {
            return 'a';
        }
        mod = sum % 11;// 进行模11运算
        return validate[mod];// 返回校验码
    }

    public boolean validateIDCard(String idCard) {
        String idnumber17 = StringUtils.substring(idCard, 0, 17);
        String checkCode = String.valueOf(getCheckoutCode(idnumber17));
        String idCheckCode = StringUtils.substring(idCard, 17);
        return StringUtils.equals(checkCode, idCheckCode);
    }

    @Override
    public String generate() {
        String areaCode = map.keySet().toArray(new String[0])[RandomUtils
                .nextInt(0, map.size())];

        String birthday = new SimpleDateFormat("yyyyMMdd").format(randomDate());
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode, birthday, randomCode);
    }

    public static String generate(Date birthdayAgs) {
        String areaCode = map.keySet().toArray(new String[0])[RandomUtils
                .nextInt(0, map.size())];

        String birthday = new SimpleDateFormat("yyyyMMdd").format(birthdayAgs);
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode, birthday, randomCode);
    }

    public static String generate(Date startDate, Date endDate) {
        long chosenDate = RandomUtils.nextLong(startDate.getTime(), endDate.getTime());
        String areaCode = map.keySet().toArray(new String[0])[RandomUtils
                .nextInt(0, map.size())];

        String birthday = new SimpleDateFormat("yyyyMMdd").format(new Date(chosenDate));
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode, birthday, randomCode);
    }

    public static String generate(Date birthday, String areaCode) {
        String birthday1 = new SimpleDateFormat("yyyyMMdd").format(birthday);
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode, birthday1, randomCode);
    }

    public static String generate(String areaCode) {
        String birthday = new SimpleDateFormat("yyyyMMdd").format(randomDate());
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode, birthday, randomCode);
    }

    public static String generate(Date birthday, String areaCode, String sex) {
        if (areaCode == null) {
            areaCode = map.keySet().toArray(new String[0])[RandomUtils
                    .nextInt(0, map.size())];
        }
        String birthday1 = new SimpleDateFormat("yyyyMMdd").format(birthday);
        String randomCode = null;
        int randomInt = 1000 + RandomUtils.nextInt(0, 998);
        if (StringUtils.equals(sex, "男")) {
            if (randomInt % 2 == 1) {
                randomCode = String.valueOf(randomInt).substring(1);
            } else {
                randomCode = String.valueOf(randomInt + 1).substring(1);
            }
        } else {
            if (randomInt % 2 == 1) {
                randomCode = String.valueOf(randomInt + 1).substring(1);
            } else {
                randomCode = String.valueOf(randomInt).substring(1);
            }
        }
        return getIdCardResult(areaCode, birthday1, randomCode);
    }

    public static String generate(Date birthdayStart, Date birthdayEnd, String areaCode, String sex) {
        long chosenDate = RandomUtils.nextLong(birthdayStart.getTime(), birthdayEnd.getTime());
        String birthday = new SimpleDateFormat("yyyyMMdd").format(new Date(chosenDate));
        String randomCode = null;
        int randomInt = 1000 + RandomUtils.nextInt(0, 998);
        if (StringUtils.equals(sex, "男")) {
            if (randomInt % 2 == 1) {
                randomCode = String.valueOf(randomInt).substring(1);
            } else {
                randomCode = String.valueOf(randomInt + 1).substring(1);
            }
        } else {
            if (randomInt % 2 == 1) {
                randomCode = String.valueOf(randomInt + 1).substring(1);
            } else {
                randomCode = String.valueOf(randomInt).substring(1);
            }
        }
        return getIdCardResult(areaCode, birthday, randomCode);
    }

    public static Date randomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 1, 1);
        long earlierDate = calendar.getTime().getTime();
        long laterDate = new Date().getTime();
        long chosenDate = RandomUtils.nextLong(earlierDate, laterDate);
        return new Date(chosenDate);
    }

    static String getIdCardResult(String areaCode, String birthday, String randomCode) {
        String pre = areaCode + birthday + randomCode;
        String verifyCode = String.valueOf(getCheckoutCode(pre));
        return pre + verifyCode;
    }

    public static Map<String, String> getAreaCode() {
        final Map<String, String> map = Maps.newHashMap();
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement preState = null;
        try {
            preState = conn.prepareStatement("select distinct  code,name from area_his");
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                map.put(String.valueOf(rs.getInt("code")),rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}

