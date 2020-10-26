package com.coffee.coffeeweb.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class PeopleBean {

    private static final long serialVersionUID = -91969758749726312L;

    public PeopleBean() {
    }

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private Date birthday;
    @Column
    private String idCard;
    @Column
    private String signIssueOrg;
    @Column
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column
    private String city;


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
