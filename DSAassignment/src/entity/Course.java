/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.HashMap;
import adt.HashMapInterface;
import adt.HashMapInterface.Entry;
import java.io.Serializable;

/**
 *
 * @author e-default
 */
public class Course implements Serializable {

    private String courseID;
    private String courseName;
    private double courseFee;
    private String courseType;
    private int creditHours;
    private HashMapInterface<String, Programme> linkProgramme;
    private HashMapInterface<String, RegisteredStudent> registeredStudents = new HashMap<>();

    public Course(String courseID, String courseName, double courseFee, String courseType, int creditHours) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.courseType = courseType;
        this.creditHours = creditHours;
        this.linkProgramme = new HashMap<>();
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public void addProgramme(Programme programme) {
        this.linkProgramme.put(programme.getProgrammeID(), programme);
    }

    public void removeProgramme(Programme programme) {
        this.linkProgramme.remove(programme.getProgrammeID());
    }

    public void addRegisteredStudent(RegisteredStudent registeredStudent) {
        if (!registeredStudents.containsKey(registeredStudent.getStudent().getStudentID())) {
            registeredStudents.put(registeredStudent.getStudent().getStudentID(), registeredStudent);
            registeredStudent.getStudent().enrollInCourse(this);
        }
    }

    public boolean removeRegisteredStudent(String studentID) {
        if (registeredStudents.containsKey(studentID)) {
            RegisteredStudent registeredStudent = registeredStudents.get(studentID);
            Student student = registeredStudent.getStudent();
            registeredStudents.remove(studentID);
            student.dropCourse(this);
            return true;
        } else {
            return false;
        }
    }

    public String getAllStudentsAsString() {
        StringBuilder sb = new StringBuilder();
        if (registeredStudents.isEmpty()) {
            sb.append("No students registered for this course.");
        } else {
            sb.append("Registered students for course ").append(courseID).append(":\n");
            for (HashMapInterface.Entry<String, RegisteredStudent> entry : registeredStudents.entries()) {
                RegisteredStudent registeredStudent = entry.getValue();
                Student student = registeredStudent.getStudent();
                sb.append("Student ID: ").append(student.getStudentID()).append(", Name: ").append(student.getName()).append("\n");
            }
        }
        return sb.toString();
    }

    public HashMapInterface<String, RegisteredStudent> getRegisteredStudents() {
        return registeredStudents;
    }
    
    public boolean isStudentRegistered(Student student) {
        for (Entry<String, RegisteredStudent> entry : registeredStudents.entries()) {
            if (entry.getValue().getStudent().equals(student)) {
                return true; // Student is already registered
            }
        }
        return false; // Student is not registered
    }
    
public void printAllStudent(HashMapInterface<String, RegisteredStudent> hashmap) {
    for (HashMapInterface.Entry<String, RegisteredStudent> entry : hashmap.entries()) {
        RegisteredStudent registeredStudent = entry.getValue();
        Student student = registeredStudent.getStudent();
        System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getName());
    }
}

    @Override
    public String toString() {
          // Define the format for the table
        String format = "| %-12s| %-47s| %-9s| %-12d|";
        // Create the header
        StringBuilder sb = new StringBuilder();
        // Add the data
        sb.append(String.format(format, courseID, courseName, courseType,creditHours));
        return sb.toString();
    }
}

//    public void addRegisteredStudent(RegisteredStudent registeredStudent) {
//        // Check if the student is already registered in the course
//        if (registeredStudents.containsKey(registeredStudent.getStudent().getStudentID())) {
//            System.out.println("Student is already registered in this course.");
//        } else {
//            // Add the registered student to the course
//            registeredStudents.put(registeredStudent.getStudent().getStudentID(), registeredStudent);
//            registeredStudent.getStudent().enrollInCourse(this);
//            System.out.println("Student successfully added to the course.");
//        }
//    }
//    
//    public boolean removeRegisteredStudent(String studentID) {
//    if (registeredStudents.containsKey(studentID)) {
//        RegisteredStudent registeredStudent = registeredStudents.get(studentID);
//        Student student = registeredStudent.getStudent();
//
//        // Remove the student from the list of registered students for this course
//        registeredStudents.remove(studentID);
//
//        // Remove this course from the list of courses in which the student is enrolled
//        student.dropCourse(this);
//
//        System.out.println("Student with ID " + studentID + " removed from the course.");
//        return true; // Return true indicating successful removal
//    } else {
//        System.out.println("Student with ID " + studentID + " is not registered in this course.");
//        return false; // Return false indicating student not found
//    }
//}
//     public void listRegisteredStudents() {
//        if (registeredStudents.isEmpty()) {
//            System.out.println("No students registered for this course.");
//        } else {
//            System.out.println("Registered students for course " + courseID + ":");
//            printAllStudent(registeredStudents);
//            }
//        }
//     
//   public void printAllStudent(HashMapInterface<String, RegisteredStudent> hashmap) {
//    for (HashMapInterface.Entry<String, RegisteredStudent> entry : hashmap.entries()) {
//        RegisteredStudent registeredStudent = entry.getValue();
//        Student student = registeredStudent.getStudent();
//        System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getName());
//    }
//}
