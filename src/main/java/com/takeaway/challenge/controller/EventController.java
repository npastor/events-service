package com.takeaway.challenge.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.challenge.dto.EventDto;
import com.takeaway.challenge.persistence.repository.EventRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = {"v1/events"})
@Api(tags = {"events"})
public class EventController {

    @Autowired
    private EventRepository repo;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Gets events related to an employee by employee id",
                  response = EventDto.class,
                  responseContainer = "List",
                  produces = "application/json")
    public List<EventDto> getEventsByEmployeeId(@ApiParam(value = "Employee ID", required = true) @PathVariable(value = "id") Integer id) {

        return Optional.ofNullable(repo.findByEmployeeIdOrderById(id))
                       .orElseGet(Collections::emptyList)
                       .stream()
                       .map(event -> new EventDto().action(event.getAction()))
                       .collect(Collectors.toList());
    }
}
