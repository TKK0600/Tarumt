/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Programme;
import entity.Student;
import adt.HashMap;
import adt.HashMapInterface;

/**
 *
 * @author jiachuan
 */
public class StudentInitializer {
    
    private static HashMapInterface<String,Student> StudentMap = new HashMap<>();
    
    public StudentInitializer(){
        initialise_Student();
    }
    
    public void initialise_Student(){
        StudentMap.put(new String("A1001"),new Student("A1001","Alex Lee","male",new Programme("RSW","Software Engineering","FOCS"),"alexlee@gmail.com","0128989","BestariJaya","100%"));
        StudentMap.put(new String("A1002"),new Student("A1002","Jeremy Lee","male",new Programme("RSW","Software Engineering","FOCS"),"jeremylhy@gmail.com","01790989","Klang","75%"));
        StudentMap.put(new String("A1003"),new Student("A1003","Jack Ee","male",new Programme("RSW","Software Engineering","FOCS"),"jackeeyf@gmail.com","012535374","BatuPahat","50%"));
        StudentMap.put(new String("A1004"),new Student("A1004","Yee Ting","female",new Programme("RSW","Software Engineering","FOCS"),"ongyeeting2003@gmail.com","012-111-1111","Pekan Nanas","100%"));
        StudentMap.put(new String("A1005"),new Student("A1005","Jason Lim","male",new Programme("RMM","Bachelor of Science (Honours) in Management Mathematics with Computing","FOCS"),"jasonLim@gmail.com","012-000-7777","Penang","0%"));
        StudentMap.put(new String("A1006"),new Student("A1006","SIMILAN","male",new Programme("RBF", "Bachelor of Banking and Finance (Honours)", "FAFB"),"similan2003@gmail.com","012-111-1111","Subang Jaya","0%"));
        
    }
    
    public static HashMapInterface<String, Student> getStudentList() {
        return StudentMap;
    }
    
    public static void printAllValues(HashMapInterface<String, Student> hashmap) {
    for (HashMapInterface.Entry<String, Student> entry : hashmap.entries()) {
        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
    }
    
  public static void main(String[] args) {
    StudentInitializer std = new StudentInitializer();
    HashMapInterface<String, Student> studentMap = std.getStudentList(); // Initialize studentMap with students
    printAllValues(StudentMap);
//    System.out.println("\nStudents :");
//    for (int i = 0; i < studentMap.size(); i++) {
//        HashMap<String, Student>.Entry<String, Student> entry = studentMap.table[i];
//        while (entry != null) {
//            Student student = entry.value;
//            System.out.println("Student ID: " + entry.key);
//            System.out.println("Name: " + student.getName());
//            System.out.println("Email: " + student.getEmail());
//            System.out.println("Phone Number: " + student.getPhoneNumber());
//            System.out.println("Address: " + student.getAddress());
//            System.out.println("Programme: " + student.getProgramme());
//            System.out.println("----------------------------------------------");
//            entry = entry.next;
//        }
//    }
}



    
}
