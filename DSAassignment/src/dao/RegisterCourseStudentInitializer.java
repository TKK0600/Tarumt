///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package dao;
//
import entity.Course;
import entity.RegisteredStudent;
import entity.Student;
import adt.HashMap;
import adt.HashMapInterface;
//
///**
// *
// * @author jiachuan
// */
//public class RegisterCourseStudentInitializer {
//    
//    private static HashMapInterface<String, RegisteredStudent> registeredStudents;
//    
//    CourseInitializer C = new CourseInitializer();
//    HashMapInterface<String, Course> courseList = C.getCourseList();
//    
//     StudentInitializer std = new StudentInitializer();
//    HashMapInterface<String, Student> studentMap = std.getStudentList(); // Initialize studentMap with students
//    
//    public void initialize_RegisteredCourseStudent(){
//      Course course1 = courseList.get("BACS2063");
//    Course course2 = courseList.get("BJEL1013");
//    
//    Student student1 = studentMap.get("A1001"); 
//    Student student2 = studentMap.get("A1002");
//    
//    RegisteredStudent registeredStudent1 = new RegisteredStudent(course1, student1);
//    RegisteredStudent registeredStudent2 = new RegisteredStudent(course2, student2);
//    
//    course1.getRegisteredStudents().put(student1.getStudentID(), registeredStudent1);
//    course2.getRegisteredStudents().put(student2.getStudentID(), registeredStudent2);
//    
//    }
//    
//
//    public RegisterCourseStudentInitializer(HashMapInterface<String, RegisteredStudent> registeredStudents) {
//        this.registeredStudents = registeredStudents;
//    }
//    
//    public static void main(String [] args){
//    
//    
//    // Pass the registeredStudents hashmap to the initializer
//    RegisterCourseStudentInitializer R_initializer = new RegisterCourseStudentInitializer();
//    
//    // Initialize registered students
//    R_initializer.initialize_RegisteredCourseStudent();
//    
//    // Print courseList contents
//    System.out.println("Courses:");
//    for (HashMapInterface.Entry<String, Course> entry : initializer.courseList.entries()) {
//        Course course = entry.getValue();
//        System.out.println("Course ID: " + course.getCourseID() + ", Course Name: " + course.getCourseName());
//    }
//    
//    // Print studentMap contents
//    System.out.println("\nStudents:");
//    for (HashMapInterface.Entry<String, Student> entry : initializer.studentMap.entries()) {
//        Student student = entry.getValue();
//        System.out.println("Student ID: " + student.getStudentID() + ", Student Name: " + student.getName());
//    }
//
//    // Print registeredStudents contents
//    System.out.println("\nRegistered Students:");
//    for (HashMapInterface.Entry<String, RegisteredStudent> entry : registeredStudents.entries()) {
//        RegisteredStudent registeredStudent = entry.getValue();
//        System.out.println("Course ID: " + registeredStudent.getCourse().getCourseID() +
//                ", Student ID: " + registeredStudent.getStudent().getStudentID());
//        // Add more details as needed
//    }
//}
//}

public class RegisterCourseStudentInitializer {

    private HashMapInterface<String, RegisteredStudent> registeredStudents = new HashMap<>();

    CourseInitializer courseInitializer = new CourseInitializer();
    HashMapInterface<String, Course> courseList = courseInitializer.getCourseList();

    StudentInitializer studentInitializer = new StudentInitializer();
    HashMapInterface<String, Student> studentMap = studentInitializer.getStudentList();

    public void addRegisteredStudentToCourse(String courseID, String studentID) {
        // Get the course and student objects using their IDs
        Course course = courseList.get(courseID);
        Student student = studentMap.get(studentID);

        if (course != null && student != null) {
            // Check if the student is already registered in the course
            if (course.isStudentRegistered(student)) {
                System.out.println("Student with ID " + studentID + " is already registered in the course.");
            } else {
                // Create a new RegisteredStudent object
                RegisteredStudent registeredStudent = new RegisteredStudent(course, student);
                // Add the registered student to the course
                course.addRegisteredStudent(registeredStudent);
                // Add the registered student to the registeredStudents hashmap
                registeredStudents.put(studentID, registeredStudent);
                // Print a message indicating success
                System.out.println("Student successfully added to the course.");
            }
        } else {
            System.out.println("Course with ID " + courseID + " or student with ID " + studentID + " not found.");
        }
    }

    public HashMapInterface<String, RegisteredStudent> getRegisteredStudents() {
        return registeredStudents;
    }

    public static void main(String[] args) {
        RegisterCourseStudentInitializer initializer = new RegisterCourseStudentInitializer();
        
        // Add registered students directly using the method
        initializer.addRegisteredStudentToCourse("BACS2063","A1003");
        initializer.addRegisteredStudentToCourse("BACS2063", "A1001");
        initializer.addRegisteredStudentToCourse("BJEL1013", "A1002");
        
        // Print registered students
        System.out.println("\nRegistered Students:");
        for (HashMapInterface.Entry<String, RegisteredStudent> entry : initializer.getRegisteredStudents().entries()) {
            RegisteredStudent registeredStudent = entry.getValue();
            System.out.println("Course ID: " + registeredStudent.getCourse().getCourseID() +
                    ", Student ID: " + registeredStudent.getStudent().getStudentID());
            // Add more details as needed
        }
    }
}
