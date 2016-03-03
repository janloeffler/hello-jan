# Demo deployment to AWS

# Make sure stups / senza is up-to-date

    $ sudo pip3 install --upgrade stups

# Login to AWS
    
    $ mai login opensource

# Login to Pier One Docker registry

    $ pierone login

# Build the project

    $ mvn clean package
    $ docker build -t pierone.stups.zalan.do/opensource/hello-jan:0.0.1-SNAPSHOT .
    
# Run locally

    $ java -jar target/hello-jan.jar
    
# Check that our Docker image works

    $ docker run -p 8080:8080 -it pierone.stups.zalan.do/opensource/hello-jan:0.0.1-SNAPSHOT

Visit [http://localhost:8080/](http://localhost:8080/)! Stop your server with **Ctrl+C**.

# Deploy it in the cloud!

    $ docker push pierone.stups.zalan.do/opensource/hello-jan:0.0.1-SNAPSHOT

If you did not set up Senza before, go and visit Stups docs:
[https://docs.stups.io](https://docs.stups.io)

Initialize your new application:

    $ senza init hello-jan.yaml --region eu-west-1

Add our Docker image as a new version to our application:

    $ senza create hello-jan.yaml 1 0.0.1-SNAPSHOT --region eu-west-1
    $ senza events hello-jan -W

Give the new version some traffic

    $ senza traffic hello-jan 1 100

Check result

    $ https://hello-jan.opensource.zalan.do/    