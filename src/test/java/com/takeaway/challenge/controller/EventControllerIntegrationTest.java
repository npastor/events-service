package com.takeaway.challenge.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeaway.challenge.dto.EventDto;
import com.takeaway.challenge.persistence.model.Event;
import com.takeaway.challenge.persistence.repository.EventRepository;

@WebMvcTest(value = EventController.class)
public class EventControllerIntegrationTest {

    private static final String BASE_PATH = "/v1/events";

    private final com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventRepository eventRepository;

    @Test
    void test_getEventsByEmployeeId_ok() throws Exception {
        List<Event> expectedEvents = new ArrayList<Event>();
        Event event = new Event();
        event.setAction("UPDATED");
        event.setEmployeeId(1);
        event.setId(1);
        expectedEvents.add(event);
        Mockito.when(eventRepository.findByEmployeeIdOrderById(Mockito.anyInt()))
               .thenReturn(expectedEvents);

        String contentBody = mockMvc
                                    .perform(MockMvcRequestBuilders.get(BASE_PATH + "/1")
                                                                   .accept(MediaType.APPLICATION_JSON)
                                                                   .contentType(MediaType.APPLICATION_JSON))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andReturn()
                                    .getResponse()
                                    .getContentAsString();
        Assertions.assertFalse(StringUtils.isEmpty(contentBody));
        List<EventDto> actualEvents = mapper.readValue(contentBody, new TypeReference<List<EventDto>>() {});
        Assertions.assertNotNull(actualEvents);
        Assertions.assertEquals(actualEvents.get(0).getAction(), actualEvents.get(0).getAction());

    }

    @Test
    void test_getEventsByEmployeeId_EMPTY() throws Exception {
        Mockito.when(eventRepository.findByEmployeeIdOrderById(Mockito.anyInt()))
               .thenReturn(Collections.emptyList());

        String contentBody = mockMvc
                                    .perform(MockMvcRequestBuilders.get(BASE_PATH + "/1")
                                                                   .accept(MediaType.APPLICATION_JSON)
                                                                   .contentType(MediaType.APPLICATION_JSON))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andReturn()
                                    .getResponse()
                                    .getContentAsString();
        Assertions.assertFalse(StringUtils.isEmpty(contentBody));
        List<EventDto> actualEvents = mapper.readValue(contentBody, new TypeReference<List<EventDto>>() {});
        Assertions.assertNotNull(actualEvents);
        Assertions.assertEquals(0, actualEvents.size());
    }
}
