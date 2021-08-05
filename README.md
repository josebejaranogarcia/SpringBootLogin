# **SpringBoot Register & Login System**

Login/resgtration backend system with SpringBoot: Login + Register + email confirmation

:white_check_mark: SpringBoot 
:lock: SpringBoot Security 

##curl
curl --location --request POST 'localhost:8090/api/v1/registration' \  
--header 'Content-Type: application/json' \  
--data-raw '{  
      "name": "Klark",  
      "lastName" : "Kent",  
      "email" : "superman@gmail.com",  
      "password" : "whatever"  
}'
