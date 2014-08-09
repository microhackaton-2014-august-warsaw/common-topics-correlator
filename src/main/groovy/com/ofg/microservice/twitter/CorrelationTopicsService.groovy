package com.ofg.microservice.twitter

import com.ofg.infrastructure.discovery.ServiceResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by pawel on 09.08.14.
 */
@Service
class CorrelationTopicsService {

    private final CorrelationRepository repository
    private final Judge judge

    @Autowired
    CorrelationTopicsService(CorrelationRepository repository, Judge judge) {
        this.repository = repository
        this.judge = judge
    }

    void correlate(CorrelationRequest correlationRequest) {

        Correlation correlation = repository.findByPairId(correlationRequest.pairId)
        if(correlation != null) {
            if (correlation.isCompleted()){
                // third request and so on
                return
            }
            // second request
            correlation.addRequest(correlationRequest)
            judge.send(correlation.pairId, correlation.generateMatches())
        } else {
            // first request
            correlation = Correlation.createAggregate(correlationRequest)
        }
        repository.save(correlation)
    }

}
