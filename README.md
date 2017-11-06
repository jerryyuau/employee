This challenge includes two parts: backend server side and frontend client side. 

To start the server, run mvnw spring-boot:run, which will start server at port 8181. Currently only two commands are opened: get all employees and add employee.
When adding employee, two rules applied: 
	1. if manager name exists but doesn't exist in the table, it is not allowed
	2. if added name already exists, it is not allowed.
	
To start up the client, run these two commands:
	1. npm start
	2. npm run server which will start a server at port 8118. Input this url(http://localhost:8118) in the browser to fetch employees and add employees. It will fetch employees every 3 seconds.
	
Frontend uses Vue and bootstrap technology.