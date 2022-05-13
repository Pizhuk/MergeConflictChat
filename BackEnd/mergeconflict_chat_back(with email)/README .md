#MergeConflict chat

***MergeConflict chat*** - web application that communicates in a general chat in real time.

The web application consists of four web pages: authorization page, registration page, chat page.

When entering the site, the user enters the authorization page and fills in the required fields "Login" and "Password".
If the data entered by the user is correct the user is redirected to the general chat page. If the user is not yet
registered, he can go to the registration page. On the registration page, the user must complete all required fields in
order to register. If the user has forgotten the password, he can recover it by e-mail. On the chat page, the user can
send and receive real-time messages. Users see messages from all members in the general chat. The user sees a list of
users with an online status display.

The front part of the web service is made in JavaScript. The server is written in the Java programming language.
The design is made in the HTML hypertext markup language using Cascading Style Sheets (CSS).
The remote Amazon server acts as a server. User data, as well as the history of correspondence,
are stored in a relational Postgre SQL database.

Format: JSON

###Requests

####*Success requests*

     1)  Autorization succsess

        URL: /auth
        body: ( 
             {
               "login":"Userlogin",
               "password":"Deveducation!"
             }
        )
        Response:
        Status-code: 200 OK

    2) Registration success

        URL: /reg
        body: (
             { 
               "login":"Userlogin",
               "password":"Deveducation!",
               "email":"user@gmail.com",
               "phoneNumber":"+3807777777777",
               "companyName":"company"
             }
        )
        Response:
        Status-code: 201 created

####*Invalid requests*

*Authorization invalid requests*

    1)  Fields must not be empty

        URL: /auth
        body: (
               { 
                 "login":"",
                 "password":"Deveducation!"
               }
        )
        Status-code: 400 Bad request
        Response:
                {
                  "message":"Fields must not be empty"
                }

    2)  Invalid password

        URL: /auth
        body: (
               { 
                 "login":"Userslogin",
                 "password":"Wrongpassword12"
               }
        )
        Status-code: 401 Unauthorized 
        Response:
                {
                  "message":"Invalid credentials"
                }

    3)  User with this login does not exist

        URL: /auth
        body: (
               { 
                 "login":"wronglogin",
                 "password":"Deveducation!"
               }
        )
        Status-code: 404 Not Found
        Response:
                {
                  "message":"User with this login does not exist"
                }

*Registration invalid requests*

    1)  All required fields must be filled!
        URL: /signup
        body: (
               { 
                 "login":"Userlogin",
                 "password":"Deveducation!",
                 "email":"",
                 "phoneNumber":"",
                 "companyName":"company"
               }
        )
        Status-code: 400 Bad request
        Response:
                {
                  "message":"All required fields must be filled!"
                } 

    2)  Incorrect login. Login must consist of 8-16 Latin letters and numbers

        URL: /signup
        body: (
                {
                  "login":"Userloginnnnnnnnnnnnnnnnnnn",
                  "password":"Deveducation!",
                  "email":"user@gmail.com",
                  "phoneNumber":"+380666666666",
                  "companyName":"company"
                }
        )
        Status-code: 400 Bad request 
        Response:
                {
                  "message":"Incorrect login. Login must consist of 8-16 Latin letters and numbers"
                }

    3)  Incorrect password. Password must consist of 8-16 Latin letters and numbers
        URL: /signup
        body: (
                {
                  "login":"Userlogin",
                  "password":"Userpasswordddddddddddddddd12",
                  "email":"user@gmail.com",
                  "phoneNumber":"+380777777777",
                  "companyName":"company"
                }

        )
        Status-code: 400 Bad request
         Response:
                {
                  "message":"Incorrect password. Password must consist of 8-16 Latin letters and numbers"
                }

    4)  Incorrect e-mail
        URL: /signup
        body: (
                {
                  "login":"Userlogin",
                  "password":"Deveducation!",
                  "email":"user.gmail.com",
                  "phoneNumber":"+380777777777",
                  "companyName":"company"
                }
        )
        Status-code: 400 Bad request
        Response:
                 {
                   "message":"Incorrect email"
                 }

    5)  Incorrect number
        URL: /signup
        body: (
                {
                  "login":"Userlogin",
                  "password":"Deveducation!",
                  "email":"user@gmail.com",
                  "phoneNumber":"+380777777778",
                  "companyName":"company"
                }
        )
        Status-code: 400 Bad request
        Response:
                {
                  "message":"Incorrect number"
                }

####* Websocket Requests *

    1)  Send a message to the general chat

       message:
                {
                    "cmd":"MESSAGE" 
                }
       answer:
                {
                   "payload":"{\"sentTo\":\".\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":true}"
                }

    2)  Send a message to private chat

        message:
                {
                     "cmd":"MESSAGE"
                }
         answer:
                {
                  "payload":"{\"sentTo\":\"Petro\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":false}"
                 }

    3)  Private chat history

         message:
                {
                    "cmd":"HISTORY_PRIVATE" 
                }
         answer:
                {
                    "payload":"[{\"sentTo\":\"Petro\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":true},
                                {\"sentTo\":\"Petro\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":false}]"
                }
 
    4)  General chat history

         message:
                {
                    "cmd":"HISTORY_GENERAL" 
                }
         answer:
                {
                    "payload":"[{\"sentTo\":\"PetroUs\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":true},
                                {\"sentTo\":\"PetroUs\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":true},
                                {\"sentTo\":\"PetroUs\",\"sentFrom\":\"Stepan\",\"date\":\"25.07.87\",
                                \"text\":\"Ну привет мой первый вебсокет\",\"generalChat\":true},]
                }

    5) List of registered users

         message:
                {
                    "cmd":"LIST_ALL_USERS" 
                }
         answer:
                {
                    "payload":"[\"Robert\",\"Filip\",\"Radek\"]"
                }

    5) Online user list

         message:
                {
                    "cmd":"ONLINE_STATUS" 
                }
         answer:
                {
                    "payload":""[\"John\"]"
                }



![](Снимок.PNG)