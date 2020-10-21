package com.coffee.generator;

import com.coffee.generator.base.GenericGenerator;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class IDCardGenerator implements GenericGenerator {

    public IDCardGenerator() {
    }

    static int[] weight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; // 十七位数字本体码权重
    static char[] validate = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // mod11,对应校验码字符值

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

    public  boolean  validateIDCard(String idCard){
        String idnumber17 = StringUtils.substring(idCard,0,17);
        String checkCode = String.valueOf(getCheckoutCode(idnumber17));
        String idCheckCode = StringUtils.substring(idCard,17);
        return StringUtils.equals(checkCode, idCheckCode);
    }

    @Override
    public String generate() {
        Map<String, String> code = getAreaCode();
        String areaCode = code.keySet().toArray(new String[0])[RandomUtils
                .nextInt(0, code.size())]
                + StringUtils.leftPad((RandomUtils.nextInt(0, 9998) + 1) + "", 4,
                "0");

        String birthday = new SimpleDateFormat("yyyyMMdd").format(randomDate());
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode,birthday,randomCode);
    }

    public String generate(Date birthdayAgs) {
        Map<String, String> code = getAreaCode();
        String areaCode = code.keySet().toArray(new String[0])[RandomUtils
                .nextInt(0, code.size())]
                + StringUtils.leftPad((RandomUtils.nextInt(0, 9998) + 1) + "", 4,
                "0");

        String birthday = new SimpleDateFormat("yyyyMMdd").format(birthdayAgs);
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode,birthday,randomCode);
    }

    public String generate(Date startDate, Date endDate) {
        long chosenDate = RandomUtils.nextLong(startDate.getTime(),endDate.getTime());
        Map<String, String> code = getAreaCode();
        String areaCode = code.keySet().toArray(new String[0])[RandomUtils
                .nextInt(0, code.size())]
                + StringUtils.leftPad((RandomUtils.nextInt(0, 9998) + 1) + "", 4,
                "0");

        String birthday = new SimpleDateFormat("yyyyMMdd").format(new Date(chosenDate));
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode,birthday,randomCode);
    }

    public String generate(Date birthday, String areaCode) {
        String birthday1 = new SimpleDateFormat("yyyyMMdd").format(birthday);
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode,birthday1,randomCode);
    }

    public String generate(String areaCode) {
        String birthday = new SimpleDateFormat("yyyyMMdd").format(randomDate());
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
                .substring(1);
        return getIdCardResult(areaCode,birthday,randomCode);
    }

    public String generate(Date birthday, String areaCode, String sex) {
        String birthday1 = new SimpleDateFormat("yyyyMMdd").format(birthday);
        String randomCode = null;
        int randomInt = RandomUtils.nextInt(1, 998);
        if(StringUtils.equals(sex,"男")){
            if(randomInt%2==1){
                randomCode = String.valueOf(randomInt);
            }else{
                randomCode = String.valueOf(randomInt+1);
            }
        }else{
            if(randomInt%2==1){
                randomCode = String.valueOf(randomInt+1);
            }else{
                randomCode = String.valueOf(randomInt);
            }
        }
        return getIdCardResult(areaCode,birthday1,randomCode);
    }

    public String generate(Date birthdayStart, Date birthdayEnd, String areaCode, String sex) {
        long chosenDate = RandomUtils.nextLong(birthdayStart.getTime(), birthdayEnd.getTime());
        String birthday = new SimpleDateFormat("yyyyMMdd").format(new Date(chosenDate));
        String randomCode = null;
        int randomInt = RandomUtils.nextInt(1, 998);
        if(StringUtils.equals(sex,"男")){
            if(randomInt%2==1){
                randomCode = String.valueOf(randomInt);
            }else{
                randomCode = String.valueOf(randomInt+1);
            }
        }else{
            if(randomInt%2==1){
                randomCode = String.valueOf(randomInt+1);
            }else{
                randomCode = String.valueOf(randomInt);
            }
        }
        return getIdCardResult(areaCode,birthday,randomCode);
    }

    static Date randomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 1, 1);
        long earlierDate = calendar.getTime().getTime();
        long laterDate = new Date().getTime();
        long chosenDate = RandomUtils.nextLong(earlierDate, laterDate);
        return new Date(chosenDate);
    }

    static String getIdCardResult(String areaCode ,String birthday, String randomCode){
        String pre = areaCode + birthday + randomCode;
        String verifyCode = String.valueOf(getCheckoutCode(pre));
        return pre + verifyCode;
    }

    private static Map<String, String> getAreaCode() {
        final Map<String, String> map = Maps.newHashMap();
        map.put("11", "北京");
        map.put("12", "天津");
        map.put("13", "河北");
        map.put("14", "山西");
        map.put("15", "内蒙古");
        map.put("21", "辽宁");
        map.put("22", "吉林");
        map.put("23", "黑龙江");
        map.put("31", "上海");
        map.put("32", "江苏");
        map.put("33", "浙江");
        map.put("34", "安徽");
        map.put("35", "福建");
        map.put("36", "江西");
        map.put("37", "山东");
        map.put("41", "河南");
        map.put("42", "湖北");
        map.put("43", "湖南");
        map.put("44", "广东");
        map.put("45", "广西");
        map.put("46", "海南");
        map.put("50", "重庆");
        map.put("51", "四川");
        map.put("52", "贵州");
        map.put("53", "云南");
        map.put("54", "西藏");
        map.put("61", "陕西");
        map.put("62", "甘肃");
        map.put("63", "青海");
        map.put("64", "宁夏");
        map.put("65", "新疆");
        map.put("71", "台湾");
        map.put("81", "香港");
        map.put("82", "澳门");
        map.put("91", "国外");
        return map;
    }

}
