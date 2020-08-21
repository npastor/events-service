package com.takeaway.challenge.consumer;

import com.takeaway.challenge.dto.EmployeeEventDto;

public interface EventConsumer {

    public void consume(EmployeeEventDto event);

}
