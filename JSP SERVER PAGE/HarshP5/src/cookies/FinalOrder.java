/*
 @author Harshkumar Pandya
 */
package cookies;

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
public class FinalOrder extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the value from the checkbox
        String books[] = request.getParameterValues("finalOrder");

        // Creating Map for storing the data
        Map booksInfo = new HashMap();
        Map<String, Integer> info = new HashMap<String, Integer>();

        booksInfo.put("Book1", "1");
        booksInfo.put("Book2", "2");
        booksInfo.put("Book3", "4");
        booksInfo.put("Book4", "8");

        info.put("1", 200);
        info.put("2", 400);
        info.put("4", 600);
        info.put("8", 100);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send XHTML page back to the Client
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " + "XTML 1.0 Strict//EN\" \"http://www.w3c.org" + "/TR/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3c.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<title>Final Order</title>");
        out.println("</head>");
        out.println("<body>");

        // Initializing the total
        int total = 0;

        // Print all the books ordered.
        out.println("<p>Thank you for ordering the following books:</br>");
        //Display appropriate message if nothing is selected.
        if (books == null || books.length == 0) {
            out.println("<p>no books selected</br>");
        } else {
            for (int i = 0; i < books.length; ++i) {
                if (info.get(booksInfo.get(books[i])) == null) {
                    continue;
                }
                out.println(" Name of the book: " + (books[i]));
                out.println("</br>");
                total = total + info.get(booksInfo.get(books[i]));
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