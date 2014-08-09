package com.ofg.microservice.twitter.dto

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class Relationship {

    private int score
    private String description
}
