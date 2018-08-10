package com.hbwh.xj.myblog.bean;

import javax.persistence.Id;
import java.math.BigInteger;

/**
 * 通用 Mapper 中，默认情况下是将实体类字段按照驼峰转下划线形式的表名列名进行转换
 */
public class User {
    @Id
    private String userid;
    private String password;

    private String usernm;
    private String signature;
    private String imagePath;
    private Integer level;
    private BigInteger pageView;
    private Long score;
    private Long rank;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernm() {
        return usernm;
    }

    public void setUsernm(String usernm) {
        this.usernm = usernm;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigInteger getPageView() {
        return pageView;
    }

    public void setPageView(BigInteger pageView) {
        this.pageView = pageView;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", usernm='" + usernm + '\'' +
                ", signature='" + signature + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", level=" + level +
                ", pageView=" + pageView +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}
