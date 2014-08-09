package com.ofg.microservice.twitter

import spock.lang.Specification

/**
 * Created by pawel on 09.08.14.
 */
class CorrelationTopicsServiceSpec extends Specification {


    def "should store topics for correlation"() {
        given:
            CorrelationRepository repository = Mock()
            Judge judge = Mock()

            CorrelationTopicsService service = new CorrelationTopicsService(repository, judge)

            CorrelationRequest request = new CorrelationRequest(analyzedId: "marcin",
                analyzerType: AnalyzerType.twitter,
                pairId: "1",
                topics: [new Topic("groovy")])

        when:
            service.correlate(request)

        then:
            1 * repository.save(_)
    }

}
