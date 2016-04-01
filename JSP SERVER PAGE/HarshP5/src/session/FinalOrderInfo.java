/*
 @author Harshkumar Pandya
 */
package session;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * This class will order the selected books from the cookies handler page.
 */
public class FinalOrderInfo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Map that will store the information
        Map booksInfo = new HashMap();
        Map<String, Integer> priceInfo = new HashMap<String, Integer>();

        booksInfo.put("Book1", "101");
        booksInfo.put("Book2", "102");
        booksInfo.put("Book3", "103");
        booksInfo.put("Book4", "104");
        booksInfo.put("Book5", "105");

        priceInfo.put("101", 200);
        priceInfo.put("102", 400);
        priceInfo.put("103", 600);
        priceInfo.put("104", 100);
        priceInfo.put("105", 200);

        //Get the values checked from previous page
        String books[] = request.getParameterValues("finalOrder");

        HttpSession session = request.getSession(false);
        Enumeration valueNames;

        if (session != null) {
            valueNames = session.getAttributeNames();
        } else {
            valueNames = null;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send XHTML page back to the client
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " + "XTML 1.0 Strict//EN\" \"http://www.w3c.org" + "/TR/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3c.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<title>Recommendations</title>");
        out.println("</head>");
        out.println("<body>");

        //Initializing the total.
        int total = 0;

        // Printing selected books
        out.println("<p>Thank you for ordering the following books:</br>");

        //Display appropriate message if nothing is selected.
        if (books == null || books.length == 0) {
            out.println("<p>no books selected</br>");
        } else {
            for (int i = 0; i < books.length; ++i) {
                if (booksInfo.get(books[i]) == null) {
                    continue;
                }
                out.println(" How to Program: " + (books[i]));
                out.println("</br>");
                total = total + priceInfo.get(booksInfo.get(books[i]));

            }

            //printing  total price
            out.println("<p>Your Total price is  :</br>");

            out.println(total);
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}