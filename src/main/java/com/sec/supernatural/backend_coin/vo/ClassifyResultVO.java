package com.sec.supernatural.backend_coin.vo;

/**
 * @author shenyichen
 * @date 2021/6/18
 **/
public class ClassifyResultVO {
    String name;
    double score;

    public ClassifyResultVO(String name,double score){
        super();
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
