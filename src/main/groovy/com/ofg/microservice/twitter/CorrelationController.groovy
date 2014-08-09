package com.ofg.microservice.twitter

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import java.lang.invoke.MethodHandles

@CompileStatic
@RestController
class CorrelationController {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private CorrelationTopicsService correlationTopicsService

    @Autowired
    CorrelationController(CorrelationTopicsService correlationTopicsService) {
        this.correlationTopicsService = correlationTopicsService
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/correlations", produces="application/json", method = RequestMethod.POST)
    void correlate(@RequestBody CorrelationRequest correlationRequest) {
        log.info("Correlation: {}", correlationRequest)
        correlationTopicsService.correlate(correlationRequest)
    }
}