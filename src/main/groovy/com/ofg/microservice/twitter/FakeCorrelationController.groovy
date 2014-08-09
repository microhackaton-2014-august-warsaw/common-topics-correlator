package com.ofg.microservice.twitter

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.filter.correlationid.CorrelationIdHolder
import com.ofg.infrastructure.web.resttemplate.RestTemplate
import com.ofg.microservice.twitter.dto.CorrelationDto
import com.ofg.microservice.twitter.dto.RelationshipDto
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import java.lang.invoke.MethodHandles

@CompileStatic
@RestController
class FakeCorrelationController {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final String IMPORTANCE_JUDGE_CONTENT_TYPE_HEADER = 'vnd.com.ofg.importance-judge.v1+json'
    public static final MediaType IMPORTANCE_JUDGE_MEDIA_TYPE = new MediaType('application', IMPORTANCE_JUDGE_CONTENT_TYPE_HEADER)

    private ServiceResolver serviceResolver
    private RestTemplate restTemplate

    @Autowired
    FakeCorrelationController(ServiceResolver serviceResolver) {
        this.serviceResolver = serviceResolver
        this.restTemplate = new RestTemplate()  //TODO: Inject it
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/correlationsTest", produces="application/json", method = RequestMethod.POST)
    void testCorrelate(@RequestBody String correlation) {
        log.info("CorrelationTest: {}", correlation)    //TODO: Replace with a dedicated class
        String judgeUrl = serviceResolver.getUrl("importance-judge").get()
        restTemplate.put("$judgeUrl/relationship", createEntity(createTestCorrelation()))
    }

    CorrelationDto createTestCorrelation() {
        new CorrelationDto(pairId: "1", correlatorType: "twitter", relationships: [new RelationshipDto(score: 8, description: "scala")])
    }

    private HttpEntity<Object> createEntity(Object object) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(IMPORTANCE_JUDGE_MEDIA_TYPE)
        headers.set(CorrelationIdHolder.CORRELATION_ID_HEADER, CorrelationIdHolder.get())
        return new HttpEntity<Object>(object, headers);
    }
}
