package com.takeaway.challenge.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takeaway.challenge.persistence.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event> findByEmployeeId(Integer empId);

}
