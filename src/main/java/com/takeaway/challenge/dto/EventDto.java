package com.takeaway.challenge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Event")
public class EventDto {

    @ApiModelProperty(value = "Action.")
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EventDto action(String action) {
        this.action = action;
        return this;
    }

    @Override
    public String toString() {
        return "EventDto [action=" + action + "]";
    }
}
