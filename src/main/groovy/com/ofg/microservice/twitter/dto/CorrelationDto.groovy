package com.ofg.microservice.twitter.dto

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class CorrelationDto {

    private String pairId;
    private String correlatorType;  //TODO: Enum
    private List<RelationshipDto> relationships
}
