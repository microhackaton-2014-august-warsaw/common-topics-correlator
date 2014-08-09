package com.ofg.microservice.twitter



interface CorrelationRepository {

    void save(Correlation correlation)

    Correlation findByPairId(String pairId)
}
