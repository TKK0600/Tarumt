/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import dao.RegisterCourseStudentInitializer;
import adt.*;
import boundary.StudentManagementUI;
import control.StudentManagementSystem;
import control.TutorialGroupManagmentSystem;
import java.util.InputMismatchException;
import java.util.Scanner;
//import utility.Comparator;

/**
 *
 * @author e-default
 */
public class DSAass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Creating an empty HashMap 
        HashMap<String, Integer> hashMap = new HashMap<>();

//        RegisterCourseStudentInitializer registerInitializer = new RegisterCourseStudentInitializer();
//        registerInitializer.initialize_RegisteredCourseStudent();
        StudentManagementSystem std1 = new StudentManagementSystem();
        StudentManagementUI std = new StudentManagementUI();

        TutorialGroupManagmentSystem tutorialGroupManagement = new TutorialGroupManagmentSystem();

        AssignmentTeamManagementSystem assignmentTeamManagement = new AssignmentTeamManagementSystem();

        CourseManagmentSystem courseManagement = new CourseManagmentSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            try {
                System.out.println("******************************************");
                System.out.println("|      University Management System      |");
                System.out.println("******************************************");
                System.out.println("| 1. Student Management                  |");
                System.out.println("| 2. Tutorial Group Management           |");
                System.out.println("| 3. Course Management                   |");
                System.out.println("| 4. Assignment Management               |");
                System.out.println("| 5. Exit                                |");
                System.out.println("******************************************");
                System.out.print("Please enter your choice : ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        int studentChoice = std.studentMenu();
                        std1.studentChoice(studentChoice);
                        break;
                    case 2:
                        tutorialGroupManagement.runTutorialGroupManagement();
                        break;
                    case 3:
                        courseManagement.startCourseManagement();
                        break;
                    case 4:
                        assignmentTeamManagement.runAssignmentTeamManagement();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid Input, Please Try Again");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a valid number.");
                scanner.next(); // Consume the invalid input to avoid an infinite loop
                choice = 0; // Set choice to an invalid value to re-enter the loop
            }
        } while (choice != 5);

        scanner.close();
    }
}
