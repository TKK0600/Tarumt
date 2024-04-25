/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;
import Entity.TutorialGroup;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utility.InputUtility;
import utility.MessageUI;
/**
 *
 * @author YeeTing
 */
public class TutorialGroupManagementUI {
    private static final Scanner scanner = new Scanner(System.in);
      
        public int getMenuChoice(){
            int choice;
            boolean validChoice =false;
        do{
            System.out.println("\n*******************************************");
            System.out.println("Welcome to Tutorial Group Management System");
            System.out.println("*******************************************");
            System.out.println("\n1.Add Tutorial Group to Programme");
            System.out.println("2.Remove Tutorial Group from Programme");
            System.out.println("3.List all tutorial groups for a programme");
            System.out.println("4.Adding students to a tutorial group");
            System.out.println("5.Remove a student from a tutorial group");
            System.out.println("6.Change the tutorial group for a student");
            System.out.println("7.List all student in a tutorial group and a programme");
            System.out.println("8.Merge tutorial group based on criteria");
            System.out.println("9.Generate Summary Report");
            System.out.println("0.Return to Menu.");
            System.out.print("\nEnter your choice: ");
            if(scanner.hasNextInt()){
                 choice = scanner.nextInt();
                 scanner.nextLine();
                 
                 if(choice >=0 && choice<=9){
                     validChoice=true;
                 }else{
                     MessageUI.displayInvalidChoice();
                 }
            }else{
                MessageUI.displayInvalidChoice();
                scanner.nextLine();
                choice=-1;
            }
        }while(!validChoice);
           return choice;
    }
       
     
       public String inputProgrammeName(){
           System.out.print("\nEnter Programme Name:");
           String name=scanner.nextLine();
           return name;
       }
       
       public String inputTutorialGroupName(){
           System.out.print("\nEnter the Tutorial Group Name:");
           String name =scanner.nextLine();
           return name;
       }
       
       public String inputStudentName() {
        System.out.print("\nEnter Student Name:");
        return scanner.nextLine();
    }
        public String inputStudentID() {
        String studentID = InputUtility.promptInput("\nEnter Student ID:");
        return studentID;
    }
        public String inputStudentGender(){
            scanner.nextLine();
            System.out.print("\nEnter Student Gender:");
            return scanner.nextLine();
        }
        public int inputMaxCapacity() {
        System.out.print("\nEnter maximum capacity for the tutorial group: ");
        return scanner.nextInt();
    }
        public int inputNewMaxCapacity(){
            System.out.print("\n Enter the new group's maximum capacity :");
            return scanner.nextInt();
        }
        
        public String inputYesNo() {
            
            System.out.print("\nDo you want to add another tutorial group? (Y/N) \n");
           scanner.nextLine();
            return scanner.nextLine();
        
    }
        public void invalidYesNo(){
            System.out.println("Invalid input. Please enter Y or N.");
        }
       public String inputFacultyName(){
           System.out.println("\nPlease select the Faculty ID:");
           System.out.println("1.FAFB-Faculty of Accountancy,Finance and Business");
           System.out.println("2.FOCS-Faculty of Computing and Information Technology");
           System.out.print("\nEnter the Faculty ID(E.g. FAFB):");
           return scanner.nextLine();
           
       }
       
       public void printFacultyName(){
           System.out.println("\nPlease select the Faculty ID:");
           System.out.println("1.FAFB-Faculty of Accountancy,Finance and Business");
           System.out.println("2.FOCS-Faculty of Computing and Information Technology");
           
           
       }
       
       public String inputProgramCodeFOCS(){
           System.out.println("\nPlease select the programme code (Faculty:FOCS) :");
           System.out.println("1.RSW - Bachelor of Software Engineering");
           System.out.println("2.RDS - Bachelor of Data Science");
           System.out.println("3.RST - Bachelor of Computer Science(Honours)in Iteractive Software Technology");
           System.out.println("4.RMM - Bachelor of Science (Honours) in Management Mathematics with Computing");
           
            String programCodeFOCS = InputUtility.promptInput("\nEnter Programe ID (E.g. RSW):");
            return programCodeFOCS;
       }
       
          public String inputProgramCodeFAFB(){
           System.out.println("\nPlease select the programme code (Faculty:FAFB) :");
           System.out.println("1.RBF - Bachelor of Banking and Finance (Honours)");
           System.out.println("2.RAC - Bachelor of Accounting (Honours)");
           System.out.println("3.RAF - Bachelor of Business (Honours) Accounting and Finance");
           
           
            String programCodeFAFB = InputUtility.promptInput("\nEnter Programe ID (E.g. RSW):");
            return programCodeFAFB;
       }
        
          public String inputProgrammeCode(){
              String programmeCode = InputUtility.promptInput("\nEnter Programe Code (E.g. RSW):");
              return programmeCode;
          }
          
          public String inputFirstMergeGroup(){
           //   scanner.nextLine();
              String firstGroup=InputUtility.promptInput("\nEnter the name of the first tutorial group to merge:");
              return firstGroup;
          }
          
             public String programmeCode(){
        String programmeChoice = InputUtility.promptInput("Enter Programme code (Eg:RSW) : ");
        System.out.println("");
        return programmeChoice;
    }
          
           public String inputSecondMergeGroup(){
              String firstGroup=InputUtility.promptInput("\nEnter the name of the second tutorial group to merge:");
              return firstGroup;
          }
          
        public String inputNewTutorialGroup(){
           String newTutorialGroup = InputUtility.promptInput("\nEnter the NEW Tutorial Group:");
              return newTutorialGroup;
       }
       
       public void printInputRemoveStudentName(){
            System.out.println("\nEnter the student's name and ID to remove from the tutorial class:");
       }
       
       public void printEndingAddmethod(){
           System.out.println("\nEnding addNewTutorialGroup method...");
       }
       
       public void printEndingAddStudentMethod(){
           System.out.println("\nEnding addStudentToTutorialGroup method...");
       }
       
       public void printEndingListTutorialGroupMethod(){
           System.out.println("\nEnding listTutorialGroupFromProgram method...");
       }
       
       public void tutorialNameExist(){
           System.err.print("\nTutorial group with the same name already exists. Please enter a different name.\n");
       }
       
       public void printTutorialGroupDoesntExit(){
           System.err.println("\nTutorial group does not exist.");
       }

       public void printEndingRemovemethod(){
           System.out.println("\nEnding removeTutorialGroupFromProgram method...");
       }
       
       public void printEndingChangeGroupmethod(){
           System.out.println("\nEnding changeTutorialGroupforStudent...");
       }
       
       public void printEndingRemoveSudentFromTutorialGroupmethod(){
           System.out.println("\nEnding removeStudentFromTutorialGroup method...");
       }
       
       public void printGroup(){
        System.out.printf("%-20s%-20s%-75s%-30s%-25s%n", "\nFacultyName","ProgramCode", "ProgrameName","CurrentCapacity/MaxCapacity", "TutorialGroupName");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
       }
       
       public void printGroupSavesuccessfully(){
           System.out.println("\nTutorial group added successfully and saved. \n");
       }
       
       public void invalidProgrammeCodeName(){
          System.err.println("\nInvalid program ID or name.");
       }
       
       public void invalidFacultyName(){
            System.err.println("Invalid Faculty Name. Please input again.");
       }
       
      
       public void printEnterStudentIdOrOut(){
           System.out.println("\nEnter the ID of the student you want to add to this tutorial group or enter '-1' to end this method.");

       }
       
       public void printReachedMaxCapacity(){
           System.out.println("The tutorial group has already reached its maximum capacity. Cannot add more students.");
       }
       
       public void printStudentInGroup(){
           System.out.printf("%n%-5s%-20s%-20s%-20s%-15s%-50s","No.","ProgramName","Student ID","Student Name","TutorialGroup","CurrentCapacity/MaxCapacity");
           System.out.println("\n-------------------------------------------------------------------------------------------------------------");
       }
       
       public void printDividingLine(){
           System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
 }
       
       public int selectReport(){
        System.out.println("Please select the Report:");
        System.out.println("1. Tutorial Group Summary Report by Program");
        System.out.println("2. Tutorial Group Summary Report by Faculty");
        System.out.print("\nPlease enter the Report number:");
        return scanner.nextInt();
       }
       
       public void printEndingMergeMethod(){
           System.out.println("\nEnding mergeTutorialGroups method...");
       }
       
       public void printEndingListStudentInGroupmethod(){
           System.out.println("\nEnding listStudentInGroupandProgramme method....");
       }
       
       //                System.out.printf("%-20s%-20s%-30s%-20s%-20d%-30d%n",programme.getProgrammeID(),tutorialGroup.getTutorialGroupName(),female,male,tutorialGroup.getMaxCapacity(),tutorialGroup.getStudents().size());

       public void printStudentinGroupQty(){
        System.out.printf("%n%-20s%-20s%-20s%-20s%-20s", "Program Code","Tutorial Group","Female","Male","CurrentCapacity/MaxCapacity");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------\n");
        
       }
       
       public void printTutorialGroupNull(){
           System.out.println("Tutorial group object is null.");

       }
       
       public void printReport1Title(){
           System.out.println("=========================================================================================================");
           System.out.println("\n                       Report of Total Student in a Tutorial Group and a Programme                     ");
           System.out.println("=========================================================================================================");
                       
       }
       
       public void printReport1Subtitle(){
             System.out.printf("%-20s%-20s%-20s%-20s%-20s", "FacultyName","ProgramCode","Tutorial Group", "MaxCpacity","Quantity of Student");
             System.out.println("\n--------------------------------------------------------------------------------------------------------");
                     
       }
       
       public void printLatestVersion(){
           System.out.println("Here is the latest version for your review:(If empty mean in programme doesn't has any tutorial group.");

       }
       
       public String changeGroupwithStdID(){
           String studentID = InputUtility.promptInput("\nEnter the student ID to change the group : ");
           return studentID;
       }
       
     public void printTARUMTHeader(){
         System.out.println("=======================================================================================================================================================");
         System.out.println("                                                     TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY               ");
         System.out.println("                                                                 TUTORIAL GROUP MANAGEMENT SUBSYSTEM                         ");
         System.out.println("\n");
         System.out.println("                                                                    TUTORIAL GROUP SUMMARY REPORT                             ");
         System.out.println("                                                                  ----------------------------------                           ");
     }

}
