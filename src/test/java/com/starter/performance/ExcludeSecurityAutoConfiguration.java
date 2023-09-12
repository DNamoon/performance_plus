package com.starter.performance;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ComponentScan(
    basePackages = "com.starter.performance",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {SecurityAutoConfiguration.class}
    )
)
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
public class ExcludeSecurityAutoConfiguration {

}
