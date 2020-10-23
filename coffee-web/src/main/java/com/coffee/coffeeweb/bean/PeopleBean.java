package com.coffee.coffeeweb.bean;

import java.util.Date;
public class PeopleBean {


    public PeopleBean() {
    }
    private int id;
    private String name;
    private String sex;
    private Date birthday;
    private String idCard;
    private String signIssueOrg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSignIssueOrg() {
        return signIssueOrg;
    }

    public void setSignIssueOrg(String signIssueOrg) {
        this.signIssueOrg = signIssueOrg;
    }
}
