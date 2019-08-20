package com.flaya.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 游戏信息
 */
@Entity
@Data
@Table(name = "t_accelerator_game")
public class GameInfo implements Serializable {

    private static final long serialVersionUID = 852748128608436007L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gameName; // 游戏名称
    private String gameIcon; // 游戏icon
    private String gamePic; // 游戏大图
    private String gamePath; // 游戏启动路径
    private Long insertTime;
    private Long updateTime;
    private String remark;

    public GameInfo() {
    }

    public GameInfo(List<String> list) {
        this.gameName = list.get(0);
        this.gameIcon = list.get(2);
        this.gamePic = list.get(3);
        this.gamePath = "";
        this.insertTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", gameIcon='" + gameIcon + '\'' +
                ", gamePic='" + gamePic + '\'' +
                ", gamePath='" + gamePath + '\'' +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

