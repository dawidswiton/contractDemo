# Contract testing demo
Example of Maven multimodule project with Spring Boot, OpenFeign and contract testing. 

# How it's working
We have maven modules:
- api (interfaces for interactions between modules)
- mod1 (module creates a REST service with enpoint `/name` and returns name string from properties)
- mod2 (module creates a REST service with enpoint `/text` and returns name string loaded via network from previous service with some additional text)
- modtest (performing contract testing between services via running both services on different ports)

# How to build:
`maven clean install`

# Miscellaneous
Both spring Boot services are build as executable jar files with suffix `exec` in directory `target`.
