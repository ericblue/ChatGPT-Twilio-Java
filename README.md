## About This Project

ChatGPT-Twilio-Java - Provides integration with ChatGPT via Twilio allowing text message (SMS) and voice interactions with ChatGPT's AI chatbot

This web application, written in Java using the Micronaut (https://micronaut.io/) framework provides API endpoints to support both SMS and Voice interactions via Twilio (in progress).  And uses OpenAI's APIs and supported engines (text-davinci-003) and models (currently tested with gpt-3.5-turbo).



<img src="https://scontent-sjc3-1.xx.fbcdn.net/v/t39.30808-6/340105174_156218764047619_2021003548218793191_n.jpg?stp=cp6_dst-jpg&_nc_cat=102&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=E8FTBM7-lXEAX9xKlwW&_nc_ht=scontent-sjc3-1.xx&oh=00_AfCk6P3u7ctZb4CoTs31OFd0IUeh1UDhKrsBSj1f3FXsSQ&oe=6437D99A" title="" alt="May be an image of text that says '10 CG Chat Today 1:50 PM Hello world! Hello there! How may today? assist you What's the meaning of life? As an AI language model, do not have personal beliefs or opinions, including on the meaning of life. The meaning of life is a complex philosophical and existential question that has been debated by thinkers,'" width="437">



## Setup

### OpenAI

An OpenAI API Key is required - this can be obtained at [OpenAI Account Keys](https://platform.openai.com/account/api-keys) and configured as an environment variable



### Twillio

* Register a new Twilio number at https://www.twilio.com/

* Under your number settings, enter webhooks for both Messaging and Voice & Fax
  
  * Messaging = https://{your public hostname}/sms
  
  * Voice = https://{your public hostname}/voice
  
  * Note: Voice functionality is not complete - this initial implementation will just greet the caller, and doesn't currently support interactions (TODO)



![](/Users/ericblue/Library/Application%20Support/marktext/images/2023-04-09-18-20-51-image.png)

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

![](/Users/ericblue/Library/Application%20Support/marktext/images/2023-04-09-18-26-01-image.png)

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

```
# Create a new Heroku app
heroku create mychatgpt3twilio

# Login to the container registry
heroku container:login

# Run a docker build
./gradlew dockerBuild

# CD to the build directory with the Dockerfile
cd build/docker/main

# Push the container
heroku container:push web -a mychatgpt3twilio

# Release the container
heroku container:release web -a mychatgpt3twilio

# Open with the public URL
heroku open -a mychatgpt3twilio
```

## Todo

* Complete voice support - consider additional voice engines (Twilio, Microsoft Azure Voices, Amazon Poly, etc)

* Add support for system messages/personas/roles to perist across multiple messages for a given session and phone number

## Author

This app was created by Eric Blue - [https://eric-blue.com](https://eric-blue.com/)


