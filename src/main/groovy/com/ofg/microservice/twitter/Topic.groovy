package com.ofg.microservice.twitter

import groovy.transform.Canonical

/**
 * Created by pawel on 09.08.14.
 */
@Canonical
class Topic {
    String name


    @Override
    String toString() {
        name
    }
}
