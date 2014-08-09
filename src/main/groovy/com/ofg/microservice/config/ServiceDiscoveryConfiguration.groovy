package com.ofg.microservice.config

import com.ofg.infrastructure.discovery.ServiceResolverConfiguration
import groovy.transform.CompileStatic
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile

import static com.ofg.microservice.Profiles.DEVELOPMENT
import static com.ofg.microservice.Profiles.PRODUCTION

@CompileStatic
@Configuration
@Import(ServiceResolverConfiguration)
@Profile([PRODUCTION, DEVELOPMENT])
class ServiceDiscoveryConfiguration {
}
