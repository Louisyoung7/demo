package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_score")
public class Score {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String subject;
    private Integer score;

    public Score() {
    }

    public Score(String name, String subject, Integer score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{id=" + id + ", name='" + name + "', subject='" + subject + "', score = " + score + "}";
    }

}

