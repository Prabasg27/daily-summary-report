Processed Future Movement Project

1.	Overview
    a.	Spring-boot version 3.2.1 & OpenJDK java 17 used. 
    b.	‘System A’ Movement-specifications inserted into H2 DB.
    c.	Schema.sql & data.sql will initialize  jdbc:h2:mem:movementdb
    d.	Apache commons csv dependency used to export csv file.
    e.	Input.txt & output.csv added under ‘src’ folder.
    f.	Dockerfile added to build docker image and run containers. 
    g.	Global-Exception handler added to handle custom exceptions.
    h.	AOP: execution Time and errors logged using @Aspect.
    i.	Unit Testing covered simply on Controller & Service classes.

2.	Project Execution
    a.	Run the source-code OR use Dockerfile to UP the REST API.
    b.	No context path added, runs on tomcat in port 8080. 
    c.	http://localhost:8080/api/v1/json  render the JSON result.
    d.	http://localhost:8080/api/v1/csv  export output.csv file.
    e.	Done by In-memory processing using java stream functions.

3.	Sample Output.csv
    Client_Information,Product_Information,Total_Transaction_Amount
    CL-1234-0003-0001,CME-FU-N1-2010-09-10,285.0
    CL-4321-0002-0001,SGX-FU-NK-2010-09-10,46.0
    CL-1234-0003-0001,CME-FU-NK.-2010-09-10,-215.0
    CL-4321-0003-0001,CME-FU-N1-2010-09-10,-79.0
    CL-1234-0002-0001,SGX-FU-NK-2010-09-10,-52.0

4.	Sample JSON 
    [{		
        "client_Information": "CL-1234-0002-0001",
        "product_Information": "SGX-FU-NK-2010-09-10",
        "total_Transaction_Amount": -52.0
      },{
        "client_Information": "CL-1234-0003-0001",
        "product_Information": "CME-FU-N1-2010-09-10",
        "total_Transaction_Amount": 285.0
      },{
        "client_Information": "CL-4321-0002-0001",
        "product_Information": "SGX-FU-NK-2010-09-10",
        "total_Transaction_Amount": 46.0
      },{
        "client_Information": "CL-1234-0003-0001",
        "product_Information": "CME-FU-NK.-2010-09-10",
        "total_Transaction_Amount": -215.0
      },{
        "client_Information": "CL-4321-0003-0001",
        "product_Information": "CME-FU-N1-2010-09-10",
        "total_Transaction_Amount": -79.0 
    }]
