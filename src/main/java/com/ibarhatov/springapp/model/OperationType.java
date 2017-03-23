package com.ibarhatov.springapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ibarkhatov on 16.03.2017.
 */
@Entity
@Table(name = "operation_type")
public class OperationType implements Serializable{

    @Id
    @Column(name = "operation_type_id")
    @GeneratedValue
    private int number;

    @Column(name = "operation_type_code")
    private String code;

    @Column(name = "operation_type_name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "business_process_code")
    private String businessProcessCode;

    @ManyToMany
    @JoinTable(name = "oper_type_oper_stage",
            joinColumns = @JoinColumn(name = "operation_type_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_stage_id"))
    private Set<OperationStage> operationStages;

    public Set<OperationStage> getOperationStages() {
        return operationStages;
    }

    public void setOperationStages(Set<OperationStage> operationStages) {
        this.operationStages = operationStages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBusinessProcessCode() {
        return businessProcessCode;
    }

    public void setBusinessProcessCode(String businessProcessCode) {
        this.businessProcessCode = businessProcessCode;
    }

    @Override
    public String toString() {
        return new StringBuilder("number=" + number).append(";code=" + code).append(";name=" + name)
                .append(";is active=" + isActive).append("\n").toString();
    }
}
