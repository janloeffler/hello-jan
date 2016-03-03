[![Build Status](https://travis-ci.org/janloeffler/hello-jan.svg?branch=master)](https://travis-ci.org/janloeffler/hello-jan)
[![Coverage Status](https://coveralls.io/repos/janloeffler/hello-jan/badge.svg?branch=master)](https://coveralls.io/r/janloeffler/hello-jan?branch=master)
[![Apache 2](http://img.shields.io/badge/license-Apache%202-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

# Hello-Jan build & deploy guidelines

Hello-Jan is just a demo REST API based on Spring Boot.

This project shows everything necessary, to deploy a fully working Java Spring web application to Amazon EC2 with the help of Docker and Senza (stups.io).

# Requirements

* JDK 8
* Maven 3
* Docker
* AWS account 

# Build the project

    $ mvn clean package
    $ docker build -t hello-jan:0.0.1-SNAPSHOT .
    
# Run locally

    $ java -jar target/hello-jan.jar
    $ java -javaagent:newrelic/newrelic.jar -jar target/hello-jan.jar
    
# Check that our Docker image works

    $ docker run -p 8080:8080 -it %YOUR_DOCKER_REGISTRY%/hello-jan:0.0.1-SNAPSHOT

Visit [http://localhost:8080/](http://localhost:8080/)! Stop your server with **Ctrl+C**.

# Deploy it in the cloud!

    $ docker push %YOUR_DOCKER_REGISTRY%/hello-jan:0.0.1-SNAPSHOT

If you did not set up Senza before, go and visit Stups docs:
[https://docs.stups.io](https://docs.stups.io)

Initialize your new application:

    $ senza init hello-jan.yaml --region eu-west-1

Add our Docker image as a new version to our application:

    $ senza create hello-jan.yaml 1 0.0.1-SNAPSHOT --region eu-west-1
    $ senza events hello-jan -W

This step might take a long(tm) time (minutes). Afterwards you will be able to directly go to your deployed version:
[https://hello-jan-1.0.%YOUR_DOMAIN%/](https://hello-jan-1.0.%YOUR_DOMAIN%/)

The application's main domain will still not show the deployed version. Now you can switch traffic of your main domain
to the new version:

    $ senza traffic hello-jan 1 100

Have fun with [https://hello-jan.%YOUR_DOMAIN%/](https://hello-jan.%YOUR_DOMAIN%/)!

    $ hello-jan.%YOUR_DOMAIN%
    
Observe your logs (remember that the Load Balancer checks spam your HTTP):

    $ senza console myapp.yaml
