/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.InputUtility;

/**
 *
 * @author jiachuan
 */
public class courseUI {

    public int getMenuChoice() {
        int choice;
        System.out.println("*********************************************");
        System.out.println("|    Welcome to Course Management System    |");
        System.out.println("*********************************************");
        System.out.println("| 1. Add a programme to a course            |");
        System.out.println("| 2. Remove a programme from a course       |");
        System.out.println("| 3. Add a new course to a programme        |");
        System.out.println("| 4. Remove a course to a programme         |");
        System.out.println("| 5. Search course offer in a semester      |");
        System.out.println("| 6. Amend course detials for a programme   |");
        System.out.println("| 7. List course taken by different faculty |");
        System.out.println("| 8. List all course for a programme        |");
        System.out.println("| 9. Generate Summary Report                |");
        System.out.println("| 10. Exit to main menu                     |");
        System.out.println("*********************************************");
        choice = InputUtility.promptIntInput("Please enter a choice : ");
        return choice;
    }

    public String inputProgrammeCode() {
        String programmeCode = InputUtility.promptUpperInput("Enter the programme code : ");
        return programmeCode;
    }

    public String inputProgrammeCode2() {
        String programmeCode = InputUtility.promptUpperInput("Enter the programme you want to add to : ");
        return programmeCode;
    }

    public String inputCourseName() {
        String courseName = InputUtility.promptUpperInput("Enter the course name : ");
        return courseName;
    }

    public String inputNewCourseName() {
        String courseName = InputUtility.promptUpperInput("Enter the new course name : ");
        return courseName;
    }

    public double inputCourseFee() {
        double courseFee = InputUtility.promptDoubleInput("Enter the course Fee : ");
        return courseFee;
    }

    public double inputNewCourseFee() {
        double courseFee = InputUtility.promptDoubleInput("Enter the new course Fee : ");
        return courseFee;
    }

    public String inputcourseType() {
        String courseType = InputUtility.promptUpperInput("Enter the course type : ");
        return courseType;
    }

    public String inputNewCourseType() {
        String courseType = InputUtility.promptUpperInput("Enter the new course type : ");
        return courseType;
    }

    public int inputCourseCreditHours() {
        int name = InputUtility.promptIntInput("Enter the new credit hours: ");
        return name;
    }

    public int inputNewCourseCreditHours() {
        int name = InputUtility.promptIntInput("Enter the new credit hours: ");
        return name;
    }

    public String inputCourseId() {
        String courseCode = InputUtility.promptUpperInput("Enter the course code : ");
        return courseCode;
    }

    public String inputCourseType() {
        String courseType = InputUtility.promptUpperInput("Enter the course status(M = Main / R = Resit / RP = Repeat / E = Elective) : ");
        return courseType;
    }

    public String inputNewCourseTyp() {
        String courseType = InputUtility.promptUpperInput("Enter the new course status(M = Main / R = Resit / RP = Repeat / E = Elective) : ");
        return courseType;
    }

    public String inputNewCourseId() {
        String courseCode = InputUtility.promptUpperInput("Enter the new course code : ");
        return courseCode;
    }

    public String inputAgain() {
        String again = InputUtility.promptUpperInput("Do you want to enter again?(n/N to exit): ");
        return again;
    }

    public String inputFaculty() {
        String faculty = InputUtility.promptUpperInput("Enter the faculty short form : ");
        return faculty;
    }

    public int inputChoice() {
        System.out.println("1. Course Management Report");
        System.out.println("2. Programmme Management Report");
        int choice = InputUtility.promptIntInput("Enter the a choice: ");
        return choice;
    }
}
