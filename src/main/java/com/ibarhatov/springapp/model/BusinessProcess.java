package com.ibarhatov.springapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ibarkhatov on 16.03.2017.
 */
@Entity
@Table(name = "BUSINESS_PROCESS")
public class BusinessProcess implements Serializable{
    @Id
    @Column(name="code")
    @GeneratedValue
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany
    @JoinColumn(name = "business_process_code")
    private Set<OperationType> operationTypes;

    public Set<OperationType> getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(Set<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
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

    @Override
    public String toString() {
        return new StringBuilder("code=" + code).append(";name=" + name).append(";is active=" + isActive)
                .append("\n").toString();
    }
}
