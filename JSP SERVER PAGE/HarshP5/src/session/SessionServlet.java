/*
 @author Harshkumar Pandya
 */
package session;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SessionServlet extends HttpServlet {

    //  Create Maps to store the books and price
    private final Map books = new HashMap();
    private Map<String, Integer> priceInfo = new HashMap<String, Integer>();

    /**
     * Loads up the information in an hashmap which can then be used against the
     * user selection.
     */
    @Override
    public void init() {
        books.put("Book1", "101");
        books.put("Book2", "102");
        books.put("Book3", "103");
        books.put("Book4", "104");
        books.put("Book5", "105");

        priceInfo.put("101", 200);
        priceInfo.put("102", 400);
        priceInfo.put("103", 600);
        priceInfo.put("104", 100);
        priceInfo.put("105", 200);
    }

    /**
     * This method is called form the index.jsp page with information about the
     * books selected which are then stored in Session. If nothing is selected
     * user is asked to go back and make a selection
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSelected = false;
        if (books == null || priceInfo == null) {
            init();
        }
        // Get the value choosen from the radio button
        String bookname = request.getParameter("bookname");
        HttpSession session = request.getSession(true);

        isSelected = (bookname == null) ? false : true;
        // Set the session
        if (isSelected) {
            session.setAttribute(bookname, books.get(bookname));
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // send XHTML page back to the Client
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " + "XTML 1.0 Strict//EN\" \"http://www.w3c.org" + "/TR/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3c.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<title>Session Example</title><br/>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Session Example:  You have selected " + bookname + "</p>");
        out.println("<p>Session ID : " + session.getId() + "<br />");
        out.println("" + (session.isNew() ? "New session" : "Existing Session") + "<br />");
        out.println("Created on: " + new Date(session.getCreationTime()) + "<br />");
        out.println("Time of last access: " + new Date(session.getLastAccessedTime()) + "<br />");
        out.println("The Maxium inactive time allowed: " + session.getMaxInactiveInterval() + " seconds</p>");
        if (isSelected) {
            out.println("<p><a href = " + "/HarshP5/index.jsp>" + "Choose another book under Session example</a></p>");
            out.println("<p><a href = " + "\"/HarshP5/SessionServlet\">" + "Click here to choose book recommendations</a></p>");
        } else {
            out.println("<p>Nothing was selected, go back and try again under Session Example" + bookname + "</p>");
            out.println("<p><a href = " + "/HarshP5/index.jsp>" + "Choose another book under Session example</a></p>");

        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    /**
     * This method sends the information to final order info, for the books to
     * be ordered.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Enumeration valueNames;
        // get the values.
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

        if (valueNames != null && valueNames.hasMoreElements()) {
            out.println("<h1>Recommendations</h1>");
            out.println("<p>");
            String name, value;
            while (valueNames.hasMoreElements()) {
                // get the key - value pair from the session
                name = valueNames.nextElement().toString();
                value = session.getAttribute(name).toString();
                int price = priceInfo.get(value);

                out.println("<form action=" + "\"/HarshP5/FinalOrderInfo\" method='get'>");
                out.println("<input type = 'checkbox' name='finalOrder' value='" + name + "'>");
                out.println(" Book Name: " + name + "</br>" + "ISBN#: " + value + "<br />" + "Price = " + price + "</br>");
                out.println("</p>");
            }

            // Submit the order information
            out.println("<input type = 'submit'  value='Order Now'/>");
            out.println("</form>");
        } else {
            // IF no language is chosen
            out.println("<h1>No Recommendations</h1>");
            out.println("<p>You did not select a Book.</p>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}