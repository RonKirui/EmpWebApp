package EmployeesOperationOnDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ron TheChosen
 */
@ManagedBean
@ViewScoped
public class EmployeesBean {

    static String newEmployee(OperationsBean actualEmployee) {
    
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeAppDB","root","");
                // mysql statement
                String query = "update employees set date=?, firstname=?, lastname=?, address=?, city=?, gender=?, age=?, position=? where empid="+actualEmployee.getEmpid();

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, actualEmployee.getDate());
                preparedStmt.setString(2, actualEmployee.getFirstname());
                preparedStmt.setString(3, actualEmployee.getLastname());
                preparedStmt.setString(4, actualEmployee.getAddress());
                preparedStmt.setString(5, actualEmployee.getCity());
                preparedStmt.setString(6, actualEmployee.getGender());
                preparedStmt.setInt(7, actualEmployee.getAge());
                preparedStmt.setString(8, actualEmployee.getPosition());
                // execute
                preparedStmt.executeUpdate();
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(OperationsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperationsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    return "";
    }

    static String deleteEmployee(OperationsBean deleteEmployee) {
    
    
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmployeesBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeAppDB","root","");
            // mysql statement
            String query = "delete from employees where empid="+deleteEmployee.getEmpid();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            conn.close();
            
        }
        catch (SQLException ex){
            Logger.getLogger(EmployeesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "viewEmployees.xhtml?faces=redirect=true";
    
    }

    String date, firstname, lastname, address, city, gender, position;
    int age;
    /**
     * Creates a new instance of RegEmployeeBean
     */
    public EmployeesBean() {
    }
    public EmployeesBean( String date, String firstname, String lastname, String address, String city, String gender, int age, String position) {
    
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.gender = gender;
        this.age = age;
        this.position = position;
    }
     public String getDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String strDate = formatter.format(date);
        
        return strDate;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getGender(){
        return gender;
    }
    public void setGender( String gender){
        this.gender = gender;
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    
    public String storeData() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeAppDB","root","");
        // mysql statement
        String query = " insert into employees (date, firstname, lastname, address, city, gender, age, position)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?)";

      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString(1, getDate());
      preparedStmt.setString(2, getFirstname());
      preparedStmt.setString(3, getLastname());
      preparedStmt.setString(4, getAddress());
      preparedStmt.setString(5, getCity());
      preparedStmt.setString(6, getGender());
      preparedStmt.setInt(7, getAge());
      preparedStmt.setString(8, getPosition());
      // execute
      preparedStmt.execute();
      conn.close();
        return "addEmployee.xhtml?faces=redirect=true";
    }
}
