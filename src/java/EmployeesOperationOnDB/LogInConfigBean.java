/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeesOperationOnDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ron TheChosen
 */
@ManagedBean
@RequestScoped
public class LogInConfigBean {
    
    String username, password;

    /**
     * Creates a new instance of LogInConfigBean
     */
    public LogInConfigBean() {
    }
    
    public LogInConfigBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String loginMethod(){
        String nextFace = "";
        
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmployeesBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeappdb","root","");
            // mysql statement
            String query = "select * from admins where username="+getUsername()+" and password="+getPassword();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            if(res.next()){
                nextFace = "welcomePrimefaces.xhtml?faces=redirect=true";
            }
            else{
                nextFace = "index.xhtml?faces=redirect=true";
            }
            conn.close();
            
        }
        catch (SQLException ex){
            Logger.getLogger(EmployeesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return nextFace;
    }
    

}
