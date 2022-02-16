# AddUp-Assignment
Tech Stack:Spring Boot and MongoDB

Three Endpoints are present:
1)/signUp:Takes request in the format
{
	"firstName":"",
	"lastName":"",
	"email":"",
	"phone":""
}
If the user is new-
Data is saved in DB and a new token is generated in the following format:
dd-mm-yy-phoneno
The token is returned and is to be used as header "auth-token" for getting or editting profile
If the User is already present:
Find the token from db and return it as response

2)/getUser:Auth token from signUp is to be passed as header
Returns null if token not found in DB else returns the User data

3)/updateUser:Auth token from signUp is to be passed as header along with request
If auth token not found,"Invalid Auth token" is returned as response
else "Success"

Please check the screenshots folder for demo
