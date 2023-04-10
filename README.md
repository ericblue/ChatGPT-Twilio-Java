## About This Project

ChatGPT-Twilio-Java - Provides integration with ChatGPT via Twilio allowing text message (SMS) and voice interactions with ChatGPT's AI chatbot

This web application, written in Java using the Micronaut (https://micronaut.io/) framework provides API endpoints to support both SMS and Voice interactions via Twilio (in progress).  And uses OpenAI's APIs and supported engines (text-davinci-003) and models (currently tested with gpt-3.5-turbo).



![](https://eric-blue.com/chatgpt3twillio/message_preview.png)



## Setup

### OpenAI

An OpenAI API Key is required - this can be obtained at [OpenAI Account Keys](https://platform.openai.com/account/api-keys) and configured as an environment variable



### Twillio

* Register a new Twilio number at https://www.twilio.com/

* Under your number settings, enter webhooks for both Messaging and Voice & Fax
  
  * Messaging = https://{your public hostname}/sms
  
  * Voice = https://{your public hostname}/voice
  
  * Note: Voice functionality is not complete - this initial implementation will just greet the caller, and doesn't currently support interactions (TODO)



![](https://eric-blue.com/chatgpt3twillio/twillio_setup.jpg)

### Configuration - Environment Variables

This app supports configuration via environment variables and loading via a .env file. Any environment variables present on startup, or contained in the .env file in the current working directory will be loaded at runtime.

Example .env file:

```
# Your OpenAI API Key
CHATGPT_API_KEY=sk-G.....O83

# Valid numbers that can send/recieve messages 
# Optional - if enabled, only these numbers can initiates messages or calls
#TWILIO_VALID_NUMBERS=+13105551212

# Host the server will listen on - default = localhost
# Note: To run with Docker or on Heroku, this value will need to 
# be set to 0.0.0.0 
HOST=localhost

# Port the server will listen on - default = 8080
PORT=8080
```

## Development Environment

### Running the app

To start the app, run:

```
./gradlew run
```

![](https://eric-blue.com/chatgpt3twillio/gradle_run.jpg)

## Swagger

Swagger endpoints are available locally at http://localhost:8080/swagger-ui



![](https://eric-blue.com/chatgpt3twillio/swagger.jpg)

## Docker

A docker image is publically available by pulling ericblue/chatgpt3twilio:0.1 and on  [Dockerhub](https://hub.docker.com/repository/docker/ericblue/chatgpt3twilio/general)



### Quick Start

Run:

```
docker run -p 8080:8080 --env-file .env  ericblue/chatgpt3twilio:0.1
```

To enable external access with public URLs, if you are using something like ngrok (https://ngrok.com/) you can run:

```
ngrok http 8080
```

## Running on Heroku

This app has been tested on Heroku (https://heroku.com/) deploying as a Docker image and running with the Gradle buildpack.
For the application name, I used chatgpt3twilio-xyz in this example, but you can use any name you like.
```
# Create a new Heroku app
heroku create chatgpt3twilio-xyz

# Login to the container registry
heroku container:login

# Run a docker build
./gradlew dockerBuild

# CD to the build directory with the Dockerfile
cd build/docker/main

# Push the container
heroku container:push web -a chatgpt3twilio-xyz

# Release the container
heroku container:release web -a chatgpt3twilio-xyz

# Note: Make sure to set the CHATGPT_API_KEY Config var under Settings

# Open with the public URL
heroku open -a chatgpt3twilio-xyz
```

## Todo

* Complete voice support - consider additional voice engines (Twilio, Microsoft Azure Voices, Amazon Poly, etc)

* Add support for system messages/personas/roles to perist across multiple messages for a given session and phone number

## Author

This app was created by Eric Blue - [https://eric-blue.com](https://eric-blue.com/)


