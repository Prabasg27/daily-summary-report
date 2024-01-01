<b><u>Processed Future Movement Project</u></b><br>
<br>
1.	<b><u>Overview</u></b><br>
    a.	Spring-boot version 3.2.1 & OpenJDK java 17 used.<br>
    b.	‘System A’ Movement-specifications inserted into H2 DB.<br>
    c.	Schema.sql & data.sql will initialize  jdbc:h2:mem:movementdb<br>
    d.	Apache commons csv dependency used to export csv file.<br>
    e.	Input.txt & output.csv added under ‘src’ folder.<br>
    f.	Dockerfile added to build docker image and run containers.<br>
    g.	Global-Exception handler added to handle custom exceptions.<br>
    h.	AOP: execution Time and errors logged using @Aspect.<br>
    i.	Unit Testing covered simply on Controller & Service classes.<br>
<br>
2.	<b><u>Project Execution</u></b><br>
        a.	Run the source-code OR use Dockerfile to UP the REST API.<br>
        b.	No context path added, runs on tomcat in port 8080.<br>
        c.	<a href="http://localhost:8080/api/v1/json">http://localhost:8080/api/v1/json</a> render the JSON result.<br>
        d.	<a href="http://localhost:8080/api/v1/csv">http://localhost:8080/api/v1/csv</a>  export output.csv file.<br>
        e.	Done by In-memory processing using java stream functions.<br>
<br>
3.	<b><u>Sample Output.csv</u></b><br>
    Client_Information,Product_Information,Total_Transaction_Amount<br>
    CL-1234-0003-0001,CME-FU-N1-2010-09-10,285.0<br>
    CL-4321-0002-0001,SGX-FU-NK-2010-09-10,46.0<br>
    CL-1234-0003-0001,CME-FU-NK.-2010-09-10,-215.0<br>
    CL-4321-0003-0001,CME-FU-N1-2010-09-10,-79.0<br>
    CL-1234-0002-0001,SGX-FU-NK-2010-09-10,-52.0<br>
<br>
4.	<b><u>Sample JSON</u></b><br>
        [{<br>		
            "client_Information": "CL-1234-0002-0001",<br>
            "product_Information": "SGX-FU-NK-2010-09-10",<br>
            "total_Transaction_Amount": -52.0<br>
          },{<br>
            "client_Information": "CL-1234-0003-0001",<br>
            "product_Information": "CME-FU-N1-2010-09-10",<br>
            "total_Transaction_Amount": 285.0<br>
          },{<br>
            "client_Information": "CL-4321-0002-0001",<br>
            "product_Information": "SGX-FU-NK-2010-09-10",<br>
            "total_Transaction_Amount": 46.0<br>
          },{<br>
            "client_Information": "CL-1234-0003-0001",<br>
            "product_Information": "CME-FU-NK.-2010-09-10",<br>
            "total_Transaction_Amount": -215.0<br>
          },{<br>
            "client_Information": "CL-4321-0003-0001",<br>
            "product_Information": "CME-FU-N1-2010-09-10",<br>
            "total_Transaction_Amount": -79.0 <br>
        }]<br>
    <br>
