package com.jdbc_demo;

import java.util.ArrayList;

public interface DAO {
    Student getStudent(String name);
    ArrayList<Student> getStudents();
    void createStudent(String name, int phone, String email);
}
