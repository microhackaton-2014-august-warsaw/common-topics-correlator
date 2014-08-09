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

    private TwitterCollector collectorWorker

    @Autowired
    CorrelationController(TwitterCollector collectorWorker) {
        this.collectorWorker = collectorWorker
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/correlations", produces="application/json", method = RequestMethod.POST)
    void correlate(@RequestBody String correlation) {
        log.info("Correlation: {}", correlation)    //TODO: Replace with a dedicated class
    }
}