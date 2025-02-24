package servlets;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		try {
			Connection con=DBConnection.getCon();
			String query="select * from users where name=? && password=?";
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, password);
		
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				String email=rs.getString(3);
				int phno=rs.getInt(4);
				
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				request.setAttribute("phno", phno);
				
				RequestDispatcher rd=request.getRequestDispatcher("Profile.jsp");
				rd.forward(request, response);
			}else {
				response.setContentType("text/html");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");
				rd.include(request, response);
				pw.print("<h2>something went wrong ,login again</h2>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
