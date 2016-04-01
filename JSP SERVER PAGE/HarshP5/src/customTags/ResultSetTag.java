

package customTags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.sql.*;

/**
 * Custom tag handler to display information from sql tables in the HTML page.
 * This class takes in query as input and pushes out data to be displayed in
 * tables.
 */

public class ResultSetTag extends BodyTagSupport {

    private String query;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {

            //getting the oracle driver.
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //establishing connection.
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:db11g", "hpandya", "000001114569");

            //Create a statement to execute the query.
            Statement st = con.createStatement();

            // Execute the query and place the result in the resultset.
            ResultSet rs = st.executeQuery(query);

            //JspContext -  base class of pagecontext
            JspContext ctx = getJspContext();

            // Get the metadata information of the required table.
            ResultSetMetaData rsmd = rs.getMetaData();

            // Getting the column count.
            int count = rsmd.getColumnCount();
            JspFragment f = getJspBody();

            // Print all the values of the resultset.
            while (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    // Creating Column name as attribute name and value as attribute's value.
                    ctx.setAttribute(rsmd.getColumnName(i), rs.getString(i));
                }
                // processing tag and sendintg result to JSPWriter
                f.invoke(out);
            }

            // closing the resultset.
            rs.close();
            // closing the connection.
            con.close();
        } catch (Exception ex) {
            throw new JspException(ex.getMessage());
        }
    }

    //Set the value for attribute query, this will be executed to retrieve data.
    public void setQuery(String query) {
        this.query = query;
    }
}