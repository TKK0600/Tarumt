/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import boundary.AssignmentTeamManagementUI;
import dao.AssignmentTeamInitializer;
import dao.ProgramInitializer;
import dao.StudentInitializer;
import entity.AssignmentTeam;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import utility.MessageUI;
import adt.ArrayList;
import adt.HashMap;
import adt.HashMapInterface;
import dao.TutorialGroupInitializer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;
import utility.EnumComparator;
import utility.JCComparator;

/**
 *
 * @author user++++
 */
public class AssignmentTeamManagementSystem {

    private StudentInitializer initializer = new StudentInitializer();
    HashMapInterface<String, Student> studentMap = initializer.getStudentList();

    private TutorialGroupInitializer tutorialGroupInitializer = new TutorialGroupInitializer();
    private HashMapInterface<String, TutorialGroup> tutorialGroupMap = tutorialGroupInitializer.initializeTutorialGroup();

    Scanner scanner = new Scanner(System.in);
    private AssignmentTeamManagementUI ui = new AssignmentTeamManagementUI();
    private HashMapInterface<String, AssignmentTeam> assignmentTeams = new HashMap<>();

    public AssignmentTeamManagementSystem() {
        AssignmentTeamInitializer assignmentTeamInitializer = new AssignmentTeamInitializer();
        // Initialize the assignmentTeams HashMap with data from AssignmentTeamInitializer
        HashMapInterface<String, AssignmentTeam> initializedData = assignmentTeamInitializer.getinitializeTeams();

        // Iterate over the entries and add them to the assignmentTeams HashMap
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : initializedData.entries()) {
            assignmentTeams.put(entry.getKey(), entry.getValue());
        }
    }

    public void runAssignmentTeamManagement() {

        boolean exit = false;
        while (!exit) {
            try {
                int choice = ui.assignmentTeamMenu();
                if (isValidChoice(choice)) {
                    switch (choice) {
                        case 0:
                            exit = true;
                            break;
                        case 1:
                            createAssignmentTeam();
                            break;
                        case 2:
                            removeAssignmentTeam();
                            break;
                        case 3:
                            amendAssignmentTeamDetails();
                            break;
                        case 4:
                            mergeAssignmentTeams();
                            break;
                        case 5:
                            listAssignmentTeamNameUnderTutorialGroup();
                            break;
                        case 6:
                            listStudentsUnderTeam();
                            break;
                        case 7:
                            summaryReport1();
                            break;
                        case 8:
                            summaryReport2();
                            break;
                    }
                } else {
                    MessageUI.displayInvalidChoice();
                }
            } catch (InputMismatchException e) {
                MessageUI.displayInvalidChoiceMessage();
                // Clear the input buffer
                ui.clearInputBuffer();
            }
        }
    }

    private boolean isValidChoice(int choice) {
        return choice >= 0 && choice <= 8;
    }

    private void createAssignmentTeam() {
        String assignmentTeam;
        while (true) {
            assignmentTeam = ui.assignmentTeamName();
            if (assignmentTeams.containsKey(assignmentTeam)) {
                MessageUI.displayInvalidAssignmentTeam();
            } else {
                break;
            }
        }

        String tutorialGroup;
        TutorialGroup selectedTutorialGroup = null;
        while (true) {
            tutorialGroup = ui.tutorialGroup();
            // Check if the entered tutorial group name matches an existing tutorial group
            if (!tutorialGroupExists(tutorialGroup)) {
                MessageUI.displayInvalidTutorialGroup();
            } else {
                // Retrieve the TutorialGroup object corresponding to the entered tutorial group name
                selectedTutorialGroup = getTutorialGroupByName(tutorialGroup);
                break;
            }
        }

        String maxSize;
        int maxSizeInt = 0;
        while (true) {
            maxSize = ui.maxSize();
            if (!maxSize.matches("[1-9]|10")) {
                MessageUI.displayInvalidMaxSize();
            } else {
                maxSizeInt = Integer.parseInt(maxSize);
                break;
            }
        }

        // Create AssignmentTeam object with the selected tutorial group
        AssignmentTeam newTeam = new AssignmentTeam(assignmentTeam, selectedTutorialGroup, maxSize);

        ui.clearInputBuffer();
        // Prompt user to enter team members
        while (newTeam.getTeamMembers().size() < maxSizeInt) {
            String member;
            while (true) {
                member = ui.teamMembers();
                if (member.matches("[a-zA-Z ]+")) {
                    break; // Valid input, break the loop
                } else {
                    MessageUI.displayInvalidTeamMember();
                }
            }

            // Check if the new member exists in the student data structure
            if (!studentExists(member)) {
                MessageUI.displayInvalidStudent();
                continue; // Exit if the student does not exist
            }

            // Check if the new member is already in any assignment team
            if (studentAlreadyInTeam(member)) {
                MessageUI.displayMemberOfOtherTeam();
                continue; // Continue the loop to prompt for a different member
            }

            // If all checks pass, add the member to the team
            newTeam.addTeamMember(new Student(null, member, null, null, null, null, null, null));

            String addAnother;
            while (true) {
                addAnother = ui.addTeamMember();
                if (addAnother.equalsIgnoreCase("yes") || addAnother.equalsIgnoreCase("no")) {
                    break; // Valid input, break the loop
                } else {
                    MessageUI.displayInvalidAddAnother();
                }
            }

            if (addAnother.equalsIgnoreCase("no")) {
                break; // Exit the loop if user doesn't want to add another member
            }
        }

        // Add the new team to the existing HashMap
        assignmentTeams.put(assignmentTeam, newTeam);

        MessageUI.displayAssignmentTeamCreated();
    }

    private boolean tutorialGroupExists(String tutorialGroupName) {
        for (HashMapInterface.Entry<String, TutorialGroup> entry : tutorialGroupMap.entries()) {
            if (entry.getValue().getTutorialGroupName().equals(tutorialGroupName)) {
                return true;
            }
        }
        return false;
    }

    private TutorialGroup getTutorialGroupByName(String tutorialGroupName) {
        for (HashMapInterface.Entry<String, TutorialGroup> entry : tutorialGroupMap.entries()) {
            if (entry.getValue().getTutorialGroupName().equals(tutorialGroupName)) {
                return entry.getValue();
            }
        }
        return null; // Return null if the tutorial group is not found
    }

    private void removeAssignmentTeam() {
        while (true) {
            String assignmentTeamToRemove = ui.removeAssignmentTeam();
            if (assignmentTeamToRemove.equals("0")) {
                // If user enters 0, return to assignment management menu
                MessageUI.displayGoBackAssignmentManagementMenu();
                break;
            }
            if (assignmentTeams.containsKey(assignmentTeamToRemove)) {
                assignmentTeams.remove(assignmentTeamToRemove);
                System.out.println("Assignment Team '" + assignmentTeamToRemove + "' removed successfully.");
                break;
            } else {
                MessageUI.displayInvalidAssignmentTeamRemoved();
            }
        }
    }

    private void amendAssignmentTeamDetails() {
        int choice;
        do {
            try {
                choice = ui.amendAssignmentTeamDetailsMenu();
                switch (choice) {
                    case 0:
                        MessageUI.displayExiting();
                        return;
                    case 1:
                        amendAssignmentTeamName();
                        break;
                    case 2:
                        amendAssignmentTutorialGroup();
                        break;
                    case 3:
                        amendAssignmentTeamMaxSize();
                        break;
                    case 4:
                        amendTeamMembers();
                        break;
                    default:
                        MessageUI.displayInvalidChoiceAmendMenu();
                }
            } catch (InputMismatchException e) {
                // Display error message for non-numeric input
                MessageUI.displayInvalidChoiceAmendMenu();
                // Clear input buffer
                ui.clearInputBuffer();
                choice = -1; // Reset choice to force loop continuation
            }
        } while (choice != 0);
    }

    private void amendAssignmentTeamName() {
        String existingTeamName = ui.currentAssignmentTeam(); // Prompt for the existing team name
        AssignmentTeam team = assignmentTeams.get(existingTeamName); // Retrieve the team from the data structure

        if (team != null) {
            String newTeamName;
            boolean validNewTeamName = false;

            // Loop until a valid new team name is provided
            while (!validNewTeamName) {
                newTeamName = ui.amendAssignmentTeam(); // Prompt for the new team name

                // Check if the new team name already exists in the hashmap
                if (assignmentTeams.containsKey(newTeamName)) {
                    MessageUI.displayInvalidAssignmentTeam(); // Display message for invalid team name
                } else {
                    // Team names are different, proceed with removal and insertion
                    assignmentTeams.remove(existingTeamName); // Remove the team from the data structure using the existing name
                    team.setAssignmentTeamName(newTeamName); // Update the team name
                    assignmentTeams.put(newTeamName, team); // Insert the team back into the data structure with the new name
                    MessageUI.displayAssignmentTeamAmended();
                    validNewTeamName = true; // Set flag to exit loop
                }
            }
        } else {
            MessageUI.displayAssignmentTeamNotFound();
        }
    }

    private void amendAssignmentTutorialGroup() {
        // Prompt user to enter the existing assignment team name     
        String existingTeamName = ui.existingTeamName();

        // Retrieve the team from the data structure
        AssignmentTeam team = assignmentTeams.get(existingTeamName);

        if (team != null) {
            // Prompt user for the new tutorial group
            String newTutorialGroup = ui.amendTutorialGroup();

            // Check if the new tutorial group is valid
            if (tutorialGroupExists(newTutorialGroup)) {
                // Update the tutorial group
                team.setTutorialGroup(newTutorialGroup);
                MessageUI.displayAssignmentTutorialGroupAmended();
            } else {
                MessageUI.displayInvalidTutorialGroup();
            }
        } else {
            MessageUI.displayAssignmentTeamNotFound();
        }
    }

    private void amendAssignmentTeamMaxSize() {
        // Prompt user to enter the existing assignment team name
        String existingTeamName = ui.existingTeamName();

        // Retrieve the team from the data structure
        AssignmentTeam team = assignmentTeams.get(existingTeamName);

        if (team != null) {
            // Prompt user for the new max size
            String newMaxSize;
            boolean validMaxSize = false;
            do {
                newMaxSize = ui.amendAssignmentTeamMaxSize();
                // Check if the new max size is greater than or equal to the current number of team members
                if (Integer.parseInt(newMaxSize) >= team.getTeamMembers().size() && Integer.parseInt(newMaxSize) <= 10) {
                    validMaxSize = true;
                } else {
                    MessageUI.displayInvalidMaxSizeAmend();
                }
            } while (!validMaxSize);

            // Update the max size
            team.setMaxSize(newMaxSize);
            MessageUI.displayAssignmentMaxSizeAmended();
        } else {
            MessageUI.displayAssignmentTeamNotFound();
        }
    }

    private void amendTeamMembers() {
        int choice;
        do {
            try {
                choice = ui.amendAssignmentTeamMembersMenu();
                switch (choice) {
                    case 0:
                        MessageUI.displayExiting();
                        return;
                    case 1:
                        addStudentToAssignmentTeam();
                        break;
                    case 2:
                        removeStudentFromAssignmentTeam();
                        break;
                    default:
                        MessageUI.displayInvalidChoiceAmendTeamMembersMenu();
                }
            } catch (InputMismatchException e) {
                // Display error message for non-numeric input
                MessageUI.displayInvalidChoiceAmendTeamMembersMenu();
                // Clear input buffer
                ui.clearInputBuffer();
                choice = -1; // Reset choice to force loop continuation
            }
        } while (choice != 0);
    }

    private void addStudentToAssignmentTeam() {
        sortedStudent();
        // Prompt user to enter the existing assignment team name
        String existingTeamName = ui.existingTeamName();
        // Retrieve the team from the data structure
        AssignmentTeam team = assignmentTeams.get(existingTeamName);

        if (team != null) {
            // Check if the team has reached its maximum size
            if (team.getTeamMembers().size() >= Integer.parseInt(team.getMaxSize())) {
                MessageUI.displayMaxSizeReached();
                return;
            }
            ui.clearInputBuffer();
            // Prompt user to enter new team member
            String newMemberName = ui.amendAddAssignmentTeamMembers();
            if (!newMemberName.isEmpty()) {
                // Check if the new member already exists in the team
                boolean memberExists = false;
                ArrayList<Student> teamMembers = team.getTeamMembers();
                for (int i = 0; i < teamMembers.size(); i++) {
                    Student member = teamMembers.get(i);
                    if (member.getName().equalsIgnoreCase(newMemberName)) {
                        MessageUI.displayTeamMemberExisted();
                        memberExists = true;
                        break;
                    }
                }
                if (!memberExists) {
                    // Check if the new member exists in the student data structure
                    if (!studentExists(newMemberName)) {
                        MessageUI.displayInvalidStudent();
                        return; // Exit if the student does not exist
                    }

                    // Check if the new member is already in another team
                    if (studentAlreadyInTeam(newMemberName)) {
                        MessageUI.displayStudentAlreadyInTeam();
                        return; // Exit if the student is already in another team
                    }

                    // Create a new Student object for the new member
                    Student newMember = new Student(null, newMemberName, null, null, null, null, null, null); // Assuming Student class has appropriate constructor
                    // Add the new member to the team
                    team.addTeamMember(newMember);
                    MessageUI.displayNewMemberAdded();
                }
            }

            // Prompt user if they want to add one more member
            String addMore;
            do {
                addMore = ui.addTeamMember();
            } while (!addMore.equalsIgnoreCase("yes") && !addMore.equalsIgnoreCase("no"));

            if (addMore.equalsIgnoreCase("yes")) {
                // Recursively call the method to add another member
                addStudentToAssignmentTeam();
            }

            // Update the assignmentTeams map with the modified team object
            assignmentTeams.put(existingTeamName, team);
        } else {
            MessageUI.displayAssignmentTeamNotFound();
        }
    }

// Method to check if the student exists in the student data structure
    private boolean studentExists(String studentName) {
        for (HashMapInterface.Entry<String, Student> entry : studentMap.entries()) {
            if (entry.getValue().getName().equalsIgnoreCase(studentName)) {
                return true;
            }
        }
        return false;
    }

// Method to check if the student is already in assignment team
    private boolean studentAlreadyInTeam(String studentName) {
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : assignmentTeams.entries()) {
            ArrayList<Student> teamMembers = entry.getValue().getTeamMembers();
            for (int i = 0; i < teamMembers.size(); i++) {
                Student member = teamMembers.get(i);
                if (member.getName().equalsIgnoreCase(studentName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeStudentFromAssignmentTeam() {
        // Prompt user to enter the existing assignment team name
        String existingTeamName = ui.existingTeamName();

        // Retrieve the team from the data structure
        AssignmentTeam team = assignmentTeams.get(existingTeamName);

        if (team == null) {
            System.out.println("Assignment team '" + existingTeamName + "' not found.");
            return;
        }

        ui.clearInputBuffer();
        // Prompt user to enter the name of the student they want to remove
        String studentNameToRemove = ui.removeTeamMember(); // Trim whitespace

        // Check if the student name to remove is empty
        if (studentNameToRemove.isEmpty()) {
            System.out.println("Invalid student name.");
            return;
        }

        // Check if the student is a member of the team
        boolean studentRemoved = false;
        for (int i = 0; i < team.getTeamMembers().size(); i++) {
            Student member = team.getTeamMembers().get(i);
            if (member.getName().equalsIgnoreCase(studentNameToRemove)) {
                // Remove the student from the team
                team.getTeamMembers().remove(i);
                System.out.println("Student '" + studentNameToRemove + "' removed successfully from team '" + existingTeamName + "'.");
                studentRemoved = true;
                break; // Exit the loop after removing the student
            }
        }

        // Check if the student was not found in the team
        if (!studentRemoved) {
            System.out.println("Student '" + studentNameToRemove + "' is not a member of team '" + existingTeamName + "'.");
        } else {
            // Check if all team members are removed
            if (team.getTeamMembers().isEmpty()) {
                // If all team members are removed, remove the whole team
                assignmentTeams.remove(existingTeamName);
                System.out.println("The team '" + existingTeamName + "' has been removed since all members were removed.");
            }
        }
    }

    private void mergeAssignmentTeams() {
        // Prompt user to enter the names of the two teams to merge
        String teamName1, teamName2;
        AssignmentTeam team1, team2;

        // Get the first team and validate its existence
        do {
            teamName1 = ui.firstTeamNameToMerge();
            team1 = assignmentTeams.get(teamName1);
            if (team1 == null) {
                MessageUI.displayInvalidAssignmentTeamsToMerge();
            }
        } while (team1 == null);

        // Get the second team and validate its existence
        do {
            teamName2 = ui.secondTeamNameToMerge();
            team2 = assignmentTeams.get(teamName2);
            if (team2 == null) {
                MessageUI.displayInvalidAssignmentTeamsToMerge();
            }
        } while (team2 == null);

        // Check if both teams exist and have the same tutorial group
        if (team1 != null && team2 != null && haveSameTutorialGroup(team1, team2)) {
            // Prompt user to enter the new team name for the merged team
            String newTeamName;
            boolean isNewTeamNameValid;
            do {
                newTeamName = ui.newTeamNameAfterMerge();
                isNewTeamNameValid = !assignmentTeams.containsKey(newTeamName);
                if (!isNewTeamNameValid) {
                    MessageUI.displayTeamMemberExisted();
                }
            } while (!isNewTeamNameValid);

            // Prompt user to enter the new max size
            String newMaxSize;
            boolean isNewMaxSizeValid;
            do {
                newMaxSize = ui.newMaxSizeAfterMerge();
                isNewMaxSizeValid = validateMaxSize(team1, team2, newMaxSize);
                if (!isNewMaxSizeValid) {
                    MessageUI.displayInvalidMaxSizeAfterMerge();
                }
            } while (!isNewMaxSizeValid);

            // Retrieve students from team 1 and add them to team 2
            team2.getTeamMembers().addAll(team1.getTeamMembers());

            // Store the merged team into the existing data structure
            assignmentTeams.put(newTeamName, team2);

            // Remove the old teams from the data structure
            assignmentTeams.remove(teamName1);
            assignmentTeams.remove(teamName2);

            MessageUI.displayAssignmentTeamsMerged();
        } else {
            MessageUI.displayAssignmentTeamCannotMerge();
        }
    }

    private boolean validateMaxSize(AssignmentTeam team1, AssignmentTeam team2, String newMaxSize) {
        int totalMembers = team1.getTeamMembers().size() + team2.getTeamMembers().size();
        int maxSize = Integer.parseInt(newMaxSize);
        return totalMembers <= maxSize && maxSize <= 10;
    }

    // Method to check if two teams have the same tutorial group
    private boolean haveSameTutorialGroup(AssignmentTeam team1, AssignmentTeam team2) {
        return team1.getTutorialGroup().getTutorialGroupName().equals(team2.getTutorialGroup().getTutorialGroupName());
    }

    private void listAssignmentTeamNameUnderTutorialGroup() {
        // Prompt user to enter the tutorial group
        String tutorialGroup = ui.tutorialGroup();

        // Flag to check if any team in the specified tutorial group is found
        boolean teamFound = false;

        // Iterate through all assignment teams
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : assignmentTeams.entries()) {
            AssignmentTeam team = entry.getValue();
            // Check if the tutorial group of the current team matches the one entered by the user
            if (team.getTutorialGroup().getTutorialGroupName().equals(tutorialGroup)) {
                // Display the team name
                System.out.println("Team Name: " + entry.getKey());
                teamFound = true;
            }
        }

        // If no teams are found in the specified tutorial group, display a message
        if (!teamFound) {
            System.out.println("No assignment teams found in tutorial group " + tutorialGroup);
        }
    }

    private void listStudentsUnderTeam() {
        // Prompt user to enter the team name
        String teamName = ui.listTeamMembersUnderAssignmentTeam();

        // Retrieve the team from the data structure
        AssignmentTeam team = assignmentTeams.get(teamName);

        // Check if the team exists
        if (team != null) {
            // Display team members
            adt.ArrayList<Student> teamMembers = team.getTeamMembers();
            if (teamMembers.isEmpty()) {
                System.out.println("The team '" + teamName + "' has no members.");
            } else {
                System.out.println("Team Members of '" + teamName + "':");
                for (int i = 0; i < teamMembers.size(); i++) {
                    Student member = teamMembers.get(i);
                    if (member != null) {
                        System.out.println(member.getName());
                    }
                }
            }
        } else {
            MessageUI.displayAssignmentTeamNotFound();
        }
    }

    private void summaryReport1() {
        // Get the current date and time
        Date currentDate = new Date();
        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);

        // Print the summary report
        System.out.println("=======================================================================================================================");
        System.out.println("                              Tunku Abdul Rahman University of Management and Technology                               ");
        System.out.println("                                         ASSIGNMENT TEAM MANAGEMENT SUBSYSTEM                                          ");
        System.out.println("                                                                                                                       ");
        System.out.println("                                            ASSIGNMENT TEAM SUMMARY REPORT                                             ");
        System.out.println("                                                                                                                       ");
        System.out.println("Generated at: " + formattedDateTime);
        System.out.println("=======================================================================================================================");
        System.out.println("| Assignment Team Name             | Tutorial Group                           | Number of Members in Assignment Team  |");

        // Variables to store the most and least number of team members
        int mostMembers = Integer.MIN_VALUE;
        int leastMembers = Integer.MAX_VALUE;
        String mostMembersTeam = "";
        String leastMembersTeam = "";

        // Iterate through all assignment teams
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : assignmentTeams.entries()) {
            String teamName = entry.getKey();
            AssignmentTeam team = entry.getValue();
            int numMembers = team.getTeamMembers().size();
            String tutorialGroupName = team.getTutorialGroup().getTutorialGroupName();

            // Update most and least members if necessary
            if (numMembers > mostMembers) {
                mostMembers = numMembers;
                mostMembersTeam = teamName;
            }
            if (numMembers < leastMembers) {
                leastMembers = numMembers;
                leastMembersTeam = teamName;
            }

            // Display the team name and number of team members
            System.out.printf("|%-33s | %-40s | %-37d |\n", teamName, tutorialGroupName, numMembers);
        }
        System.out.println("=======================================================================================================================");

// Calculate the total number of team members in each tutorial group
        HashMap<String, Integer> totalMembersByTutorialGroup = new HashMap<>();
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : assignmentTeams.entries()) {
            String assignmentTeamName = entry.getKey();
            AssignmentTeam team = entry.getValue();
            String tutorialGroup = getTutorialGroupFromAssignmentTeamName(assignmentTeamName);
            int numMembers = team.getTeamMembers().size();

            // Check if the tutorial group already exists in the map
            if (totalMembersByTutorialGroup.containsKey(tutorialGroup)) {
                // If it exists, update the count by adding the number of members
                int currentCount = totalMembersByTutorialGroup.get(tutorialGroup);
                totalMembersByTutorialGroup.put(tutorialGroup, currentCount + numMembers);
            } else {
                // If it doesn't exist, initialize it with the number of members
                totalMembersByTutorialGroup.put(tutorialGroup, numMembers);
            }
        }

        // Display the total number of team members in each tutorial group
        System.out.println("| Tutorial Group                                                              | Number of Student in Tutorial Group   |");
        for (HashMapInterface.Entry<String, Integer> entry : totalMembersByTutorialGroup.entries()) {
            System.out.printf("| %-75s | %-37d |\n", entry.getKey(), entry.getValue());
        }
        System.out.println("=======================================================================================================================");

        // Display the most and least enrolled teams
        System.out.println("| Assignment Team With Most Team Members    : " + mostMembersTeam + " (with " + mostMembers + " members)                                                     |");
        System.out.println("| Assignment Team With Least Team Members   : " + leastMembersTeam + " (with " + leastMembers + " members)                                                     |");
        System.out.println("=======================================================================================================================");
    }

    private String getTutorialGroupFromAssignmentTeamName(String assignmentTeamName) {
        AssignmentTeam team = assignmentTeams.get(assignmentTeamName);
        if (team != null) {
            return team.getTutorialGroup().getTutorialGroupName();
        }
        return null; // or handle appropriately if the assignment team name doesn't exist
    }

    private void summaryReport2() {
        // Get the current date and time
        Date currentDate = new Date();
        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);

        // Print the summary report
        System.out.println("=======================================================================================================================");
        System.out.println("                              Tunku Abdul Rahman University of Management and Technology                               ");
        System.out.println("                                         ASSIGNMENT TEAM MANAGEMENT SUBSYSTEM                                          ");
        System.out.println("                                                                                                                       ");
        System.out.println("                                            ASSIGNMENT TEAM SUMMARY REPORT                                             ");
        System.out.println("                                                                                                                       ");
        System.out.println("Generated at: " + formattedDateTime);
        System.out.println("=======================================================================================================================");
        System.out.println("| Assignment Team Name     | Tutorial Group        | Number of Members | Max Team Size | Utilization (%) | Spaces Left |");

        // Variables to store the most and least number of team members
        int mostMembers = Integer.MIN_VALUE;
        int leastMembers = Integer.MAX_VALUE;
        String mostMembersTeam = "";
        String leastMembersTeam = "";

        // Iterate through all assignment teams
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : assignmentTeams.entries()) {
            String teamName = entry.getKey();
            AssignmentTeam team = entry.getValue();
            int numMembers = team.getTeamMembers().size();
            int maxTeamSize = Integer.parseInt(team.getMaxSize());
            double utilization = (double) numMembers / maxTeamSize * 100;
            int spacesLeft = maxTeamSize - numMembers;
            String tutorialGroupName = team.getTutorialGroup().getTutorialGroupName();

            // Update most and least members if necessary
            if (numMembers > mostMembers) {
                mostMembers = numMembers;
                mostMembersTeam = teamName;
            }
            if (numMembers < leastMembers) {
                leastMembers = numMembers;
                leastMembersTeam = teamName;
            }

            // Display the team name, number of team members, max team size, utilization, and spaces left
            System.out.printf("|%-25s | %-21s | %-17d | %-13d | %.2f%%          | %-11d |\n", teamName, tutorialGroupName, numMembers, maxTeamSize, utilization, spacesLeft);
        }
        System.out.println("=======================================================================================================================");

        // Calculate the total number of team members in each tutorial group
        HashMap<String, Integer> totalMembersByTutorialGroup = new HashMap<>();
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : assignmentTeams.entries()) {
            String assignmentTeamName = entry.getKey();
            AssignmentTeam team = entry.getValue();
            String tutorialGroup = getTutorialGroupFromAssignmentTeamName(assignmentTeamName);
            int numMembers = team.getTeamMembers().size();

            // Check if the tutorial group already exists in the map
            if (totalMembersByTutorialGroup.containsKey(tutorialGroup)) {
                // If it exists, update the count by adding the number of members
                int currentCount = totalMembersByTutorialGroup.get(tutorialGroup);
                totalMembersByTutorialGroup.put(tutorialGroup, currentCount + numMembers);
            } else {
                // If it doesn't exist, initialize it with the number of members
                totalMembersByTutorialGroup.put(tutorialGroup, numMembers);
            }
        }
    }

    public void sortedStudent() {

        ArrayList<Student> students = studentMap.getValues();

        JCComparator<Student> comparator = new JCComparator<>(EnumComparator.studentID);
        studentMap.sort(students, comparator);
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }
}
