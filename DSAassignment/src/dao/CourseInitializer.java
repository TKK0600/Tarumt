///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package dao;
//
import entity.Course;
import adt.HashMap;
import adt.HashMapInterface;

/**
 *
 * @author e-default
 */
public class CourseInitializer {
    private static HashMapInterface<String, Course> CourseList = new HashMap<>();
    
    public CourseInitializer(){
        initialise_Course();
    }
    
    public void initialise_Course(){
        CourseList.put("BACS2042", new Course("BACS2042", "Research Method",518.00,"M", 2));
       CourseList.put("BACS2063", new Course("BACS2063", "Data Structure and Algorithms", 777.00,"M",3));
        CourseList.put("BACS2163", new Course("BACS2163", "Software engineering",777.00,"M", 3));
        CourseList.put("BACS1013", new Course("BACS1013", "PROBLEM SOLVING AND PROGRAMMING",777.00,"M", 3));
        CourseList.put("BACS1024", new Course("BACS1024", "INTRODUCTION TO COMPUTER SYSTEMS",777.00,"M", 3));
        CourseList.put("BAMS1613", new Course("BAMS1613", "PROBABILITY AND STATISTICS",777.00,"M", 3));
        CourseList.put("BAIT1023", new Course("BAIT1023", "WEB DESIGN AND DEVELOPMENT",777.00,"M", 3));
        CourseList.put("BJEL1013", new Course("BJEL1013", "ENGLISH FOR TERTIARY STUDIES",777.00,"M", 3));
        CourseList.put("BJEL1013[RP]", new Course("BJEL1013[RP]", "ENGLISH FOR TERTIARY STUDIES",777.00,"RP", 3));
        CourseList.put("BACS2063[R]", new Course("BACS2063[R]", "Data Structure and Algorithms", 777.00,"R",3));
        CourseList.put("BACS2063[E]",new Course("BACS2063[E]", "Data Structure and Algorithms", 777.00,"E",3));
    }
    
     public static HashMapInterface<String, Course> getCourseList() {
        return CourseList;
    }
     
     public static void printAllValues(HashMapInterface<String, Course> hashmap) {
    for (HashMapInterface.Entry<String, Course> entry : hashmap.entries()) {
        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
     }
    
 public static void main(String[] args) {
     //ways to call the defined 
    CourseInitializer C = new CourseInitializer();
    HashMapInterface<String, Course> courseList = C.getCourseList();
    printAllValues(CourseList);
    
    
    
    //able to put inside 
     //System.out.println(courseList.get("BACS2023"));   
     //System.out.println(courseList.get("BACS2063"));
     //System.out.println(courseList.get("BAIT1013"));
     //System.out.println(courseList.get("BACS2173"));  
     
    //able to run
    //courseList.printAllValues();
     
     
//    Iterator<String, Course> iterator = courseList.iterator();
//    while (iterator.hasNext()) {
//    //String courseCode = iterator.nextKey();
//    Course course = iterator.nextValue();
//    //System.out.println("Next key: " + courseCode);
 //   System.out.println("Next value: " + course);
//}

//    System.out.println("Courses:");
//    Iterator<String, Course> iterator = courseList.iterator();
//    while (iterator.hasNext()) {
//        String courseCode = iterator.nextKey(); // Retrieve the next key
//        Course course = iterator.nextValue(); // Retrieve the next value
//        System.out.println("Course Code: " + courseCode);
//        System.out.println("Course Name: " + course.getCourseName());
//        System.out.println("Course Fee: " + course.getCourseFee());
//        System.out.println("Course Type: " + course.getCourseType());
//        System.out.println("----------------------------------------------");
//    }
    
    
     
}
}







//Debug
//public class CourseInitializer {
//    private static HashMapInterface<String, Course> courseList = new HashMap<>();
//
//    public CourseInitializer() {
//        initialiseCourses();
//    }
//
//    public void initialiseCourses() {
//        System.out.println("Adding course BACS2042: " + addCourse("BACS2042", "Research Method", 518.00, "M", 2));
//        System.out.println("Adding course BACS2063: " + addCourse("BACS2063", "Data Structure and Algorithms", 777.00, "M", 3));
//        System.out.println("Adding course BACS2163: " + addCourse("BACS2163", "Software engineering", 777.00, "M", 3));
//        System.out.println("Adding course BACS1013: " + addCourse("BACS1013", "PROBLEM SOLVING AND PROGRAMMING", 777.00, "M", 3));
//        System.out.println("Adding course BACS1024: " + addCourse("BACS1024", "INTRODUCTION TO COMPUTER SYSTEMS", 777.00, "M", 3));
//        System.out.println("Adding course BAMS1613: " + addCourse("BAMS1613", "PROBABILITY AND STATISTICS", 777.00, "M", 3));
//        System.out.println("Adding course BAIT1023: " + addCourse("BAIT1023", "WEB DESIGN AND DEVELOPMENT", 777.00, "M", 3));
//        System.out.println("Adding course BJEL1013: " + addCourse("BJEL1013", "ENGLISH FOR TERTIARY STUDIES", 777.00, "M", 3));
//        System.out.println("Adding course BJEL1013[RP]: " + addCourse("BJEL1013[RP]", "ENGLISH FOR TERTIARY STUDIES", 777.00, "RP", 3));
//        System.out.println("Adding course BACS2063[R]: " + addCourse("BACS2063[R]", "Data Structure and Algorithms", 777.00, "R", 3));
//    }
//
//    private boolean addCourse(String courseID, String courseName, double courseFee, String courseType, int courseCredits) {
//        Course course = new Course(courseID, courseName, courseFee, courseType, courseCredits);
//        return courseList.put(courseID, course);
//    }
//
//    public static HashMapInterface<String, Course> getCourseList() {
//        return courseList;
//    }
//
//    public static void printAllValues(HashMapInterface<String, Course> hashmap) {
//        if (hashmap.isEmpty()) {
//            System.out.println("No courses found.");
//        } else {
//            System.out.println("Course List:");
//            for (HashMapInterface.Entry<String, Course> entry : hashmap.entries()) {
//                Course course = entry.getValue();
//                System.out.println("Key: " + entry.getKey() + ", Value: " + course);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        CourseInitializer C = new CourseInitializer();
//        HashMapInterface<String, Course> courseList = C.getCourseList();
//        printAllValues(courseList);
//    }
//}
//
