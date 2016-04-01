/*
 @author Harshkumar Pandya
 */
package cookies;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;



public class CookieServlet extends HttpServlet {

    // Create map to store the data.
    Map books = new HashMap();
    Map<String, Integer> info = new HashMap<String, Integer>();

    /**
     * Loads up the information in an hashmap which can then be used against the
     * user selection.
     */
    @Override
    public void init() {
        books.put("Book1", "1");
        books.put("Book2", "2");
        books.put("Book3", "4");
        books.put("Book4", "8");

        info.put("1", 200);
        info.put("2", 400);
        info.put("4", 600);
        info.put("8", 100);
    }

    /**
     * This method is called form the index.jsp page with information about the
     * books selected which are then stored in cookies. If nothing is selected
     * user is asked to go back and make a selection
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (books == null || info == null) {
            init();
        }
        // Get the value from the radio button.
        String bookname = request.getParameter("bookname");
        boolean isSelected = (bookname == null) ? false : true;

        if (!isSelected) {
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();

            // send XHTML page back to the Client
            out.println("<?xml version = \"1.0\"?>");
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " + "XTML 1.0 Strict//EN\" \"http://www.w3c.org" + "/TR/xhtml1-strict.dtd\">");
            out.println("<html xmlns = \"http://www.w3c.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<title>Welcome Cookies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Nothing was selected, select a book under Cookies Example" + "</p>");
            out.println("<p><a href = " + "\"/HarshP5/index.jsp\">" + "choose a book</a></p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        } else {
            String isbn = books.get(bookname).toString();

            // create a cookie and pass the book and isbn info
            Cookie cookie = new Cookie(bookname, isbn);

            // Add cookie
            response.addCookie(cookie);

            response.setContentType("text/html");

            PrintWriter out = response.getWriter();

            // send XHTML page back to the Client
            out.println("<?xml version = \"1.0\"?>");
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " + "XTML 1.0 Strict//EN\" \"http://www.w3c.org" + "/TR/xhtml1-strict.dtd\">");
            out.println("<html xmlns = \"http://www.w3c.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<title>Welcome Cookies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Welcome to Cookies! You selected " + bookname + "</p>");
            out.println("<p><a href = " + "\"/HarshP5/index.jsp\">" + "Click here to choose another book under Cookies Example</a></p>");
            out.println("<p><a href = " + "\"/HarshP5/CookieServlet\">" + "Click here to view recommendations</a></p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    /**
     * This method sends the information to final order, for the books to be
     * ordered.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the values from the cookie.
        Cookie cookies[] = request.getCookies();

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

        if (cookies != null && cookies.length != 0) {
            out.println("<h1>Recommendations</h1>");
            out.println("<p>");

            out.println("<form action=" + "\"/HarshP5/FinalOrder\" method='get'>");

            // add a checkbox and print the books information - bookname , isbn and price
            for (int i = 0; i < cookies.length; i++) {
                if (info.get(cookies[i].getValue()) == null) {
                    continue;
                }
                out.println("<input type = 'checkbox' name='finalOrder' value='" + cookies[i].getName() + "'>");
                out.println(" How to Program: " + cookies[i].getName() + " </br> &nbsp;ISBN#: "
                        + cookies[i].getValue() + "  </br>           Price :  " + info.get(cookies[i].getValue()) + "<br /></br>");
            }

            // submit button to submit the information to the other servlet
            out.println("<input type = 'submit'  value='Order Now'/>");
            out.println("</form>");
            out.println("</p>");
        } else {
            out.println("<h1>No Recommendations</h1>");
            out.println("<p>No choice was made.</p>");
        }

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

}