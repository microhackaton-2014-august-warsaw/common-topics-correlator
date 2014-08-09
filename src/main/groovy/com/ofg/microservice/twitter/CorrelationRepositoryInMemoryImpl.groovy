package com.ofg.microservice.twitter;

import java.util.Map;

/**
 * Created by pawel on 09.08.14.
 */
class CorrelationRepositoryInMemoryImpl implements CorrelationRepository  {

    Map<String, Correlation> database = [:]

    @Override
    void save(Correlation correlation) {
        database.put(correlation.pairId, correlation)
    }

    @Override
    Correlation findByPairId(String pairId) {
        database[pairId]
    }
}
