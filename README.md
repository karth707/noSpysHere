# noSpysHere
Spy messages App

<b>Application Requirement:</b>
The main functionality of the web application will have user registration, login, logout, as well as, a message board, with users able to post messages.
Each user will have a unique “knock sequence” (sequence = (first4chars(MD5(username))) % 4). Once the secret “knock sequence” of URLs is requested by a logged-in session, the application must switch into “secret” mode. 
Everything should remain the same, except now secret messages will be shown and posted.

Knock-codes:
[
  0 : /user/register,
  1 : /user/login,
  2 : /message/add,
  3 : /message/list
]

----------------------------------------------------------------------------------------------------------------------------------------

<b>Instructions</b>
To run the application you need apache tomcat, MySQl and maven.
Do a ‘mvn clean package’ in the root of the source this will generate a .war file that can be deployed on tomcat.
Note: for the DB queries and the DB config, check the dbQueries file, noSpysHere-servlet.xml and spring-security.xml and make appropriate changes.

<i>Base URL: localhost:8080/noSpysHere/</i>

----------------------------------------------------------------------------------------------------------------------------------------

<b>Explanation:</b>
The state information is stored in two session attributes:
‘isSpy’ -> This attribute is 1 when user has provided the knock code and 0 otherwise
‘spyCode’ -> A list of the states visited by the user (this is compared to knockCode and at any stage if there is a match the ‘isSpy’ session attribute is set to 1)

There are 4 controllers as listed below along with the URL mapping to trigger the controllers:

RegisterController ("/user/register")<br>
LoginController ("/user/login")<br>
ListController ("/message/list")<br>
MessageController ("/message/add")<br>

<i>RegisterController:</i>
When there is a GET request, the form is sent. When there is a POST request, a database connection is made and the user info is filled into the userInfo table along with the knockcode. If the user is already logged in then the current state is added to the queue of the last 4 states.

<i>LoginController:</i>
When there is a GET request, the form is sent. When there is a POST request, the info is validated and the user logs in. If the user is already logged in then the current state is added to the queue of the last 4 states.

<i>ListController:</i>
This page is shown when the user logs in. If the ‘isSpy’ session attribute is set to 1, then the db queries the spy table for the list of spy messages, else it queries the regular table. The current state is added to the queue of the last 4 states.

<i>MessageController:</i>
When there is a GET request, the form is sent. The user enters a message and submits. If the ‘isSpy’ session attribute is set to 1, then the title and message gets added to the spy table, else it gets added to the regular table. The current state is added to the queue of the last 4 states.

(“/user/logout”)
This calls the j_spring_security_logout of spring security and the session attributes are set to null and the user logs out successfully.
