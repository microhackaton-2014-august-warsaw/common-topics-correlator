package com.ofg.microservice.twitter

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.filter.correlationid.CorrelationIdHolder
import com.ofg.microservice.twitter.dto.CorrelationDto
import com.ofg.microservice.twitter.dto.Relationship
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.RestTemplate

import java.lang.invoke.MethodHandles

/**
 * Created by pawel on 09.08.14.
 */
@Service
class JudgeImpl implements Judge{

    public static final String IMPORTANCE_JUDGE_CONTENT_TYPE_HEADER = 'application/vnd.com.ofg.importance-judge.v1+json'
    public static final MediaType IMPORTANCE_JUDGE_MEDIA_TYPE = new MediaType('application', IMPORTANCE_JUDGE_CONTENT_TYPE_HEADER)

    private ServiceResolver serviceResolver
    private RestTemplate restTemplate

    @Autowired
    JudgeImpl(ServiceResolver serviceResolver) {
        this.serviceResolver = serviceResolver
        this.restTemplate = new com.ofg.infrastructure.web.resttemplate.RestTemplate()
    }

    @Override
    void send(String pairId, List<CorrelationMatch> matches) {

        String judgeUrl = serviceResolver.getUrl("importance-judge").get()
        restTemplate.put("$judgeUrl/relationships", createEntity(
                createTestCorrelation(pairId, matches)))

    }

    CorrelationDto createTestCorrelation(String pairId, List<CorrelationMatch> matches) {
        new CorrelationDto(pairId: pairId, correlatorType: "twitter",
                relationships: matches.collect { new Relationship(score: it.score, description: it.topic) })
    }

    private HttpEntity<Object> createEntity(Object object) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(IMPORTANCE_JUDGE_MEDIA_TYPE)
        headers.set(CorrelationIdHolder.CORRELATION_ID_HEADER, CorrelationIdHolder.get())
        return new HttpEntity<Object>(object, headers);
    }
}
