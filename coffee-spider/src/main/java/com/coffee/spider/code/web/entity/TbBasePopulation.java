package com.coffee.spider.code.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_base_population")
public class TbBasePopulation implements Serializable {

    @Id
    private Long id;
    private Long rank;
    private Date date;
    private String country;
    private String countryCode;
    private Long population;
    private Double increaseRate;
    private Double populationDensity;

    public TbBasePopulation() {
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(Double increaseRate) {
        this.increaseRate = increaseRate;
    }

    public Double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(Double populationDensity) {
        this.populationDensity = populationDensity;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "id=" + id +
                "rank=" + rank +
                ", date=" + date +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", population=" + population +
                ", increaseRate=" + increaseRate +
                ", populationDensity=" + populationDensity +
                '}';
    }
}
