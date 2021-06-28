
# Internationalization

Internationalization is a microservice responsible for translate the messages sent to the user based on a header called 'locale'. 

Its intended to be used internaly by others microservices, not to be exposed on internet, thats why the requests are not authenticated with jwt token

locales available: en_us and pt_br


## Made with

- java 11
- spring boot 2.5
- maven
- sprint tool suite 4

## Installation

Import on your preferred IDE as a maven project and run it like any other spring boot project

## Postman reqs

To make your life easier I put all requests available for download directly in your postman [here](https://www.getpostman.com/collections/3f7d6ea128d4b85c9943)

## Dependency

 - [Alexandria](https://github.com/RicardoCampinas/digitus-forum-alexandria)


## Microservices ecosystem

Internationalization is part of 4 microservices intended to be [my linkedin](https://www.linkedin.com/in/ricardojava/) portfolio
 - [Firewall](https://github.com/RicardoCampinas/digitus-forum-firewall-microservice)
 - [Internationalization](https://github.com/RicardoCampinas/digitus-forum-internationalization-microservice)
 - [Login](https://github.com/RicardoCampinas/digitus-forum-login-microservice)
 - [User](https://github.com/RicardoCampinas/digitus-forum-user-microservice)
 

[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/GPL-3.0)


