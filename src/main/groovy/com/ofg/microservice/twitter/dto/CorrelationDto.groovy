package com.ofg.microservice.twitter.dto

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class CorrelationDto {

    String pairId;
    String correlatorType;  //TODO: Enum
    List<RelationshipDto> relationships
}
