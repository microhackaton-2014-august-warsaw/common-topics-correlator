package com.ofg.microservice.twitter

import spock.lang.Specification

/**
 * Created by pawel on 09.08.14.
 */
class CorrelationSpec extends Specification {

    def "correlation score should be 5 for unordered topics match"() {
        given:
        Correlation correlation = new Correlation(
                pairId: "1",
                entries: [new CorrelationEntry(analyzerType: AnalyzerType.twitter,
                        analyzedId: "marcin",
                        topics: [new Topic("groovy"), new Topic("scala")]
                ), new CorrelationEntry(analyzerType: AnalyzerType.twitter,
                        analyzedId: "pawel",
                        topics: [new Topic("scala"), new Topic("groovy")]
                )])

        when:
            List<CorrelationMatch> matches = correlation.generateMatches()

        then:
            matches?.get(0)?.score == 5
            matches?.get(0)?.topic == "groovy, scala"
        and:
            matches?.get(1)?.score == 5
            matches?.get(1)?.topic == "scala, groovy"
    }
}
