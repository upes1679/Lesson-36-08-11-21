package com.jdbc_demo;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO implements DAO{
    private String connString;

    public StudentDAO(String connString){
        this.connString=connString;
    }

    @Override
    public Student getStudent(String name) {
        try(Connection conn=
                    DriverManager.getConnection(connString);
            PreparedStatement statement=conn.prepareStatement("SELECT * FROM students WHERE name = ?");){
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();

            Student student = null;

            while (resultSet.next()){
                int phone=resultSet.getInt("phone");
                String email=resultSet.getString("email");
                student=new Student(name,phone,email);
            }

            return student;
        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Student> getStudents() {
        return null;
    }

    @Override
    public void createStudent(String name, int phone, String email) {
        try(Connection conn=
                    DriverManager.getConnection(connString);
            PreparedStatement statement=conn.prepareStatement("INSERT INTO `students` values (?,?,?)");){
            statement.setString(1,name);
            statement.setInt(2,phone);
            statement.setString(3,email);

            int rowsAffected= statement.executeUpdate();
            System.out.println(rowsAffected);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
