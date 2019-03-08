package com.sun.demo.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //声明一个实体，用的是Java规范下的注解
@Table(name = "T_STUDENT") //映射的表名称
public class StudentInfoEntity {

    /**
     * 学号,唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue
    private Integer stuNum;

    /**
     * 学生姓名
     */
    @Column(length = 20)
    private String stuName;

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}