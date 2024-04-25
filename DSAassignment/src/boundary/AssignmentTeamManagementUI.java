package boundary;

import java.util.Scanner;

public class AssignmentTeamManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int assignmentTeamMenu() {
        System.out.println("\nAssignment Team Management Menu:");
        System.out.println("--------------------------------------------");
        System.out.println("| 1. Create Assignment Team                  |");
        System.out.println("| 2. Remove Assignment Team                  |");
        System.out.println("| 3. Amend Assignment Team Details           |");
        System.out.println("| 4. Merge Assignment Teams                  |");
        System.out.println("| 5. List Assignment Teams for Tutorial Group|");
        System.out.println("| 6. List Students under Assignment Team     |");
        System.out.println("| 7. Summary Report 1                        |");
        System.out.println("| 8. Summary Report 2                        |");
        System.out.println("| 0. Exit                                    |");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public String assignmentTeamName() {
        System.out.print("Enter Assignment Team Name: ");
        return scanner.next();
    }

    public String tutorialGroup() {
        System.out.print("Enter Tutorial Group: ");
        return scanner.next();
    }
    
    public String maxSize() {
        System.out.print("Enter Max Number of Members ( 1 to 10 ): ");
        return scanner.next();
    }
        
    public String teamMembers() {
        System.out.print("Enter Student Name: ");
        return scanner.nextLine();
    }
    
    public String removeAssignmentTeam() {
        System.out.print("Enter Assignment Team Name to be Removed: ");
        return scanner.next();
    }
    
    public int amendAssignmentTeamDetailsMenu() {
        System.out.println("\nChoice of Team Detail To Amend:");
        System.out.println("--------------------------------------------");
        System.out.println("| 1. Amend Assignment Team Name              |");
        System.out.println("| 2. Amend Assignment Tutorial Group         |");
        System.out.println("| 3. Amend Assignment Team Max Size          |");
        System.out.println("| 4. Amend Assignment Team Members           |");
        System.out.println("| 0. Exit                                    |");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
    
    public int amendAssignmentTeamMembersMenu() {
        System.out.println("\nChoice of Team Detail To Amend:");
        System.out.println("--------------------------------------------");
        System.out.println("| 1. Add Student to Assignment Team          |");
        System.out.println("| 2. Remove Student from Assignment Team     |");
        System.out.println("| 0. Exit                                    |");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
    
    public String currentAssignmentTeam() {
        System.out.print("Enter current assignment team name: ");
        return scanner.next();
    }
    public String amendAssignmentTeam() {
        System.out.print("Enter new assignment team name: ");
        return scanner.next();
    }
    
    public String amendTutorialGroup() {
        System.out.print("Enter new assignment team tutorial group: ");
        return scanner.next();
    }
    
    public String amendAssignmentTeamMaxSize() {
        System.out.print("Enter new assignment team max number of members: ");
        return scanner.next();
    }
    
    public String amendAddAssignmentTeamMembers() {
        System.out.print("Enter new assignment team member: ");
        return scanner.nextLine();
    }
    
    public String listAssignmentTeamUnderTutorialGroup() {
        System.out.print("Enter tutorial group to list assignment teams: ");
        return scanner.next();
    }
    
    public String listTeamMembersUnderAssignmentTeam() {
        System.out.print("Enter assignment team name to list team members: ");
        return scanner.next();
    }
    
    public String addTeamMember() {
        System.out.print("Do you want to add another team member? (yes/no): ");
        return scanner.next();
    }
    public String existingTeamName() {
        System.out.print("Enter the existing assignment team name: ");
        return scanner.next();
    }

    public String clearInputBuffer() {   
 // Read and discard the remaining input
        return scanner.nextLine();
    }

    public String removeTeamMember() {
        System.out.print("Enter the team member you want to remove: ");
        return scanner.nextLine();
    }
    
    public String firstTeamNameToMerge() {
        System.out.print("Enter the name of the first team to merge: ");
        return scanner.next();
    }

    public String secondTeamNameToMerge() {
        System.out.print("Enter the name of the second team to merge: ");
        return scanner.next();
    }

    public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }
    
    public String newMaxSizeAfterMerge() {
        System.out.print("Enter the new max size for the merged team: ");
        return scanner.next();
    }

  /*  public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }

    public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }

    public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }

    public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }

    public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }

    public String newTeamNameAfterMerge() {
        System.out.print("Enter the new team name for the merged team: ");
        return scanner.next();
    }
    
    */
    
            
            
}
