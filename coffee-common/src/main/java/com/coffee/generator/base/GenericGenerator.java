package com.coffee.generator.base;

import java.util.Date;

public interface GenericGenerator {

    /**
     * 校验身份证号码 （此处不负责校验身份证所属年月对应的行政区划代码是否同于实际的年月对应的行政区划代码）
     * @param idCard
     * @return
     */
    public  boolean validateIDCard(String idCard);

    /**
     * 随机生成身份证号
     * @return
     */
    public String generate();

    /**
     * 根据生日生成身份证号
     * @param birthday
     * @return
     */
    public String generate(Date birthday);

    /**
     * 根据生日区间生成身份证号
     * @param startDate
     * @param endDate
     * @return
     */
    public String generate(Date startDate, Date endDate);

    /**
     * 根据生日和地区代码生成身份证号
     * @param birthday
     * @param areaCode
     * @return
     */
    public String generate(Date birthday, String areaCode);

    /**
     * 根据地区生成身份证号
     * @param areaCode
     * @return
     */
    public String generate(String areaCode);

    /**
     * 根据生日、地区和性别生成身份证号
     * @param birthday
     * @param areaCode
     * @param sex
     * @return
     */
    public String generate(Date birthday, String areaCode,String sex);

    /**
     * 根据生日区间、地区和性别生成身份证号
     * @param birthdayStart
     * @param birthdayEnd
     * @param areaCode
     * @param sex
     * @return
     */
    public String generate(Date birthdayStart, Date birthdayEnd, String areaCode,String sex);


}
