package com.ofg.microservice.twitter

import com.ofg.infrastructure.discovery.ServiceResolver
import org.springframework.stereotype.Service

/**
 * Created by pawel on 09.08.14.
 */
@Service
class CorrelationTopicsService {

    private final CorrelationRepository repository
    private final Judge judge

    CorrelationTopicsService(CorrelationRepository repository, Judge judge) {
        this.repository = repository
        this.judge = judge
    }

    void correlate(CorrelationRequest correlationRequest) {

        Correlation correlation = repository.findByPairId(correlationRequest.pairId)
        if(correlation != null) {

            if (correlation.isCompleted()) {
                judge.send(correlation.pairId, correlation.generateMatches())
            } else {
                correlation.addRequest(correlationRequest)
            }

        } else {
            correlation = Correlation.createAggregate(correlationRequest)
        }
        repository.save(correlation)
    }

}
