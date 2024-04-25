/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;

/**
 *
 * @author e-default
 */
public class Student {
    private String studentID;
    private String gender;
    private String name;
    private Programme programme;
    private String email;
    private String phoneNumber;
    private String address;
    private String scholarship;
    private ArrayList<Course> registeredCourse;

    public Student(String studentID, String name,String gender, Programme programme, String email, String phoneNumber, String address,String scholarship) {
        this.studentID = studentID;
        this.gender = gender;
        this.name = name;
        this.programme = programme;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.scholarship = scholarship;
        this.registeredCourse = new ArrayList<>();
    }

    public String getScholarship() {
        return scholarship;
    }

    public void setScholarship(String scholarship) {
        this.scholarship = scholarship;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public ArrayList<Course> getEnrolledCourses() {
        return registeredCourse;
    }

    public void enrollInCourse(Course course) {
        registeredCourse.add(course);
    }
    
    public void dropCourse(Course course) {
    registeredCourse.remove(course);
}
    
    
//    public void displayEnrolledCourses() {
//        if (registeredCourse.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("No courses enrolled.");
//        } else {
//            System.out.println("Enrolled Courses:");
//            Course[] coursesArray = registeredCourse.toArray(new Course[0]);
//            for (Course course : coursesArray) {
//                sb.append(course).append("\n"); // Assuming Course class has a meaningful toString() method
//            }
//        }
//    }

    public String displayEnrolledCourses() {
    StringBuilder sb = new StringBuilder();
    if (registeredCourse.isEmpty()) {
        sb.append("No courses enrolled.");
    } else {
        sb.append("Enrolled Courses:\n");
        Course[] coursesArray = registeredCourse.toArray(new Course[0]);
        for (Course course : coursesArray) {
            sb.append(course).append("\n"); // Assuming Course class has a meaningful toString() method
        }
    }
    return sb.toString();
}

    
 @Override
public String toString() {
    String format = "| %-10s | %-25s | %-10s | %-12s | %-25s | %-13s | %-19s | %-10s |\n"
                  + "+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n";
    return String.format(format, studentID, name, gender, programme.getProgrammeID(), email, phoneNumber, address, scholarship);
}



      
}
