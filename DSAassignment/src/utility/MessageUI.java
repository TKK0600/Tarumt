/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author jiachuan
 */
public class MessageUI {
    public static void displayInvalidChoiceMessage() {
    System.out.println("\nInvalid choice");
  }

  public static void displayExitMessage() {
    System.out.println("\nExiting system");
  }
  
  public static void displayInvalidChoice(){
      System.out.println("Invalid choide.Please enter the number between 0-9");
  }
  
    public static void displayInvalidAssignmentTeam() {
        System.out.println("Assignment Team Name Already Exists. Please Enter a Different Name.");
    }

    public static void displayInvalidTutorialGroup() {
        System.out.println("Invalid! Tutorial Group must be either '1' or '2'. Please Enter Again.");
    }

    public static void displayInvalidMaxSize() {
        System.out.println("Max Number of Members must be between 1 and 10. Please Enter Again.");
    }
    
    public static void displayMaxSizeReached() {
        System.out.println("Maximum Number of Team Members Reached. Unable to add more members.");
    }

    public static void displayInvalidTeamMember() {
        System.out.println("Team Member Can Only Contain Alphabets With or Without Spacing.");
    }

    public static void displayTeamMemberExisted() {
        System.out.println("Team Member Already Exists. Please Enter a Different Team Member.");
    }

    public static void displayInvalidAddAnother() {
        System.out.println("Invalid! Please enter 'yes' or 'no'.");
    }

    public static void displayAssignmentTeamCreated() {
        System.out.println("Assignment Team created successfully.");
    }
       
    public static void displayGoBackAssignmentManagementMenu() {
        System.out.println("Going back to Assignment Management Menu.");
    }

    public static void displayInvalidAssignmentTeamRemoved() {
        System.out.println("\nInvalid Assignment Team! Please enter a valid assignment team name or Enter '0' to go back to Assignment Team Management Menu.");
    }

    public static void displayExiting() {
        System.out.println("Exiting...");
    }
    
    public static void displayInvalidChoiceAmendMenu() {
        System.out.println("Invalid choice. Please enter a number from 0 to 4.");
    }

    public static void displayAssignmentTeamAmended() {
        System.out.println("Assignment Team Name amended successfully.");
    }

    public static void displayAssignmentTeamNotFound() {
        System.out.println("Assignment Team not found.");
    }

    public static void displayAssignmentTutorialGroupAmended() {
        System.out.println("Assignment Tutorial Group amended successfully.");
    }

    public static void displayInvalidMaxSizeAmend() {
        System.out.println("Invalid max size. Please enter a number larger than the current number of team members but less than 10.");
    }

    public static void displayAssignmentMaxSizeAmended() {
        System.out.println("Assignment Team Max Size amended successfully.");
    }

    public static void displayNewMemberAdded() {
        System.out.println("New member added successfully.");
    }

   public static void displayInvalidChoiceAmendTeamMembersMenu() {
        System.out.println("Invalid choice. Please enter a number from 0 to 2.");
    }

    public static void displayInvalidSpecificTeamMember() {
        System.out.println("The specified team member is not in the team.");

    }

     public static void displayAssignmentTeamsMerged() {
        System.out.println("Teams merged successfully.");
    }

    public static void displayAssignmentTeamCannotMerge() {
        System.out.println("Teams cannot be merged. They must have the same tutorial group and max size.");
    }

    public static void displayInvalidAssignmentTeamsToMerge() {
        System.out.println("One or both of the specified teams do not exist.");
    }
    
    public static void displayInvalidMaxSizeAfterMerge(){
        System.out.println("Team Members From Both Teams Exceeded Max Size");
    }
    
     public static void displayInvalidStudent() {
        System.out.println("Student Not Available.");
    }
    public static void displayStudentAlreadyInTeam() {
        System.out.println("Student Has Already Join Other Teams.");
    }
    
    public static void displayMemberOfOtherTeam() {
        System.out.println("Student Has Already Join Other Teams.");
    }

    public static void displayProgrammeTableHead() {
        System.out.println("+------------+-----------------------------------------------------------------------------+------------+");
        System.out.println("|    CODE    | PRROGRAMEE NAME                                                             |  Faculty   |");
        System.out.println("+------------+-----------------------------------------------------------------------------+------------+");
    }

    public static void displayProgrammeTableTail() {
        System.out.println("+------------+-----------------------------------------------------------------------------+------------+");
    }

    public static void displayFacultyTable() {
        System.out.println("+----------------+---------------------------------------------------------------------------+");
        System.out.println("| Faculty Code   | Faculty Name                                                              |");
        System.out.println("+----------------+---------------------------------------------------------------------------+");
    }

    public static void displayCourseTableHead() {
        System.out.println("=========================================================================================");
        System.out.println("|    CODE     | COURSE NAME                                    |  STATUS  | CREDIT HOUR |");
        System.out.println("=========================================================================================");
    }

    public static void displayCourseTableTail() {
        System.out.println("=========================================================================================");
        System.out.println("Course Status: M = Main / R = Resit / RP = Repeat / E = Elective\n");
    }
}
