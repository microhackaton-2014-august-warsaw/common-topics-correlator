package com.ofg.microservice.twitter

import com.ofg.infrastructure.discovery.ServiceResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * Created by pawel on 09.08.14.
 */
@Service
class JudgeImpl implements Judge{

    private ServiceResolver serviceResolver

    private RestTemplate restTemplate = new RestTemplate()

    @Autowired
    JudgeImpl(ServiceResolver serviceResolver) {
        this.serviceResolver = serviceResolver
    }

    @Override
    void send(String pairId, List<CorrelationMatch> matches) {
        // TODO send it
    }
}
