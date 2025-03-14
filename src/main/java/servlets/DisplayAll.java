package servlets;

import jakarta.servlet.ServletException;
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
 * Servlet implementation class DisplayAll
 */
public class DisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");

		try {
			Connection con=DBConnection.getCon();
			String query="select * from users";
			
			PreparedStatement ps=con.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			
			pw.println("<table border=4>");
			pw.println("<tr>");
			pw.println("<th>Name</th>");
			pw.println("<th>Password</th>");
			pw.println("<th>Email</th>");
			pw.println("<th>Phno</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");

			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");	
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getInt(4)+"</td>");
				pw.println("<td><a href='Edit?uname="+rs.getString(1)+"'>edit</a></td>");
				pw.println("<td><a href='Delete?uname="+rs.getString(1)+"'>delete</a></td>");
				pw.println("</tr>");
			}
			
			pw.println("</table>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
