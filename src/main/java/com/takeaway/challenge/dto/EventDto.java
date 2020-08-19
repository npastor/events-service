package com.takeaway.challenge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Event")
public class EventDto {

    @ApiModelProperty(value = "Event ID.")
    private Integer id;

    @ApiModelProperty(value = "Employee ID.")
    private Integer employeeId;

    @ApiModelProperty(value = "Action.")
    private String action;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EventDto id(Integer id) {
        this.id = id;
        return this;
    }

    public EventDto employeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public EventDto action(String action) {
        this.action = action;
        return this;
    }

    @Override
    public String toString() {
        return "EventDto [id=" + id + ", employeeId=" + employeeId + ", action=" + action + "]";
    }
}
