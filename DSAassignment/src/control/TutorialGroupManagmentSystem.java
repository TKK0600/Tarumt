/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ProgramInitializer;
import dao.StudentInitializer;
import entity.Programme;
import entity.Student;
import adt.ArrayList;
import adt.HashMap;
import adt.HashMapInterface;
import boundary.TutorialGroupManagementUI;
import dao.TutorialGroupInitializer;
import entity.TutorialGroup;
import adt.ArrayListInterface;
import java.util.Scanner;
import utility.MessageUI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;

/**
 *
 * @author Yee Ting
 */
public class TutorialGroupManagmentSystem {
     private static final Scanner scanner = new Scanner(System.in);
    private ArrayListInterface<Student> std;
    
    ProgramInitializer Pinitializer = new ProgramInitializer();
    HashMapInterface<String, Programme> program = Pinitializer.getProgrammeList();
    
    private StudentInitializer initializer = new StudentInitializer();
    private HashMapInterface<String,Student> students = initializer.getStudentList();
    
    private HashMapInterface<String,TutorialGroup> tutorialGroupMap = new HashMap<>();
    
    private final TutorialGroupManagementUI TutorialGroupManagementUI =new TutorialGroupManagementUI();
    
     HashMapInterface<String, TutorialGroup> tutorialGroupList = TutorialGroupInitializer.getTutorialGroupList();
     HashMapInterface<String,TutorialGroup> studentInGroupList =TutorialGroupInitializer.getStudentInGroupList();
     
    ProgramInitializer Programinitializer = new ProgramInitializer();
    HashMapInterface<String, Programme> programList = Programinitializer.getProgrammeList();
    
    public void runTutorialGroupManagement(){
        int choice =0;
        do{
            choice = TutorialGroupManagementUI.getMenuChoice();
       
            TutorialGroupInitializer tgi =new TutorialGroupInitializer();
           tutorialGroupMap=tgi.initializeTutorialGroup();
           TutorialGroupInitializer tsi =new TutorialGroupInitializer();
           tutorialGroupMap=tsi.initializeStudentInGroup();
          
            switch(choice){
            case 0:
                return;
            case 1:
                addNewTutorialGroup();
                break;
            case 2:
                removeTutorialGroupFromProgram();
                break;
            case 3:
                displayTutorialGroups();
               break;
            case 4:
                addStudentToTutorialGroup();
                break;
            case 5:
                removeStudentFromTutorialGroup();
                break;
            case 6:
                changeTutorialGroupForStudent();
                break;
            case 7:
                listStudentsInTutorialGroupAndProgram();
                break;
            case 8:
                mergeTutorialGroups();
                break;
            case 9:
                generateProgramSummaryReport();
                break;
            default:
             MessageUI.displayInvalidChoiceMessage();
        }
        }while (choice != 0);
    }
    
    public void printAllProgramme(HashMapInterface<String, Programme> hashmap) {
        System.out.println("Type of Programme : ");
        System.out.println("+------------+---------------------------------------------------------------------------+--------------+");
        System.out.println("| Programme  | Name                                                                      | Faculty      |");
        System.out.println("+------------+---------------------------------------------------------------------------+--------------+");
        for (HashMapInterface.Entry<String, Programme> entry : hashmap.entries()) {
            System.out.println(entry.getValue());
        }
        //System.out.println("+------------+------------------------------------------------------------------------+--------------+");
    }
        public Programme getProgrammeByCode(String programmeID) {
        return programList.get(programmeID);
    }
  
    public void addNewTutorialGroup() {
    boolean addAnotherGroup=false;

    do {
        printAllProgramme(programList);
        String programCode;
        do {
            programCode = TutorialGroupManagementUI.programmeCode();
            if(programCode.equals("-1")){
                TutorialGroupManagementUI.printEndingAddmethod();
                return;
            }else  if (getProgrammeByCode(programCode) == null) {
                System.err.println("Invalid programme code. Please enter a valid programme code.");
            }
        } while (getProgrammeByCode(programCode) == null);
        Programme program = getProgramById(programCode);

        String programmeName = program.getProgrammeName(); // Fetch programme name from the program object

        String tutorialGroupName = TutorialGroupManagementUI.inputTutorialGroupName();
        if (tutorialGroupName.equals("-1")) {
            TutorialGroupManagementUI.printEndingAddmethod();
            return;
        }
         String keyToCheck = programCode + "-" + programmeName + "-" + tutorialGroupName;
        if (tutorialGroupMap.containsKey(keyToCheck)) {
            TutorialGroupManagementUI.tutorialNameExist();
            addAnotherGroup = true;
            continue;
        }
        
        int maxCapacity = TutorialGroupManagementUI.inputMaxCapacity();
        if (maxCapacity == -1) {
            TutorialGroupManagementUI.printEndingAddmethod();
            return;
        }

        String key = programCode + "-" + programmeName + "-" + tutorialGroupName;
        TutorialGroup newTutorialGroup = new TutorialGroup(program, tutorialGroupName, maxCapacity);
        tutorialGroupMap.put(key, newTutorialGroup);
        TutorialGroupManagementUI.printGroupSavesuccessfully();

        do {
            String input = TutorialGroupManagementUI.inputYesNo();
            if (input.equalsIgnoreCase("Y")) {
                addAnotherGroup = true;
                break;
            } else if (input.equalsIgnoreCase("N")) {
                addAnotherGroup = false;
                break;
            } else {
                TutorialGroupManagementUI.invalidYesNo();
            }
        } while (true);

    } while (addAnotherGroup);
}

    public void removeTutorialGroupFromProgram() {
       printAllProgramme(programList);
       String programCode;
       do {
           programCode = TutorialGroupManagementUI.programmeCode();
           if(programCode.equals("-1")){
               TutorialGroupManagementUI.printEndingRemovemethod();
               return;
           }else  if (getProgrammeByCode(programCode) == null) {
               System.err.println("Invalid programme code. Please enter a valid programme code.");
           }
       } while (getProgrammeByCode(programCode) == null);
       Programme program = getProgramById(programCode);

    if (program != null) { // Ensure the program exists
        String programName = program.getProgrammeName();

        boolean foundProgram = false;

        TutorialGroupManagementUI.printGroup();
        for (HashMapInterface.Entry entry : tutorialGroupMap.entries()) {
            TutorialGroup tutorialGroup = tutorialGroupMap.getValue(entry);
            Programme groupProgramme = tutorialGroup.getProgramme();
            if (groupProgramme.getProgrammeID().equals(programCode) && groupProgramme.getProgrammeName().equals(programName)) {
                foundProgram=true;
                System.out.printf("%-20s%-20s%-75s%-30s%-25s%n", groupProgramme.getProgrammeFac(), groupProgramme.getProgrammeID(), groupProgramme.getProgrammeName(), tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity(), tutorialGroup.getTutorialGroupName());
            }
            System.out.println("\n");
            }

        if (!foundProgram) {
            System.out.println("No tutorial groups found for program " + programCode + " " + programName);
            return;
        }

        String tutorialGroupName = TutorialGroupManagementUI.inputTutorialGroupName();
        String key = programCode + "-" + programName + "-" + tutorialGroupName;

        if(tutorialGroupName.equals("-1")){
            TutorialGroupManagementUI.printEndingRemovemethod();
            
        }else if (tutorialGroupMap.containsKey(key)) {
            tutorialGroupMap.remove(key);

            System.out.println("\nTutorial group with program code " + programCode + " and name " + tutorialGroupName + " has been successfully removed.");
            TutorialGroupManagementUI.printLatestVersion();
            System.out.println("\nTutorial Groups for Program: " + programCode + "  " + programName);
            TutorialGroupManagementUI.printDividingLine();
            TutorialGroupManagementUI.printGroup();
           for (HashMapInterface.Entry entry : tutorialGroupMap.entries()) {
            TutorialGroup tutorialGroup = tutorialGroupMap.getValue(entry);
            Programme groupProgramme = tutorialGroup.getProgramme();
            if (groupProgramme.getProgrammeID().equals(programCode) && groupProgramme.getProgrammeName().equals(programName)) {
                System.out.printf("%-20s%-20s%-75s%-30s%-25s%n", groupProgramme.getProgrammeFac(), groupProgramme.getProgrammeID(), groupProgramme.getProgrammeName(), tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity(), tutorialGroup.getTutorialGroupName());
     }
            System.out.println("\n");
            }
            
        } else if(! tutorialGroupMap.containsKey(key)) {
            System.err.println("Tutorial group with program code " + programCode + " and name " + tutorialGroupName + " does not exist.");
        }
    } else {
        System.err.println("Program with code " + programCode + " does not exist.");
    }
}
    
    public void displayTutorialGroups() {
    printAllProgramme(programList);
       String programCode;
       do {
           programCode = TutorialGroupManagementUI.programmeCode();
           if(programCode.equals("-1")){
               TutorialGroupManagementUI.printEndingListTutorialGroupMethod();
               return;
           }else  if (getProgrammeByCode(programCode) == null) {
               System.err.println("Invalid programme code. Please enter a valid programme code.");
           }
       } while (getProgrammeByCode(programCode) == null);
       
       Programme program = getProgramById(programCode);

     if (program != null) {
        String programName = program.getProgrammeName();
        boolean found = false;
        System.out.println("\nTutorial Groups for Program: " + programCode + "  " + programName);
        TutorialGroupManagementUI.printDividingLine();
        TutorialGroupManagementUI.printGroup();
        for (HashMapInterface.Entry entry : tutorialGroupMap.entries()) {
            TutorialGroup tutorialGroup = tutorialGroupMap.getValue(entry);
            Programme groupProgramme = tutorialGroup.getProgramme();
            if (groupProgramme.getProgrammeID().equals(programCode) && groupProgramme.getProgrammeName().equals(programName)) {
                found = true;
                System.out.printf("%-20s%-20s%-75s%-30s%-25s%n", groupProgramme.getProgrammeFac(), groupProgramme.getProgrammeID(), groupProgramme.getProgrammeName(), tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity(), tutorialGroup.getTutorialGroupName());
            }
            System.out.println("\n");
        }
        if (!found) {
            System.out.println("No tutorial groups found for program " + programCode + " " + programName);
        }
    } else {
        TutorialGroupManagementUI.invalidProgrammeCodeName();
        return;
    }
}
    
public void addStudentToTutorialGroup() {
    printAllProgramme(programList);
    String programCode;
    do {
       programCode = TutorialGroupManagementUI.programmeCode();
       if(programCode.equals("-1")){
           TutorialGroupManagementUI.printEndingAddStudentMethod();
           return;
       } else if (getProgrammeByCode(programCode) == null) {
           System.err.println("Invalid programme code. Please enter a valid programme code.");
       }
    } while (getProgrammeByCode(programCode) == null);
    
    Programme program = getProgramById(programCode);
                
    String tutorialGroupName = TutorialGroupManagementUI.inputTutorialGroupName();
    if(tutorialGroupName.equals("-1")){
       TutorialGroupManagementUI.printEndingAddStudentMethod();
       return;
    }

    if (program != null) {
        String key = program.getProgrammeID() + "-" + program.getProgrammeName() + "-" + tutorialGroupName;
        if (tutorialGroupMap.containsKey(key)) {
            TutorialGroup tutorialGroup = tutorialGroupMap.get(key);
            String studentID;
            
            do {
                TutorialGroupManagementUI.printEnterStudentIdOrOut();
                studentID = TutorialGroupManagementUI.inputStudentID();
                if (!studentID.equals("-1")) {
                    Student student = getStudentById(studentID);
                    if (student != null) {
                        ArrayListInterface<Student> students = tutorialGroup.getStudents();
                        if (students != null) {
                            boolean studentExists = false;
                             for (int i = 0; i < students.size(); i++) {
                                 Student s = students.get(i);
                                if (s.getStudentID().equals(student.getStudentID())) {
                                    System.err.println("Student with ID " + studentID + " is already in a tutorial group.");
                                    studentExists = true;
                                    break;
                                }
                            }
                            if (!studentExists) {
                                tutorialGroup.addStudent(student);
                                tutorialGroupMap.put(key, tutorialGroup); // Update the tutorialGroupMap
                                System.out.println("Student " + student.getName() + " with ID " + studentID + " added to tutorial group " + tutorialGroupName + "\n");
                            }
                        } else {
                            // Handle the case when students is null
                            System.err.println("Error: Students list is null.");
                        }
                    } else {
                        System.err.println("Student with ID " + studentID + " does not exist in the system.");
                    }
                } else {
                    TutorialGroupManagementUI.printEndingAddStudentMethod();
                    return;
                }
                
                if (tutorialGroup.getStudents().size() >= tutorialGroup.getMaxCapacity()) {
                    TutorialGroupManagementUI.printReachedMaxCapacity();
                    tutorialGroupMap.put(key, tutorialGroup);
                    std = tutorialGroup.getStudents();
                    System.out.println("\nStudents in tutorial group " + tutorialGroupName + ":");
                    TutorialGroupManagementUI.printStudentInGroup();
                    for(int i = 0; i < std.size(); i++) {
                        Student student = std.get(i);
                        System.out.printf("%%-5s%-20s%-20s%-20s%-15s%-50s",i+1+".",programCode,student.getStudentID(),student.getName(),tutorialGroup.getTutorialGroupName(),tutorialGroup.getStudents().size()+"/"+tutorialGroup.getMaxCapacity());             
                    }
                    return;
                }
            } while (studentID.equals("-1"));
            tutorialGroupMap.put(key, tutorialGroup);
            std = tutorialGroup.getStudents(); 
            System.out.println("\nStudents in tutorial group " + tutorialGroupName + ":");
            TutorialGroupManagementUI.printStudentInGroup();
            for(int i = 0; i < std.size(); i++) {
                Student student = std.get(i);
                System.out.printf("%-5s%-20s%-20s%-20s%-15s%-50s%n",i+1+".",programCode,student.getStudentID(),student.getName(),tutorialGroup.getTutorialGroupName(),tutorialGroup.getStudents().size()+"/"+tutorialGroup.getMaxCapacity());
            }
        } else {
            System.err.println("Tutorial group with program code " + programCode + " and number " + tutorialGroupName + " does not exist.");
            return;
        }
    } else {
        TutorialGroupManagementUI.invalidProgrammeCodeName();
        return;
    }
}

    public void removeStudentFromTutorialGroup() {
    printAllProgramme(programList);
    String programCode;
    do {
        programCode = TutorialGroupManagementUI.programmeCode();
           if(programCode.equals("-1")){
                TutorialGroupManagementUI.printEndingRemoveSudentFromTutorialGroupmethod();
               return;
           }else  if (getProgrammeByCode(programCode) == null) {
               System.err.println("Invalid programme code. Please enter a valid programme code.");
           }
       } while (getProgrammeByCode(programCode) == null);
    
    Programme program = getProgramById(programCode);

    String tutorialGroupName = TutorialGroupManagementUI.inputTutorialGroupName();
    if (tutorialGroupName.equals("-1")) {
        TutorialGroupManagementUI.printEndingRemoveSudentFromTutorialGroupmethod();
        return;
    }

    if (program != null) {
        String key = programCode + "-" + program.getProgrammeName() + "-" + tutorialGroupName;
        TutorialGroup tutorialGroup = tutorialGroupMap.get(key);

        if (tutorialGroupMap.containsKey(key)) {
            std = tutorialGroup.getStudents();
            System.out.println("\nStudents in tutorial group " + tutorialGroupName + ":");
            TutorialGroupManagementUI.printStudentInGroup();
            for (int i = 0; i < std.size(); i++) {
                Student student = std.get(i);
                System.out.printf("%-5s%-20s%-20s%-20s%-15s%-50s%n", i + 1 + ".", programCode, student.getStudentID(), student.getName(), tutorialGroupName, tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity());
                }
            System.out.println("\nEnter the student's ID to remove from the tutorial class:");
            String studentID = TutorialGroupManagementUI.inputStudentID();
            if (studentID.equals("-1")) {
                TutorialGroupManagementUI.printEndingRemoveSudentFromTutorialGroupmethod();
                return;
            }

            boolean studentRemoved = false;
            for (int i = 0; i < std.size(); i++) {
                Student student = std.get(i);
                if (student != null && student.getStudentID().equals(studentID)) {
                    tutorialGroup.removeStudent(student);
                    System.out.println("Student " + student.getName() + " with ID " + studentID + " removed from tutorial group " + tutorialGroupName);
                    studentRemoved = true;
                    break; // Exit the loop after removing the student
                }
            }

            if (!studentRemoved) {
                System.err.println("Student with ID " + studentID + " is not in the tutorial group " + tutorialGroupName);
            }

            // Print updated list of students
            System.out.println("\nStudents in tutorial group " + tutorialGroupName + ":");
            TutorialGroupManagementUI.printStudentInGroup();
            for (int i = 0; i < std.size(); i++) {
                Student student = std.get(i);
                if (student != null) {
                    System.out.printf("%-5s%-20s%-20s%-20s%-15s%-50s%n", i + 1 + ".", programCode, student.getStudentID(), student.getName(), tutorialGroupName, tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity());
                } else {
                    System.out.println("Encountered null student object at index " + i);
                }
            }
        } else {
            System.err.println("Tutorial group with program code " + programCode + " and number " + tutorialGroupName + " does not exist.");
        }
    } else {
        System.err.println("Invalid program code or name.");
    }
}
    
    public void changeTutorialGroupForStudent() {
    printAllProgramme(programList);
    String oldprogramCode;
    do {
        oldprogramCode = TutorialGroupManagementUI.programmeCode();
           if(oldprogramCode.equals("-1")){
               TutorialGroupManagementUI.printEndingChangeGroupmethod();
               return;
           }else  if (getProgrammeByCode(oldprogramCode) == null) {
               System.err.println("Invalid programme code. Please enter a valid programme code.");
           }
       } while (getProgrammeByCode(oldprogramCode) == null);
    
    Programme program = getProgramById(oldprogramCode);

        if (program == null) {
            System.err.println("Program with code " + oldprogramCode + " does not exist.");
            return;
        }
   
    String oldTutorialGroupName = TutorialGroupManagementUI.inputTutorialGroupName();
    if(oldTutorialGroupName.equals("-1")){
        TutorialGroupManagementUI.printEndingChangeGroupmethod();
        return;
    }
  
     if (program != null) {
         String key = oldprogramCode + "-" + program.getProgrammeName() + "-" + oldTutorialGroupName;   
         
         if (tutorialGroupMap.containsKey(key)) {
            TutorialGroup tutorialGroup = tutorialGroupMap.get(key);
            std = tutorialGroup.getStudents();

            TutorialGroupManagementUI.printStudentInGroup();

           for(int i = 0; i < std.size(); i++) {
               Student student = std.get(i);
               System.out.printf("%-5s%-20s%-20s%-20s%-15s%-50s%n",i+1+".",oldprogramCode,student.getStudentID(),student.getName(),tutorialGroup.getTutorialGroupName(),tutorialGroup.getStudents().size()+"/"+tutorialGroup.getMaxCapacity());
           }

           String studentID = TutorialGroupManagementUI.changeGroupwithStdID();
            if (studentID.equals("-1")) {
                TutorialGroupManagementUI.printEndingChangeGroupmethod();
               return; 
               }
            
            if (students.containsKey(studentID)) {
            for(int i = 0; i < std.size(); i++) {
               Student student = getStudentById(studentID);
               Student studentss = std.get(i);
            
                 String oldKey = oldprogramCode + "-" + program.getProgrammeName() + "-" + oldTutorialGroupName;
           
                if (tutorialGroupMap.containsKey(oldKey)) {
                       TutorialGroup oldTutorialGroup = tutorialGroupMap.get(oldKey);
                       String newTutorialGroupName = TutorialGroupManagementUI.inputNewTutorialGroup();
                       if(newTutorialGroupName.equals("-1")){
                           TutorialGroupManagementUI.printEndingChangeGroupmethod();
                           return;
                       }
                       
                       String newKey = oldprogramCode + "-" + program.getProgrammeName() + "-" + newTutorialGroupName;
                       TutorialGroup newTutorialGroup = tutorialGroupMap.get(newKey);

                       if (tutorialGroupMap.containsKey(newKey)) {

                           oldTutorialGroup.removeStudent(studentss);
                           tutorialGroupMap.put(oldKey, oldTutorialGroup);

                           newTutorialGroup.addStudent(studentss);
                           tutorialGroupMap.put(newKey, newTutorialGroup);
                           
                           System.out.println("Tutorial group for student " + student.getName() + " with ID " + studentID + " changed from " + oldTutorialGroupName + " to " + newTutorialGroupName);
                           String newkKey = oldprogramCode + "-" + program.getProgrammeName() + "-" + newTutorialGroupName;
                           tutorialGroupMap.get(newKey);
                       } else if(!tutorialGroupMap.containsKey(newKey)) {
                           System.err.println("\nTutorial group with program code " + oldprogramCode + " and group " + newTutorialGroupName + " does not exist.");
                       }
                   } else {
                       System.err.println("\nTutorial group with program code " + oldprogramCode + " and group " + oldTutorialGroupName + " does not exist.");
                   }
               }
           }else {
                    System.err.println("Student " + studentID + " is not in the tutorial group " + oldTutorialGroupName);
               }
        } else {
               System.err.println("\nTutorial doesn't exist.");
       }
     }else{
         TutorialGroupManagementUI.invalidProgrammeCodeName();
               return;
     }
}
    
    public void listStudentsInTutorialGroupAndProgram() {
    printAllProgramme(programList);
    String programCode;
      do {
        programCode = TutorialGroupManagementUI.programmeCode();
           if(programCode.equals("-1")){
               TutorialGroupManagementUI.printEndingListStudentInGroupmethod();
               return;
           }else  if (getProgrammeByCode(programCode) == null) {
               System.err.println("Invalid programme code. Please enter a valid programme code.");
           }
       } while (getProgrammeByCode(programCode) == null);
      
      Programme program = getProgramById(programCode);
   

    String tutorialGroupName = TutorialGroupManagementUI.inputTutorialGroupName();
    if(tutorialGroupName.equals("-1")){
        TutorialGroupManagementUI.printEndingListStudentInGroupmethod();
        return;
    }
    
    if (program != null) {
        String key = programCode + "-" + program.getProgrammeName() + "-" + tutorialGroupName;
        
        if (tutorialGroupMap.containsKey(key)) {
            TutorialGroup tutorialGroup = tutorialGroupMap.get(key);
            if (tutorialGroup != null) {
                std = tutorialGroup.getStudents();
                if (std != null) {

                    System.out.println("\nStudents in tutorial group " + tutorialGroupName + " of program " + programCode + " " + program.getProgrammeName() + ":");
                    TutorialGroupManagementUI.printStudentInGroup();
                    for(int i = 0; i < std.size(); i++) {
                        Student student = std.get(i);

                        if (student != null) {
                            System.out.printf("%-5s%-20s%-20s%-20s%-15s%-50s%n", i+1 + ".", programCode, student.getStudentID(),student.getName(), tutorialGroupName, tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity());
                        } else {
                            System.out.println("Encountered null student object at index " + i);
                        }
                    }
                    System.out.println("\nThe total student of " + tutorialGroupName + " : " + tutorialGroup.getStudents().size() + "\n");
                } else {
                    System.out.println("No students found in tutorial group " + tutorialGroupName);
                }
            } else {
                TutorialGroupManagementUI.printTutorialGroupNull();
            }
        } else {
            System.err.println("\nTutorial group with program code " + programCode + " and number " + tutorialGroupName + " does not exist.");
        }
    } else {
        TutorialGroupManagementUI.invalidProgrammeCodeName();
    }
}
    
    public void mergeTutorialGroups() {
    printAllProgramme(programList);
    String programCode;
    do {
        programCode = TutorialGroupManagementUI.programmeCode();
           if(programCode.equals("-1")){
               TutorialGroupManagementUI.printEndingMergeMethod();
               return;
           }else  if (getProgrammeByCode(programCode) == null) {
               System.err.println("Invalid programme code. Please enter a valid programme code.");
           }
       } while (getProgrammeByCode(programCode) == null);
    
    Programme program = getProgramById(programCode);
                    
    boolean found = false;

    if (program != null) {
        TutorialGroupManagementUI.printGroup();

        for (HashMapInterface.Entry entry : tutorialGroupMap.entries()) {
            TutorialGroup tutorialGroup = tutorialGroupMap.getValue(entry);
            Programme programme = tutorialGroup.getProgramme();
            std = tutorialGroup.getStudents();
            if (programme.getProgrammeID().equals(programCode)) {
                found = true;

                System.out.printf("%-20s%-20s%-75s%-30s%-25s%n", program.getProgrammeFac(), program.getProgrammeID(), program.getProgrammeName(), tutorialGroup.getStudents().size() + "/" + tutorialGroup.getMaxCapacity(), tutorialGroup.getTutorialGroupName());
        }
        }

        if (!found) {
            System.err.println("\nNo tutorial groups found for program " + programCode + " " + program.getProgrammeName());
        }

        String firstGroupName = TutorialGroupManagementUI.inputFirstMergeGroup();
        if (firstGroupName.equals("-1")) {
            TutorialGroupManagementUI.printEndingMergeMethod();
            return;
        }
        String key1 = programCode + "-" + program.getProgrammeName() + "-" + firstGroupName;
        if (!tutorialGroupMap.containsKey(key1)) {
            System.err.println("\nTutorial group '" + firstGroupName + "' does not exist.");
            return;
        }

        System.out.print("\nEnter the name of the second tutorial group to merge:");
        String secondGroupName = scanner.nextLine();
        if (secondGroupName.equals("-1")) {
            TutorialGroupManagementUI.printEndingMergeMethod();
            return;
        }
        String key2 = programCode + "-" + program.getProgrammeName() + "-" + secondGroupName;
        if (!tutorialGroupMap.containsKey(key2)) {
            System.err.println("Tutorial group '" + secondGroupName + "' does not exist.");
            return;
        }

        System.out.print("\nEnter the New name for the merged tutorial group:");
        String newGroupName = null;
        boolean validName = false;

        while (!validName) {
            newGroupName = scanner.nextLine();
            if (newGroupName.equals("-1")) {
                TutorialGroupManagementUI.printEndingMergeMethod();
                return;
            }
            String programmeName = program.getProgrammeName();
            String keyToCheck = programCode + "-" + programmeName + "-" + newGroupName;

            if (tutorialGroupMap.containsKey(keyToCheck)) {
                System.err.println("Tutorial group '" + newGroupName + "' already exists.");
                System.out.print("Please use another name:");
                continue;

            } else {
                validName = true;
            }
        }

        System.out.print("\nEnter the Maximum capacity for the merged tutorial group:");
        int newMaxCapacity = 0;
        boolean validCapacity = false;
        while (!validCapacity) {
            try {
                newMaxCapacity = Integer.parseInt(scanner.nextLine());
                validCapacity = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input.");
                System.out.print(" Please enter an integer for the Maximum capacity:");
            }
        }

        if (newMaxCapacity == -1) {
            TutorialGroupManagementUI.printEndingMergeMethod();
            return;
        }

        // Merge the tutorial group:
        ArrayList<Student> mergedStudents = new ArrayList<>();

        // Retrieve students from the first tutorial group
        TutorialGroup firstGroup = tutorialGroupMap.get(key1);
        if (firstGroup != null) {
            mergedStudents.addAll(firstGroup.getStudents());
        }

        // Retrieve students from the second tutorial group
        TutorialGroup secondGroup = tutorialGroupMap.get(key2);
        if (secondGroup != null) {
            mergedStudents.addAll(secondGroup.getStudents());
        }

        if (mergedStudents.size() > newMaxCapacity) {
            System.err.println("Adding all students to the new tutorial group would exceed the maximum capacity.");
            System.err.println("Merging tutorial groups aborted.");
            return;
        }

        // Create a new merged tutorial group
        TutorialGroup mergedGroup = new TutorialGroup(program, newGroupName, newMaxCapacity, mergedStudents);
        tutorialGroupMap.put(programCode + "-" + program.getProgrammeName() + "-" + newGroupName, mergedGroup);

        // Remove original groups only if the merge was successful
        if (firstGroup != null) {
            tutorialGroupMap.remove(key1);
        }
        if (secondGroup != null) {
            tutorialGroupMap.remove(key2);
        }

        // Update std to merged students
        std = mergedGroup.getStudents();

        System.out.println("\nTutorial groups merged successfully.");
    }
}

    public void generateProgramSummaryReport() {
       System.out.println("\n");

       int number = -1; // Default value

       try {
           number = TutorialGroupManagementUI.selectReport();
       } catch (InputMismatchException e) {
           System.err.println("Invalid input. Please enter a valid option.");
           return;
       }

       switch (number) {
           case 1 -> generateProgramSummaryReportx();//generateEnrollmentSummaryReport();
           case 2 -> generateEnrollmentSummaryReport2();
           case -1 -> {
               System.out.println("Ending generateSummaryReport method...");
               return;
           }
           default -> {
               System.err.println("Invalid options.");
               return;
           }
       }
   }

    public Student getStudentById(String studentId) {
        return students.get(studentId);
                }
    
    public Programme getProgramById(String programmeID){
        return programList.get(programmeID);
    }
    
    public boolean isValidProgram(String programCode) {
    // Fetch the program based on the program code
    Programme program = getProgramById(programCode);
    
    // Check if the program exists
    return program != null;
}

    public void displayHighestLowestTutorialGroups(String programCode) {
        int maxGroupSize = Integer.MIN_VALUE;
        String maxGroupName = "";
        int minGroupSize = Integer.MAX_VALUE;
        String minGroupName = "";

        // Iterate over each entry (key-value pair) in the tutorialGroupMap
        for (HashMapInterface.Entry<String, TutorialGroup> entry : tutorialGroupMap.entries()) {
            String key = entry.getKey();
            TutorialGroup tutorialGroup = entry.getValue();
            Programme programme = tutorialGroup.getProgramme();

            if (programme.getProgrammeID().equals(programCode)) {
                int groupSize = tutorialGroup.getStudents().size();

                if (groupSize > maxGroupSize) {
                    maxGroupSize = groupSize;
                    maxGroupName = tutorialGroup.getTutorialGroupName();
                }

                if (groupSize < minGroupSize) {
                    minGroupSize = groupSize;
                    minGroupName = tutorialGroup.getTutorialGroupName();
                }
            }
        }

        if (maxGroupName.isEmpty() || minGroupName.isEmpty()) {
            System.out.println("No tutorial groups found for program " + programCode);
        } else {
            System.out.println("Highest Tutorial Group:" + "\n-> "+maxGroupName + ", Student : " + maxGroupSize);

            System.out.println("\nLowest Tutorial Group:" + "\n-> " +minGroupName + ", Student: " + minGroupSize);
        }
    }

    public void generateEnrollmentSummaryReport2() {
        //TutorialGroupManagementUI.printFacultyName();
        System.out.print("\nEnter faculty name:");
        String facultyName = scanner.nextLine().trim(); // Read input and trim whitespace
        if (facultyName.equals("-1")) {
            System.out.println("Ending SummaryReport method...");
            return;
        }
       boolean facultyExists = false;
        for (HashMap.Entry<String, Programme> entry : programList.entries()) {
            Programme programme = entry.getValue();
            if (programme.getProgrammeFac().equalsIgnoreCase(facultyName)) {
                facultyExists = true;
                break;
            }
        }

        if (!facultyExists) {
            System.err.println("Faculty with name " + facultyName + " does not exist.");
            return;
        }
        // Debugging output to see the input faculty name
        System.out.println("Faculty Name Input: " + facultyName);

        TutorialGroupManagementUI.printTARUMTHeader();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);
        System.out.println("\nGenerated at : " + formattedDateTime);

        HashMapInterface<String, Integer> courseGroupCounts = new HashMap<>(); // Map to store course and group count
        HashMapInterface<String, Integer> courseStudentCounts = new HashMap<>(); // Map to store course and total students

        // Iterate over each tutorial group
        for (HashMapInterface.Entry<String, TutorialGroup> groupEntry : tutorialGroupMap.entries()) {
            TutorialGroup tutorialGroup = groupEntry.getValue();
            Programme programme = tutorialGroup.getProgramme();
            if (programme.getProgrammeFac().equalsIgnoreCase(facultyName)) {
                String courseName = programme.getProgrammeName();
                // Increment the count for the course if it exists, else initialize to 1
                if (courseGroupCounts.containsKey(courseName)) {
                    int currentCount = courseGroupCounts.get(courseName);
                    courseGroupCounts.put(courseName, currentCount + 1);
                } else {
                    courseGroupCounts.put(courseName, 1);
                }

                // Update the total student count for the course
                int totalStudents = tutorialGroup.getStudents().size();
                if (courseStudentCounts.containsKey(courseName)) {
                    int currentTotal = courseStudentCounts.get(courseName);
                    courseStudentCounts.put(courseName, currentTotal + totalStudents);
                } else {
                    courseStudentCounts.put(courseName, totalStudents);
                }

            }
        }

        System.out.printf("%n%-90s%-43s%-25s%n", "Course Name", "Num of Tutorial Groups", "Total Students");
        System.out.printf("%n%-90s%-43s%-25s%n","---------------------------------------------------------------------------","--------------------------------","------------------");

        // Variables to track highest and lowest total students and tutorial groups
        String highestStudentCourse = "";
        int highestStudentCount = 0;
        String lowestStudentCourse = "";
        int lowestStudentCount = Integer.MAX_VALUE;
        String highestGroupCourse = "";
        int highestGroupCount = 0;
        String lowestGroupCourse = "";
        int lowestGroupCount = Integer.MAX_VALUE;

        // Iterate over the entrySet of courseGroupCounts and display course name, group count, and total students
        for (HashMapInterface.Entry<String, Integer> entry : courseGroupCounts.entries()) {
            String courseName = entry.getKey();
            int groupCount = entry.getValue();
            int totalStudents = courseStudentCounts.getOrDefault(courseName, 0);
            System.out.printf("%-90s%-43d%-25d%n", courseName, groupCount, totalStudents);

            // Update highest and lowest student count courses
            if (totalStudents > highestStudentCount) {
                highestStudentCount = totalStudents;
                highestStudentCourse = courseName;
            }
            if (totalStudents < lowestStudentCount) {
                lowestStudentCount = totalStudents;
                lowestStudentCourse = courseName;
            }

            // Update highest and lowest group count courses
            if (groupCount > highestGroupCount) {
                highestGroupCount = groupCount;
                highestGroupCourse = courseName;
            }
            if (groupCount < lowestGroupCount) {
                lowestGroupCount = groupCount;
                lowestGroupCourse = courseName;
            }
        }

        // Display highest and lowest student count courses
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Highest Total Students in a Course: " + highestStudentCourse + " (" + highestStudentCount + " students)");
        System.out.println("\nLowest Total Students in a Course: " + lowestStudentCourse + " (" + lowestStudentCount + " students)");

        // Display highest and lowest group count courses
        System.out.println("\n****************************************************\n");
        System.out.println("Highest Total Tutorial Groups in a Course: " + highestGroupCourse + " (" + highestGroupCount + " groups)");
        System.out.println("\nLowest Total Tutorial Groups in a Course: " + lowestGroupCourse + " (" + lowestGroupCount + " groups)");

        System.out.println("\n\n                                                       END OF THE TUTORIAL GROUP SUMMARY REPORT");
        System.out.println("=======================================================================================================================================================");
   
        System.out.println("Press <Enter> key to continue...");
        scanner.nextLine();
    }
    
    public void generateProgramSummaryReportx() {
        System.out.print("\nEnter Programme Code (E.g. RSW):");
        String programCode = scanner.nextLine();
        if (programCode.equals("-1")) {
           System.out.println("Ending SummaryReport method...");
            return;
        }

        Programme program = getProgramById(programCode);
        if (program == null) {
            System.err.println("Invalid program code.");
            return;
        }

        // Call the method to get male and female counts
        HashMapInterface<String, HashMapInterface<String, Integer>> genderCountsMap = countStudentsByGenderInEachGroup(program.getProgrammeID(), programCode);

        // Display the program summary report
        TutorialGroupManagementUI.printTARUMTHeader();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);
        System.out.println("\nGenerated at : " + formattedDateTime);
        System.out.println("\n");
        System.out.printf("%-20s%-25s%-25s%-25s%-25s%n", "Programme ID", "Tutorial Group", "Male Students", "Female Students", "Total Students");
        System.out.printf("%-20s%-25s%-25s%-25s%-25s%n", "--------------", "------------------", "--------------------", "-------------------", "-------------------");

        // Variables to track highest and lowest total students and their tutorial groups
        String highestGroup = null;
        String lowestGroup = null;
        int highestStudents = Integer.MIN_VALUE;
        int lowestStudents = Integer.MAX_VALUE;

        // Iterate over the genderCountsMap to display the counts and find highest/lowest
        for (HashMapInterface.Entry<String, HashMapInterface<String, Integer>> entry : genderCountsMap.entries()) {
            String groupName = entry.getKey();
            HashMapInterface<String, Integer> genderCounts = entry.getValue();

            int maleCount = genderCounts.getOrDefault("Male", 0);
            int femaleCount = genderCounts.getOrDefault("Female", 0);
            int totalStudents = maleCount + femaleCount;

            // Display male and female counts in one line
            System.out.printf("%-20s%-25s%-25d%-25d%-25d%n", programCode, groupName, maleCount, femaleCount, totalStudents);
            // Update highest and lowest if necessary
            if (totalStudents > highestStudents) {
                highestStudents = totalStudents;
                highestGroup = groupName;
            }
            if (totalStudents < lowestStudents) {
                lowestStudents = totalStudents;
                lowestGroup = groupName;
            }
        }

        // Display highest and lowest total students
        if (highestGroup != null && lowestGroup != null) {
            System.out.println("\n----------------------------------------------------------------------------------------------------------------------\n");

            System.out.println("\nHighest Total Students in a Tutorial Group: " + highestGroup + " (" + highestStudents + " students)");
            System.out.println("\nLowest Total Students in a Tutorial Group: " + lowestGroup + " (" + lowestStudents + " students)");
        } else {
            System.out.println("\nNo tutorial groups found.");
        }

        System.out.println("\n\n                                                            END OF THE PROGRAMME SUMMARY REPORT");
        System.out.println("=======================================================================================================================================================");
    
        System.out.println("Press <Enter> key to continue...");
        scanner.nextLine();
    }

    private HashMapInterface<String, HashMapInterface<String, Integer>> countStudentsByGenderInEachGroup(String programID, String programCode) {
        HashMapInterface<String, HashMapInterface<String, Integer>> genderCountMap = new HashMap<>();

        // Iterate over each tutorial group
        for (HashMapInterface.Entry<String, TutorialGroup> groupEntry : tutorialGroupMap.entries()) {
            TutorialGroup tutorialGroup = groupEntry.getValue();
            Programme programme = tutorialGroup.getProgramme();
            if (programme.getProgrammeID().equals(programID) && programCode.equals(programme.getProgrammeID())) {
                int maleCount = 0;
                int femaleCount = 0;

                // Get the students from the tutorial group
                ArrayListInterface<Student> students = tutorialGroup.getStudents();

                // Iterate over each student in the ArrayList
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    if (student.getGender().equalsIgnoreCase("male")) {
                        maleCount++;
                    } else if (student.getGender().equalsIgnoreCase("female")) {
                        femaleCount++;
                    }
                }

                // Add the counts to the map
                String groupName = tutorialGroup.getTutorialGroupName();
                HashMapInterface<String, Integer> groupGenderCounts = new HashMap<>();
                groupGenderCounts.put("Male", maleCount);
                groupGenderCounts.put("Female", femaleCount);
                genderCountMap.put(groupName, groupGenderCounts);
            }
        }

        return genderCountMap;
    }
    
    

}
