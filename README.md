# **SpringBoot Register & Login System** :rocket:  
---  
Login/resgtration backend system with SpringBoot: Login + Register + email confirmation

:white_check_mark: SpringBoot   
:lock: Spring Security  
:monocle_face: Email confirmation with expiration  
:zap: Spring Data Jpa


## CURL  
```
curl --location --request POST 'localhost:8090/api/v1/registration' \  
--header 'Content-Type: application/json' \  
--data-raw '{  
    "name": "Klark",   
    "lastName" : "Kent",   
    "email" : "superman@gmail.com",   
    "password" : "whatever"  
}'
```
