package br.com.rinhaBackend;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.http.HttpServlet;

public abstract class ServletBase extends HttpServlet{

    protected Connection conn;

    @Override
    public void init(){
        try {
            Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/MyPostgresDB");
                conn = ds.getConnection();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
