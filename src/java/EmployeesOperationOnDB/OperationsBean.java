/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeesOperationOnDB;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ron TheChosen
 */
@ManagedBean
@ViewScoped
public class OperationsBean {

    String date, firstname, lastname, address, city, gender, position;
    int age, empid;
    ArrayList listEmployeesDB;
    private boolean edit = false;
    /**
     * Creates a new instance of OperationsBean
     */
    public OperationsBean() {
    }
    
     public OperationsBean( int empid, String date, String firstname, String lastname, String address, String city, String gender, int age, String position) {
    
        this.empid = empid;
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
        
        return date;
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

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    @PostConstruct
    public void init(){
        listEmployeesDB = retrieveData();
    }
    public ArrayList listEmployees(){
        
        return listEmployeesDB;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }
    
    public ArrayList retrieveData(){
        ArrayList listEmployee = new ArrayList();
        try{
        Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeAppDB","root","")) {
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("select * from employees");
                
                while(res.next()){
                    OperationsBean objEmpl = new OperationsBean();
                    objEmpl.setEmpid(res.getInt("empid"));
                    objEmpl.setDate(res.getString("date"));
                    objEmpl.setFirstname(res.getString("firstname"));
                    objEmpl.setLastname(res.getString("lastname"));
                    objEmpl.setAddress(res.getString("address"));
                    objEmpl.setCity(res.getString("city"));
                    objEmpl.setGender(res.getString("gender"));
                    objEmpl.setAge(res.getInt("age"));
                    objEmpl.setPosition(res.getString("position"));
                    
                    listEmployee.add(objEmpl);
                    
                }  
            
            conn.close();
            }
            
            catch(Exception e){
                
            }
            
        }
        catch(Exception e){
            
        }
        
        
        return listEmployee;
    }
    
    public void editEmployee(OperationsBean opBean){
        
        opBean.setEdit(true);
    }
    public String actualEmployee(OperationsBean actualEmployee){
        actualEmployee.setEdit(false);
        return EmployeesBean.newEmployee(actualEmployee);
    }

     public String deleteEmployee(OperationsBean deleteEmployee){
        deleteEmployee.setEdit(false);
        return EmployeesBean.deleteEmployee(deleteEmployee);
    }
}
