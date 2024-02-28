package br.com.rinhaBackend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main extends ServletBase {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
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
