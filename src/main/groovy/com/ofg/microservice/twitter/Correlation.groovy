package com.ofg.microservice.twitter

import groovy.transform.Canonical

@Canonical
class Correlation {

    String pairId
    List<CorrelationEntry> entries

    static Correlation createAggregate(CorrelationRequest correlationRequest) {
        Correlation correlation = new Correlation(pairId: correlationRequest.pairId)
        correlation.entries = new ArrayList<>()
        correlation.addRequest(correlationRequest)
    }

    void addRequest(CorrelationRequest correlationRequest) {
        entries.add(new CorrelationEntry(topics: correlationRequest.topics,
                analyzedId: correlationRequest.analyzedId,
                analyzerType: correlationRequest.analyzerType))
    }

    boolean isCompleted() {
        return entries.size() > 1
    }

    List<CorrelationMatch> generateMatches() {
        assert isCompleted()
        CorrelationEntry left = entries[0]
        CorrelationEntry right = entries[1]
        CorrelationMatch res1 = left.match(right)
        CorrelationMatch res2 = right.match(left)
        return [res1, res2]
    }
}