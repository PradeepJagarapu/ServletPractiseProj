package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Edit
 */
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("uname");
		try {
			Connection con=DBConnection.getCon();
			String query="select * from users where name=?";
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				pw.println("<form action='Update' method='post'>");
				pw.println("<input type=\"text\" name=\"name\" readonly value="+rs.getString(1)+">");
				pw.println("<input type=\"text\" name=\"pwd\" value="+rs.getString(2)+">");
				pw.println("<input type=\"text\" name=\"email\" value="+rs.getString(3)+">");
				pw.println("<input type=\"text\" name=\"phno\" value="+rs.getInt(4)+">");
				pw.println("<input type=\"submit\">");
				pw.println("</form>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
