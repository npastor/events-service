package com.takeaway.challenge.consumer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.takeaway.challenge.consumer.EventConsumer;
import com.takeaway.challenge.dto.EmployeeEventDto;
import com.takeaway.challenge.persistence.model.Event;
import com.takeaway.challenge.persistence.repository.EventRepository;

@Service
public class EventConsumerImpl implements EventConsumer {

    @Autowired
    private EventRepository eventRepository;

    private final Logger logger = LoggerFactory.getLogger(EventConsumerImpl.class);

    private static final String TOPIC = "EMPLOYEE_DATA";

    private static final String GROUP_ID = "group-id";

    @Override
    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(EmployeeEventDto employeeEvent) {
        logger.info(String.format("#### -> Consumed message -> %s", employeeEvent));

        Event event = new Event();
        event.setAction(employeeEvent.getAction());
        event.setEmployeeId(employeeEvent.getEmployeeId());
        eventRepository.save(event);
    }

}
