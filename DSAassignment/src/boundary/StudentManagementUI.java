/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.InputUtility;


/**
 *
 * @author e-default
 */
public class StudentManagementUI {
    
    public int studentMenu(){
    System.out.println("\nStudent Management Menu : ");
    System.out.println("-----------------------------------------------------");
    System.out.println("|1. Add New Student                                 |");
    System.out.println("|2. Remove a Student                                |");
    System.out.println("|3. Amend Student Details                           |");
    System.out.println("|4. Search Student for registered course            |");
    System.out.println("|5. Add Students to Courses                         |");
    System.out.println("|6. Remove Student from Courses                     |");
    System.out.println("|7. Calculate Fee Paid for student                  |");
    System.out.println("|8. Filter Student based on Criteria                |");
    System.out.println("|9. Generate Summary Report 1                       |");
    System.out.println("|10.Generate Summary Report 2                       |");
    System.out.println("|0. Back to Main Menu                               |");
    System.out.println("-----------------------------------------------------");
    System.out.println("");
    int studentChoice = InputUtility.promptIntInput("Please enter a choice : ");
    System.out.println("");
    return studentChoice;
    }
            
    
    
    
    public String programmeCode(){
        String programmeChoice = InputUtility.promptInput("Please enter your programme code (Eg:RSW) : ");
        System.out.println("");
        return programmeChoice;
    }
    
    
    public String RemoveMenu(){
        System.out.println("----------------Remove Student----------------");
        String stdID = InputUtility.promptInput("Enter the Student ID you want to remove : ");
        return stdID;
    }
    
    public String AmendMenu(){
        System.out.println("---------------Amend Student Details----------------");
        String stdID =  InputUtility.promptInput("Enter the Student ID you want to amend : ");
        return stdID;
    }
    
    public int AmendChoiceMenu(){
        System.out.println("--------------Choice of Amend------------------");
        System.out.println("1. Student Name");
        System.out.println("2. Student Programme");
        System.out.println("3. Student Email");
        System.out.println("4. Student Phone Number");
        System.out.println("5. Student Address");
        System.out.println("6. Student Scholarship");
        System.out.println("7. Student Gender");
        System.out.println("0. Previous Page");
        System.out.println("------------------------------------------------");
        int amendChoice = InputUtility.promptIntInput("Enter the Details You want to amend : ");
        return amendChoice;
    }
    public String AmendStudentGender(){
        System.out.println("--------------Amend Student Gender-----------------");
        String gender = InputUtility.promptInput("Please enter Student Gender (male/female)");
        return gender;
    }
    public String AmendStudentName(){
        System.out.println("\n-----------Amend Student Name-----------");
        String newStudentName = InputUtility.promptInput("Please enter Student Name : ");
        return newStudentName;
    }
    
    public String AmendStudentEmail(){
        System.out.println("\n-----------Amend Student Email-----------");
        String newStudentEmail = InputUtility.promptInput("Please enter Student Email : ");
        return newStudentEmail;
    }
    
    public String AmendStudentPhoneNo(){
        System.out.println("---------------Amend Student Phone Number-------------");
        String newStudentPhoneNo = InputUtility.promptInput("Please enter Student Phone Number : ");
        return newStudentPhoneNo;
    }
    
    public String AmendStudentAddress(){
        System.out.println("----------------Amend Student Address----------------");
        String newStudentAddress = InputUtility.promptInput("Please enter Student Address : ");
        return newStudentAddress;
    }
    
    public String addStudentToCourses(){
        String coursesId = InputUtility.promptInput("Please enter Course Code to add student : ");
        return coursesId;
    }
    
    public String addStudentId(){
        String studentId = InputUtility.promptInput("Please enter Student Id to add : ");
        return studentId;
    }
    
    public String searchRegisterStudentCourseCode(){
        String courseCode = InputUtility.promptInput("Enter the course code to search for registered students:");
        return courseCode;
    }
    
    public String removeStudent(){
        String studentId = InputUtility.promptInput("Enter the student ID you want to remove from the course:");
        return studentId;
    }
    
    public String AskcourseCode(){
        String courseCode = InputUtility.promptInput("Enter the course code: ");
        return courseCode;
    }
    
    public String askChoice(){
        String choice = InputUtility.promptInput("Do you want to remove another student? (yes/no)");
        return choice;
    }
    
    public String askStudentId(){
        String studentId = InputUtility.promptInput("Please enter the student Id : ");
        return studentId;
    }
    
    public int askScholarShips(){
        System.out.println("---------------------ScholarShips--------------");
        System.out.println("1. 100% Scholarships");
        System.out.println("2. 75% Scholarships");
        System.out.println("3. 50% Scholarships");
        System.out.println("4. 25% Scholarships");
        System.out.println("0. No Scholarships Holder");
        System.out.println("-----------------------------------------------");
        int choice = InputUtility.promptIntInput("Please enter your choice : ");
        return choice;
        
    }
    
    public int askFilterChoice(){
        System.out.println("----------------Filter Choice---------------");
        System.out.println("1. Gender");
        System.out.println("2. Programme");
        System.out.println("3. Scholarships");
        System.out.println("---------------------------------------------");
        int choice = InputUtility.promptIntInput("Please enter your choice to filter : ");
        return choice;
    }
    
    public String filterAskGender(){
        String gender = InputUtility.promptInput("Enter gender to filter (male/female): ");
        return gender;
    }
    
    public String filterAskProgrammeCode(){
        String programmeCode = InputUtility.promptInput("Enter programme code to filter : ");
        return programmeCode;
    }
    
    public int askCourseType(){
        System.out.println("-----------------Course Type--------------------");
        System.out.println("1. Main");
        System.out.println("2. Repeat");
        System.out.println("3. Elective");
        System.out.println("4. Resit");
        System.out.println("------------------------------------------------");
        int courseChoice = InputUtility.promptIntInput("Please choose the course type : ");
        return courseChoice;
    }
    
    public int continueOrNot(){
        int choice = InputUtility.promptIntInput("Do you want to continue making changes? (1 for yes, 0 to return to menu) : ");
        return choice;
    }
    
}
