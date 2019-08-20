package com.flaya.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Component // 添加之后可以在配置文件中应用组件映射格式：girl-info
@Table(name = "girl")
@ConfigurationProperties(prefix = "girl-info") // 映射配置文件application-dev.yml
public class GirlInfo implements Serializable {

    private static final long serialVersionUID = -3013690653206061481L;

    private int id;

    private String cupSize;

    // 表单验证
    @Min(value = 18, message = "Too young to into!")
    private Integer age;

    // 表单验证
    @NotNull(message = "No money no happy!")
    private double money;

    public GirlInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "GirlInfo{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
