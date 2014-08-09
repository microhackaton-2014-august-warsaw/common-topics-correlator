package com.ofg.microservice.twitter

/**
 * Created by pawel on 09.08.14.
 */
interface Judge {
    void send(String pairId, List<CorrelationMatch> matches)
}
