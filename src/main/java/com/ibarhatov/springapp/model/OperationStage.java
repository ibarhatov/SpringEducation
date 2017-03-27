package com.ibarhatov.springapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ibarkhatov on 16.03.2017.
 */
@Entity
@Table(name = "operation_stage")
public class OperationStage implements Serializable{
    @Id
    @Column(name = "operation_stage_id")
    @GeneratedValue
    private int id;

    @Column(name = "operation_stage_name")
    private String name;

    @Column(name = "operation_stage_num")
    private long num;

    @Column(name = "operation_stage_type")
    private String type;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "operation_stage_code")
    private String code;

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

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new StringBuilder("id=" + id).append(";name=" + name).append(";num=" + num).append("type=" + type)
                .append(";is active=" + isActive).append(";code=" + code).append("\n").toString();
    }
}
