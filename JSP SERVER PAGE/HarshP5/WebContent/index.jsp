<%-- 
    Document   : Index page
    Harshkumar Pandya- 00001114569
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>COEN 235 Project 5</h1>
        <p>Harshkumar Pandya- 00001114569</p>
        <p>Please select from following options </p>
        <br/>
        <h3>Cookies Example</h3>
        <form action = "/HarshP5/CookieServlet" method ="post">
            <p>
                <!-- <input type = "hidden" name = "language" value = "Book1" />Book1<br />-->
                <input type = "radio" name = "bookname" value = "Book1" />Book1 <br />
                <input type = "radio" name = "bookname" value = "Book2" />Book2 <br />
                <input type = "radio" name = "bookname" value = "Book3" />Book3 <br />
                <input type = "radio" name = "bookname" value = "Book4" />Book4 <br />
            </p>
            <p><input type = "submit" value = "Submit" /></p>
        </form>
        <h3>Session Example</h3>
        <form action = "/HarshP5/SessionServlet" method ="post">
            <p>
                <!--<input type = "hidden" name = "language" value = "Book1" />Book1<br />-->
                <input type = "radio" name = "bookname" value = "Book1" />Book1<br />
                <input type = "radio" name = "bookname" value = "Book2" />Book2 <br />
                <input type = "radio" name = "bookname" value = "Book3" />Book3 <br />
                <input type = "radio" name = "bookname" value = "Book3" />Book4 <br />
            </p>
            <p><input type = "submit" value = "Submit" /></p>
        </form>
       <h3>Custom Tag Example</h3>
        <a href="/HarshP5/CustomTag.jsp">Show all Tables</a>
    </body>
</html>