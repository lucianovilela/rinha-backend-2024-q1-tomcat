package br.com.rinhaBackend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.naming.*;

import javax.sql.*;
import java.sql.*;

public class Main extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/MyPostgresDB");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from clientes");
            out.println("[");
            while(rs.next()){
                out.printf("{%s:\"%s\",","id", rs.getString("id"));
                out.printf("%s:\"%s\",","nome", rs.getString("nome"));
                out.printf("%s:%s},","limite", rs.getString("limite"));
                

            }
            out.println("]");
            stmt.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
