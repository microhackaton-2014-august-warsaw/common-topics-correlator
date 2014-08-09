package com.ofg.microservice.twitter

/**
 * Created by pawel on 09.08.14.
 */
class CorrelationEntry {

    List<Topic> topics;
    String analyzedId;
    AnalyzerType analyzerType;

    CorrelationMatch match(CorrelationEntry other) {
        String topicsAsString = topics.join(", ")
        if (topics.equals(other.topics)) {
            return new CorrelationMatch(score: 10, topic: topicsAsString)
        } else if (topics.containsAll(other.topics)) {
            return new CorrelationMatch(score: 5, topic: topicsAsString
            )
        } else {
            return new CorrelationMatch(score: 0, topic: topicsAsString)
        }
    }
}
