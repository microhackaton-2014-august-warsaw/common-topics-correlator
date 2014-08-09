package com.ofg.base

import com.ofg.infrastructure.discovery.ServiceDiscoveryStubbingConfiguration
import com.ofg.microservice.Application
import groovy.transform.CompileStatic
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@CompileStatic
@Configuration
@Import([ServiceDiscoveryStubbingConfiguration, Application])
class ServiceDiscoveryStubbingApplicationConfiguration {

    
}
