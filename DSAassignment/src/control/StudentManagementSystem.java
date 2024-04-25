/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.CourseInitializer;
import dao.ProgramInitializer;
import entity.Student;
import utility.InputUtility;
import boundary.StudentManagementUI;
import dao.StudentInitializer;
import entity.Course;
import entity.Programme;
import entity.RegisteredStudent;
import adt.ArrayList;
import adt.ArrayListInterface;
import adt.HashMap;
import adt.HashMapInterface;
import adt.HashMapInterface.Entry;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import utility.EnumComparator;
import utility.JCComparator;

/**
 *
 * @author jiachuan
 */
public class StudentManagementSystem {

    private StudentInitializer initializer = new StudentInitializer();
    HashMapInterface<String, Student> studentMap = initializer.getStudentList();

    private StudentManagementUI std = new StudentManagementUI();

    private CourseInitializer C = new CourseInitializer();
    private HashMapInterface<String, Course> courseList = C.getCourseList();

    ProgramInitializer Programinitializer = new ProgramInitializer();
    HashMapInterface<String, Programme> programList = Programinitializer.getProgrammeList();

    public void studentChoice(int studentChoice) {
        initializeRegisterStudent();
        while (studentChoice != 0) {
            switch (studentChoice) {
                case 1:                //Add STUDENT
                    System.out.println("----------------Add Student--------------------");

                    //name
                    String name;
                    do {
                        name = InputUtility.promptInput("Enter student name : ");
                        if (name.isEmpty() || containsNumbers(name)) {
                            System.err.println("Invalid name. Name cannot be empty or contain numbers. Please try again.");
                        }
                    } while (name.isEmpty() || containsNumbers(name));

                    System.out.println("");

                    //gender
                    String gender;
                    do {
                        gender = InputUtility.promptInput("Enter Gender (male/female) : ");
                        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
                            System.err.println("Invalid gender. Please enter 'male' or 'female'.");
                        }
                    } while (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"));

                    System.out.println("");

                    //email
                    String email;
                    do {
                        email = InputUtility.promptInput("Enter email : ");
                        if (!isValidEmail(email)) {
                            System.err.println("Invalid email format. Please enter a valid email.");
                        }
                    } while (!isValidEmail(email));

                    System.out.println("");

                    //phone number
                    String phoneNo;
                    do {
                        phoneNo = InputUtility.promptInput("Enter phone number (XXX-XXX-XXXX) : ");
                        if (!isValidPhoneNumber(phoneNo)) {
                            System.err.println("Invalid phone number format. Please enter a valid phone number.");
                        }
                    } while (!isValidPhoneNumber(phoneNo));

                    System.out.println("");

                    //address
                    String address = InputUtility.promptInput("Enter address : ");
                    System.out.println("");

                    //programme code
                    printAllProgramme(programList);
                    String programmeCode;
                    do {
                        programmeCode = std.programmeCode();
                        if (getProgrammeByCode(programmeCode) == null) {
                            System.err.println("Invalid programme code. Please enter a valid programme code.");
                        }
                    } while (getProgrammeByCode(programmeCode) == null);
                    Programme programme = getProgrammeByCode(programmeCode);
                    System.out.println("");

                    //int program = std.programmeMenu();
//                    String programme = programmeCode(program);
                    //scholarships
                    int scholarChoice = std.askScholarShips();
                    String scholarFees = scholarShip(scholarChoice);
                    System.out.println("");

                    //student ID
                    String studentId = generateStudentID(programme);

                    //create student
                    Student newStudent = new Student(studentId, name, gender, programme, email, phoneNo, address, scholarFees);

                    //store into hashmap
                    studentMap.put(studentId, newStudent);
                    System.out.println("Student " + name + " with " + studentId + " added successfully.");
                    System.out.println("");
                    System.out.println("\nPress Enter to return to the selection menu...");
                    try {
                        System.in.read(); // Wait for user to press Enter
                    } catch (IOException e) {
                        e.printStackTrace(); // Handle any IO 
                    }
                    break;

                case 2: // Remove a student
                    while (true) {
                        sortedStudent();
                        String removeId = std.RemoveMenu();

                        if (removeId.equals("0")) {
                            System.out.println("Returning to the previous menu...");
                            break;
                        }

                        if (studentMap.containsKey(removeId)) {
                            Student studentToRemove = studentMap.get(removeId);
                            // Check if the student is registered in any course
                            boolean isRegistered = false;
                            for (Entry<String, Course> entry : courseList.entries()) {
                                Course course = entry.getValue();
                                if (course.isStudentRegistered(studentToRemove)) {
                                    isRegistered = true;
                                    break;
                                }
                            }
                            if (isRegistered) {
                                System.err.println("Cannot remove student. Student is registered under courses.");
                            } else {
                                // Remove the student if not registered in any course
                                studentMap.remove(removeId);
                                System.out.println("Student with ID " + removeId + " has been removed successfully.");
                                System.out.println("Remaining Students:");
                                sortedStudent();
                                break; // Exit the loop after successfully removing the student
                            }
                        } else {
                            System.out.println("The student does not exist. Please make sure that the Student ID entered exists.");
                        }
                    }
                    break;
                case 3:                 // amend student details
                    sortedStudent();
                    String amendId;
                    do {
                        amendId = std.AmendMenu();
                        if (amendId.equals("0")) {
                            System.out.println("Returning to previous menu...");
                            break; // Exit the loop if the user chooses to return to the menu
                        }
                        if (studentMap.containsKey(amendId)) {
                            boolean continueAmend = amendDetails(amendId);
                            if (!continueAmend) {
                                break; // Exit the loop if the user wants to return to the previous menu
                            }
                        } else {
                            System.out.println("The student does not exist. Please make sure that the Student ID entered exists.");
                        }
                    } while (true);

                    break;
                case 4:                 //Search students for registered courses
                    while (true) {
                        // Display the list of courses
                        ListCourses(courseList);
                        String courseCode = std.searchRegisterStudentCourseCode();

                        // Check if the user wants to return to the menu
                        if (courseCode.equals("0")) {
                            System.out.println("Returning to previous menu...");
                            break;
                        }

                        // Retrieve the selected course
                        Course selectedCourse = getCourseByCode(courseCode); // Implement this method to get the course by its code

                        if (selectedCourse != null) {
                            // Display the list of students registered in the selected course
                            //selectedCourse.listRegisteredStudents(); // Implement this method to list registered students for the selected course
                            displayRegisteredStudents(selectedCourse);
                            break;
                        } else {
                            System.out.println("Course not found.");
                        }
                    }
                    break;

                case 5: // Add students to courses

                    while (true) {
                        // Ask the user for the type of course
                        int courseChoice = std.askCourseType();
                        if (courseChoice == 0) {
                            System.out.println("Returning to previous menu...");
                            break; // Exit the loop if the user chooses to return to the menu
                        }
                        String courseType = courseType(courseChoice);

                        // Check if the course type is valid
                        if (!isValidCourseType(courseType)) {
                            System.out.println("Invalid course type. Please enter a valid course type.");
                            continue; // Ask the user for the course type again
                        }

                        // List courses of the specified type
                        ListCoursesWithType(courseList, courseType);

                        // Ask the user for the course they want to add the student to
                        String courseID = std.addStudentToCourses();

                        // Get the course object corresponding to the course ID
                        Course course = getCourseByCode(courseID); // Assuming you have a method to get the course by its code

                        if (course != null) {
                            // Get the student object corresponding to the student ID
                            sortedStudent();
                            String stdId = std.addStudentId();
                            if (stdId.equals("0")) {
                                System.out.println("Returning to previous menu...");
                                break; // Exit the loop if the user chooses to return to the menu
                            }
                            Student student = getStudentById(stdId);

                            if (student != null) {
                                // Check if the student is already registered in the course
                                if (course.isStudentRegistered(student)) {
                                    System.out.println("Student with ID " + stdId + " is already registered in the course.");
                                } else {
                                    // Create a new RegisteredStudent object
                                    RegisteredStudent registeredStudent = new RegisteredStudent(course, student);
                                    // Add the registered student to the course
                                    course.addRegisteredStudent(registeredStudent);
                                    // Print a message indicating success
                                    System.out.println("Student successfully added to the course.");
                                }
                            } else {
                                System.out.println("Student with ID " + stdId + " not found.");
                            }
                        } else {
                            System.out.println("Course with ID " + courseID + " not found. Please enter a valid course code.");
                            continue; // Ask the user for the course code again
                        }
                        break; // Break out of the loop if a valid course code is entered
                    }
                    break;
                case 6:                 //remove student from courses
                    //Enter code for the courses
                    //Check Validation
                    //If yes 
                    //ask for the studentId want to be deleted
                    //after complete ask again want to delete or end
                    // Display the list of courses
                    // Display the list of courses
                    ListCourses(courseList);

// Ask for the course code
                    String coursecode = std.AskcourseCode();

// Check if the user wants to return to the previous menu
                    if (coursecode.equals("0")) {
                        System.out.println("Returning to the previous menu...");
                        break; // Exit the loop and return to the menu
                    }

// If the user didn't choose to return to the previous menu, proceed with the rest of the code
                    Course cCode = getCourseByCode(coursecode);

// Validate the course code (You need to implement this validation)
                    if (cCode != null) {
                        // Check if the course type is main or elective
                        if (cCode.getCourseType().equalsIgnoreCase("M") || cCode.getCourseType().equalsIgnoreCase("E")) {
                            // Flag to track whether any students are registered for the selected course
                            boolean anyStudentsRegistered = false;

                            do {
                                displayRegisteredStudents(cCode);

                                // Check if any students are registered for the selected course
                                if (cCode.getRegisteredStudents().isEmpty()) {
                                    System.out.println("There are no students registered for this course.");
                                    break; // Exit the loop
                                }

                                // Ask for the student ID to remove
                                String sId = std.removeStudent();
                                //Student studentid = getStudentById(sId);

                                // Remove the student from the course (You need to implement this functionality)
                                boolean removed = removeStudentFromCourse(coursecode, sId);

                                if (removed) {
                                    System.out.println("Student removed successfully from the course.");
                                } else {
                                    System.out.println("Failed to remove student from the course. Please try again.");
                                }

                                // Ask if they want to delete another student or end
                                String choice = std.askChoice();

                                if (choice.equalsIgnoreCase("no")) {
                                    System.out.println("End of process.");
                                    break;
                                } else if (choice.equalsIgnoreCase("0")) {
                                    System.out.println("Returning to previous menu...");
                                    break; // Break out of both inner and outer loops
                                }
                            } while (true);
                        } else {
                            System.out.println("You can only delete students from main or elective courses.");
                        }
                    } else {
                        System.out.println("Invalid course code. Please try again.");
                    }
                    break;

                case 7: // calculate fee paid for students
                    while (true) {
                        sortedStudent();
                        String studentID = std.askStudentId();

                        if (studentID.equals("0")) {
                            System.out.println("Returning to previous menu...");
                            break;
                        }

                        Student student = getStudentById(studentID);

                        if (student != null) {
                            generateResitForStudent(student);
                            break; // Exit the loop if a valid student ID is provided
                        } else {
                            System.out.println("Invalid student ID. Please enter a valid student ID.");
                        }
                    }
                    break;
                case 8: // Filter students for courses based on criteria
                    String courseC;
                    int filterChoice;

                    while (true) {
                        // Ask the user for the course code
                        ListCourses(courseList);
                        courseC = std.AskcourseCode();

                        // Check if the user wants to return to the previous menu
                        if (courseC.equals("0")) {
                            System.out.println("Returning to the previous menu...");
                            break; // Exit the loop and return to the menu
                        }

                        // Get the course object corresponding to the course code
                        Course course = getCourseByCode(courseC);

                        // Validate the course object
                        if (course == null) {
                            // If the course object is null, prompt the user to re-enter
                            System.err.println("Invalid course code. Please enter a valid course code.");
                        } else {
                            // Check if there are any registered students in the class
                            if (course.getRegisteredStudents().isEmpty()) {
                                // If no students are registered, inform the user
                                System.out.println("There are no registered students in this class.");

                                // Ask the user if they want to input another course code or return to the menu
                                String userInput = std.AskcourseCode();

                                // Check if the user wants to return to the previous menu
                                if (userInput.equals("0")) {
                                    System.out.println("Returning to the previous menu...");
                                    break; // Exit the loop and return to the menu
                                } else {
                                    courseC = userInput;
                                    // Continue to the next iteration of the loop to ask for another course code
                                    continue;
                                }
                            }

                            // If the course object is valid and there are registered students,
                            // ask the user for the filter choice
                            while (true) {
                                // Ask the user for the filter choice
                                filterChoice = std.askFilterChoice();

                                // Validate the filter choice
                                if (!isValidFilterChoice(filterChoice)) {
                                    // If the filter choice is invalid, prompt the user to re-enter
                                    System.err.println("Invalid filter choice. Please enter a valid filter choice.");
                                } else {
                                    // If the filter choice is valid, break out of the loop
                                    break;
                                }
                            }

                            // After both inputs are valid, proceed with the filter choice action
                            FilterChoice(courseC, filterChoice);

                            // Break out of the outer loop since we've successfully processed the inputs
                            break;
                        }
                    }
                    break;

// Proceed with further actions here
                case 9:                 //Generate Summary Reports
                    generateStudentSummaryReport();
                    break;
                case 10:                // Display course enroll by a student
                    generateTotalIncome();
                    break;
                case 0:
            }
            studentChoice = std.studentMenu();
        }
    }

    private void ListCoursesWithType(HashMapInterface<String, Course> courseMap, String courseType) {
        System.out.println("Courses of type " + courseType + ":");
        System.out.println("+--------------+---------------------------------------------------+");
        System.out.println("| Course ID    | Course Name                                       |");
        System.out.println("+--------------+---------------------------------------------------+");
        for (HashMapInterface.Entry<String, Course> entry : courseMap.entries()) {
            Course course = entry.getValue();
            if (course.getCourseType().equalsIgnoreCase(courseType)) {
                System.out.printf("| %-12s | %-47s |%n", course.getCourseID(), course.getCourseName());
            }
        }
        System.out.println("+--------------+---------------------------------------------------+");
    }

    public String courseType(int courseChoice) {
        String courseType = "";
        switch (courseChoice) {
            case 1:
                courseType = "M";
                break;
            case 2:
                courseType = "R";
                break;
            case 3:
                courseType = "E";
                break;
            case 4:
                courseType = "RP";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return courseType;
    }

    public static void ListRegisterdStudent(HashMapInterface<String, RegisteredStudent> hashmap) {
        System.out.println("Student Registered : \n");
        System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
        System.out.print("| Student ID |            Name           |   Gender   | Programme ID |            Email          |  Phone Number |       Address       | Scholarship|\n");
        System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
        for (Entry<String, RegisteredStudent> entry : hashmap.entries()) {
            System.out.print(entry.getValue());
        }
    }

    public static void displayRegisteredStudents(Course course) {
        HashMapInterface<String, RegisteredStudent> registeredStudentsMap = course.getRegisteredStudents();
        // Check if there are registered students for the course
        if (!course.getRegisteredStudents().isEmpty()) {
            // Iterate over the registered students and display their information
            ListRegisterdStudent(registeredStudentsMap);
        } else {
            System.out.println("No students registered for this course.");
        }
    }

    public void FilterChoice(String courseCode, int filterChoice) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            boolean isValidInput = false;
            switch (filterChoice) {
                case 1: // Filter by gender
                    String gender = std.filterAskGender();
                    // Validate gender input
                    if (isValidGender(gender)) {
                        filterByGender(course.getRegisteredStudents(), gender);
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid gender choice. Please re-enter.");
                    }
                    break;
                case 2: // Filter by programme
                    String programmeCode = std.filterAskProgrammeCode();
                    // Validate programme code input
                    if (isValidProgrammeCode(programmeCode)) {
                        filterByProgramme(course.getRegisteredStudents(), programmeCode);
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid programme code. Please re-enter.");
                    }
                    break;
                case 3: // Filter by scholarships
                    int scholarshipType = std.askScholarShips();
                    // Validate scholarship type input
                    if (isValidScholarshipType(scholarshipType)) {
                        String scholarShip = scholarShip(scholarshipType);
                        filterByScholarship(course.getRegisteredStudents(), scholarShip);
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid scholarship type. Please re-enter.");
                    }
                    break;
                default:
                    System.out.println("Invalid filter choice.");
                    isValidInput = true; // Exit the loop for default case
            }

            // Check if no students are registered for the course
            if (!isValidInput && course.getRegisteredStudents().isEmpty()) {
                System.out.println("There are no students registered for this course.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private boolean isValidGender(String gender) {
        // Add your gender validation logic here
        // For example:
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    private boolean isValidProgrammeCode(String programmeCode) {
        // Check if the programme code exists in the programList map
        boolean valid = programList.containsKey(programmeCode);
        return valid;
    }

    private boolean isValidScholarshipType(int scholarshipType) {
        // Add your scholarship type validation logic here
        // For example:
        return scholarshipType >= 1 && scholarshipType <= 3; // Assuming there are three types of scholarships
    }

    public boolean amendDetails(String studentID) {
        Student student = studentMap.get(studentID);
        int amendChoice;
        do {
            amendChoice = std.AmendChoiceMenu();
            switch (amendChoice) {
                case 1: // Amend name
                    String newName = std.AmendStudentName();
                    if (!newName.equals(student.getName())) {
                        student.setName(newName);
                    } else {
                        System.out.println("New name is the same as the current name. No changes made.");
                        if (!continueOrReturn()) {
                            return false; // Return to previous menu
                        }
                    }
                    break;
                case 2: // Amend student programme
                    printAllProgramme(programList);
                    String programmeCode = std.programmeCode();
                    Programme newProgramme = getProgrammeByCode(programmeCode);
                    if (!newProgramme.getProgrammeID().equals(student.getProgramme().getProgrammeID())) {
                        student.setProgramme(newProgramme);
                    } else {
                        System.out.println("New programme is the same as the current programme. No changes made.");
                        if (!continueOrReturn()) {
                            return false; // Return to previous menu
                        }
                    }
                    break;
                case 3: // Amend email
                    String newEmail = std.AmendStudentEmail();
                    if (!newEmail.equals(student.getEmail())) {
                        student.setEmail(newEmail);
                    } else {
                        System.out.println("New email is the same as the current email. No changes made.");
                        if (!continueOrReturn()) {
                            return false; // Return to previous menu
                        }
                    }
                    break;
                case 4: // Amend phone number
                    String newPhoneNo = std.AmendStudentPhoneNo();
                    student.setPhoneNumber(newPhoneNo);
                    break;
                case 5: // Amend address
                    String newAddress = std.AmendStudentAddress();
                    student.setAddress(newAddress);
                    break;
                case 6: // Amend scholarship
                    int choice = std.askScholarShips();
                    String scholarship = scholarShip(choice);
                    student.setScholarship(scholarship);
                    break;
                case 7: // Change gender
                    String newGender = std.AmendStudentGender();
                    student.setGender(newGender);
                    break;
                case 0: // Return to previous menu
                    System.out.println("Returning to previous menu...");
                    return false;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Ask the user if they want to continue making changes
            if (amendChoice != 0) {
                if (!continueOrReturn()) {
                    return false; // Return to previous menu
                }
            }
        } while (true); // Loop indefinitely until explicitly broken out of
    }

    private boolean continueOrReturn() {
        int continueChoice = std.continueOrNot(); // Assuming std.nextInt() reads an integer input from the user
        if (continueChoice == 1) {
            return true; // Continue making changes
        } else {
            System.out.println("Returning to previous menu...");
            return false; // Return to previous menu
        }
    }

    public void ListCourses(HashMapInterface<String, Course> hashmap) {
        System.out.println("=========================================================================================");
        System.out.println("|    CODE     | COURSE NAME                                    |  STATUS  | CREDIT HOUR |");
        System.out.println("=========================================================================================");
        for (Entry<String, Course> entry : hashmap.entries()) {
            System.out.println(entry.getValue());
        }
        System.out.println("=========================================================================================");
    }

    public Course getCourseByType(String courseType) {
        return courseList.get(courseType);
    }

    private String generateStudentID(Programme programme) {
        if (programme == null) {
            throw new IllegalArgumentException("Programme cannot be null.");
        }

        // Extract the programme code from the programme object
        String programCode = programme.getProgrammeID(); // Assuming getProgrammeCode() returns the programme code

        // Generate a random four-digit number
        String randomDigits = String.format("%04d", (int) (Math.random() * 10000));

        // Combine the program code with the random digits
        return programCode + "-" + randomDigits;
    }

    public Programme getProgrammeByCode(String programmeID) {
        return programList.get(programmeID);
    }

    public Course getCourseByCode(String courseCode) {
        return courseList.get(courseCode);
    }

    public Student getStudentById(String studentId) {
        return studentMap.get(studentId);
    }

    public boolean removeStudentFromCourse(String courseCode, String studentId) {
        // Get the course object corresponding to the course code
        Course course = getCourseByCode(courseCode);

        // If the course exists
        if (course != null) {
            // Remove the student from the course
            return course.removeRegisteredStudent(studentId);
        } else {
            // If the course does not exist
            System.err.println("Course not found.");
            return false;
        }
    }

    public double DoubleScholarship(String scholarship) {
        // Remove the "%" character from the string
        String percentageString = scholarship.replace("%", "");

        // Parse the string as a double
        double percentage = Double.parseDouble(percentageString);

        // Divide by 100 to get the decimal value
        double decimalValue = percentage / 100.0;

        return decimalValue;
    }

    public String scholarShip(int choice) {
        double scholar = 0;
        switch (choice) {
            case 1:
                scholar = 100.0 / 100.0;
                break;
            case 2:
                scholar = 75.0 / 100.0;
                break;
            case 3:
                scholar = 50.0 / 100.0;
                break;
            case 4:
                scholar = 25.0 / 100.0;
                break;
            case 0:
                scholar = 0.0 / 100.0;
                break;
            default:
                System.err.println("Please enter the correct choice");
        }
        // Format the result as a percentage
        String formattedScholarship = String.format("%.0f%%", scholar * 100);
        return formattedScholarship;
    }

    public double calculateTotalTuitionFees(Student student) {
        double totalTuitionFees = 0;

        // Get the list of courses in which the student is enrolled
        ArrayList<Course> enrolledCourses = student.getEnrolledCourses();

        // Print the header for course details
        System.out.println("|Course Code    | Course Name                                                           |Tuition Fee(RM)   |");
        System.out.println("========================================================================================================================");

        // Iterate over each course in the list
        for (int i = 0; i < enrolledCourses.size(); i++) {
            Course course = enrolledCourses.get(i);
            // Get the details of the course
            String courseCode = course.getCourseID();
            String courseName = course.getCourseName();
            double tuitionFeeForCourse = course.getCourseFee();
            // Print the details of the course
            System.out.printf("| %-13s | %-70s | %-16.2f |\n", courseCode, courseName, tuitionFeeForCourse);
            // Add the tuition fee for this course to the total
            totalTuitionFees += tuitionFeeForCourse;
        }

        return totalTuitionFees;
    }

    public boolean isValidCourseType(String courseType) {
        switch (courseType) {
            case "M":
            case "RP":
            case "R":
            case "E":
                return true;
            default:
                return false;
        }
    }

    public boolean isValidPhoneNumber(String phoneNo) {
        String regex = "\\d{3}-\\d{3}-\\d{4}";

        // Check if the provided phone number matches the regex pattern
        boolean isValid = phoneNo.matches(regex);

        return isValid;
    }

    public boolean isValidEmail(String email) {
        // Regular expression to validate email address format
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Check if the provided email matches the regex pattern
        boolean isValid = email.matches(regex);

        return isValid;
    }

    private boolean containsNumbers(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public void printAllProgramme(HashMapInterface<String, Programme> hashmap) {
        System.out.println("Type of Programme : ");
        System.out.println("+------------+-----------------------------------------------------------------------------+------------+");
        System.out.println("| Programme  | Name                                                                        | Faculty    |");
        System.out.println("+------------+-----------------------------------------------------------------------------+------------+");
        for (Entry<String, Programme> entry : hashmap.entries()) {
            System.out.println(entry.getValue());
        }
        //System.out.println("+------------+------------------------------------------------------------------------+--------------+");
    }

    // Constructor to initialize the student map with the students from the initializer
    public void printAllStudent(HashMapInterface<String, Student> hashmap) {
        System.out.println("Student List: ");
        System.out.println("+------------+---------------------------+------------+------------+---------------------------+---------------+---------------------+------------+---------------------+");
        System.out.println("| Student ID | Name                      | Gender     | Programme  | Email                     | Phone Number  | Address             | Scholarship|");
        System.out.println("+------------+---------------------------+------------+------------+---------------------------+---------------+---------------------+------------+---------------------+");
        System.out.println("");
        for (Entry<String, Student> entry : hashmap.entries()) {
            System.out.println(entry.getValue());
        }
        //studentMap.printAllValues();
    }

    public void filterByGender(HashMapInterface<String, RegisteredStudent> students, String gender) {
        boolean foundMatchingStudent = false;
        for (HashMapInterface.Entry<String, RegisteredStudent> entry : students.entries()) {
            RegisteredStudent student = entry.getValue();
            if (student.getStudent().getGender().equalsIgnoreCase(gender)) {
                if (!foundMatchingStudent) {
                    foundMatchingStudent = true;
                    System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
                    System.out.print("| Student ID |            Name           |   Gender   | Programme ID |            Email          |  Phone Number |       Address       | Scholarship|\n");
                    System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
                }
                Student currentStudent = student.getStudent();
                String formattedString = String.format("| %-10s | %-25s | %-10s | %-12s | %-25s | %-13s | %-19s | %-10s |\n",
                        currentStudent.getStudentID(), currentStudent.getName(), currentStudent.getGender(),
                        currentStudent.getProgramme().getProgrammeID(), currentStudent.getEmail(), currentStudent.getPhoneNumber(),
                        currentStudent.getAddress(), currentStudent.getScholarship());
                System.out.print(formattedString);
                System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
            }
        }
        if (!foundMatchingStudent) {
            System.out.println("No students found with the specified gender.");
        }
    }

    public void filterByProgramme(HashMapInterface<String, RegisteredStudent> students, String programmeCode) {
        boolean foundMatchingStudent = false;
        for (HashMapInterface.Entry<String, RegisteredStudent> entry : students.entries()) {
            RegisteredStudent student = entry.getValue();
            Programme programme = student.getStudent().getProgramme();
            if (programme != null && programme.getProgrammeID().equals(programmeCode)) {
                if (!foundMatchingStudent) {
                    foundMatchingStudent = true;
                    System.out.print("+------------+---------------------------+---------------------------+\n");
                    System.out.print("| Student ID |            Name           |        Programme         |\n");
                    System.out.print("+------------+---------------------------+---------------------------+\n");
                }
                String formattedString = String.format("| %-10s | %-25s | %-25s |\n",
                        student.getStudent().getStudentID(), student.getStudent().getName(), programme.getProgrammeID());
                System.out.print(formattedString);
                System.out.print("+------------+---------------------------+---------------------------+\n");
            }
        }
        if (!foundMatchingStudent) {
            System.out.println("No students found with the specified programme.");
        }
    }

    public void filterByScholarship(HashMapInterface<String, RegisteredStudent> students, String scholarshipThreshold) {
        boolean foundMatchingStudent = false;
        for (HashMapInterface.Entry<String, RegisteredStudent> entry : students.entries()) {
            RegisteredStudent student = entry.getValue();
            String scholarshipString = student.getStudent().getScholarship(); // Assuming scholarship is now stored as a string
            double scholarship;

            try {
                // Try to parse the scholarship string as a double
                scholarship = DoubleScholarship(scholarshipString);
            } catch (NumberFormatException e) {
                // If parsing fails, handle the error (e.g., log a message)
                System.err.println("Invalid scholarship value for student " + student.getStudent().getStudentID());
                continue; // Skip to the next student
            }

            // Convert the scholarship threshold string to a double
            double threshold = DoubleScholarship(scholarshipThreshold);

            if (scholarship >= threshold) {
                if (!foundMatchingStudent) {
                    foundMatchingStudent = true;
                    System.out.print("+------------+---------------------------+---------------------------+\n");
                    System.out.print("| Student ID |            Name           |         Scholarship       |\n");
                    System.out.print("+------------+---------------------------+---------------------------+\n");
                }
                String scholarshipPercent = String.format("%.0f%%", scholarship * 100);
                String formattedString = String.format("| %-10s | %-25s | %-25s |\n",
                        student.getStudent().getStudentID(), student.getStudent().getName(), scholarshipPercent);
                System.out.print(formattedString);
                System.out.print("+------------+---------------------------+---------------------------+\n");
            }
        }
        if (!foundMatchingStudent) {
            System.out.println("No students found with a scholarship greater than or equal to the specified threshold.");
        }
    }

    public void generateStudentSummaryReport() {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);

        // Count total number of students
        int totalStudents = studentMap.size();

        // Count number of male and female students
        int maleStudents = countStudentsByGender("male");
        int femaleStudents = countStudentsByGender("female");

        // Variables to track most enrolled program and faculty
        String mostEnrolledProgram = "";
        int maxProgramCount = 0;
        String mostEnrolledFaculty = "";
        int maxFacultyCount = 0;

        // Create a map to store the total number of students for each faculty
        HashMapInterface<String, Integer> facultyStudentCount = new HashMap<>();

        // Display the summary report
        System.out.println("========================================================================================================================");
        System.out.println("                                  Tunku Abdul Rahman Univeristy of Management and Technology                            ");
        System.out.println("                                              STUDENT MANAGEMENT SUBSYSTEM                                              ");
        System.out.println("");
        System.out.println("                                                STUDENT SUMMARY REPORT                                                  ");
        System.out.println("Generated at : " + formattedDateTime);
        System.out.println("========================================================================================================================");
        System.out.println("| Program Name                                                                     | Male Students | Female Students |");
        System.out.println("========================================================================================================================");

        // Display student count by gender in each program
        for (HashMapInterface.Entry<String, Programme> programEntry : programList.entries()) {
            Programme program = programEntry.getValue();
            String programName = program.getProgrammeName();
            String faculty = program.getProgrammeFac();

            // Count students by gender within the program
            int maleCount = countStudentsByGenderInProgram(program.getProgrammeID(), "male");
            int femaleCount = countStudentsByGenderInProgram(program.getProgrammeID(), "female");

            // Update most enrolled program
            int totalProgramStudents = maleCount + femaleCount;
            if (totalProgramStudents > maxProgramCount) {
                maxProgramCount = totalProgramStudents;
                mostEnrolledProgram = programName;
            }

            // Update faculty student count
            facultyStudentCount.put(faculty, facultyStudentCount.getOrDefault(faculty, 0) + totalProgramStudents);

            // Display the counts in table format
            System.out.printf("| %-80s | %-13d | %-15d |\n", programName, maleCount, femaleCount);
        }
        System.out.println("=========================================================================================================================");
        System.out.println("| Total Number of Students Enrolled:                                                                 | " + totalStudents + " |");
        System.out.println("| Male Students:                                                                                     | " + maleStudents + " |");
        System.out.println("| Female Students:                                                                                   | " + femaleStudents + " |");
        System.out.println("=========================================================================================================================");

        // Find the most enrolled faculty
        for (HashMapInterface.Entry<String, Integer> entry : facultyStudentCount.entries()) {
            String faculty = entry.getKey();
            int totalFacultyStudents = entry.getValue();
            if (totalFacultyStudents > maxFacultyCount) {
                maxFacultyCount = totalFacultyStudents;
                mostEnrolledFaculty = faculty;
            }
        }

        // Display most enrolled program and faculty
        System.out.println("Most Enrolled Program: " + mostEnrolledProgram + " (" + maxProgramCount + " students)");
        System.out.println("Most Enrolled Faculty: " + mostEnrolledFaculty + " (" + maxFacultyCount + " students)");
        System.out.println("==========================================================================================================================");
        System.out.println("");
        System.out.println("\nPress Enter to return to the selection menu...");
        try {
            System.in.read(); // Wait for user to press Enter
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions
        }
    }

// Method to count students by gender
    private int countStudentsByGender(String gender) {
        int count = 0;
        for (HashMapInterface.Entry<String, Student> entry : studentMap.entries()) {
            Student student = entry.getValue(); // Get the Student object from the entry
            if (student.getGender().equalsIgnoreCase(gender)) {
                count++;
            }
        }
        return count;
    }

// Method to display count of students by gender in each program
    public void displayStudentCountByGenderInEachProgram() {
        // Iterate over each program
        for (HashMapInterface.Entry<String, Programme> programEntry : programList.entries()) {
            Programme program = programEntry.getValue();
            System.out.println("Programme: " + program.getProgrammeName());

            // Count students by gender within the program
            int maleCount = countStudentsByGenderInProgram(program.getProgrammeID(), "male");
            int femaleCount = countStudentsByGenderInProgram(program.getProgrammeID(), "female");

            // Display the counts
            System.out.println("Male Students: " + maleCount);
            System.out.println("Female Students: " + femaleCount);
            System.out.println();
        }
    }

// Method to count students by gender within a specific program
    private int countStudentsByGenderInProgram(String programID, String gender) {
        int count = 0;
        // Iterate over each student in the program
        for (HashMapInterface.Entry<String, Student> studentEntry : studentMap.entries()) {
            Student student = studentEntry.getValue();
            if (student.getProgramme().getProgrammeID().equals(programID) && student.getGender().equalsIgnoreCase(gender)) {
                count++;
            }
        }
        return count;
    }

    public void generateTotalIncome() {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);
        // Initialize variables for total income calculation
        double totalIncome = 0;
        double maxIncome = 0; // Initialize with 0 instead of Double.MIN_VALUE
        double minIncome = Double.MAX_VALUE;
        String maxIncomeCourseID = "";
        String minIncomeCourseID = "";

        // Flag to indicate if it's the first iteration
        boolean isFirstCourse = true;

        // Print header
        // Display the summary report
        System.out.println("========================================================================================================================");
        System.out.println("                                  Tunku Abdul Rahman Univeristy of Management and Technology                            ");
        System.out.println("                                              STUDENT MANAGEMENT SUBSYSTEM                                              ");
        System.out.println("");
        System.out.println("                                                STUDENT SUMMARY REPORT                                                  ");
        System.out.println("Generated at : " + formattedDateTime);
        System.out.println("========================================================================================================================");
        System.out.printf("%-15s %-35s %-15s %-15s\n", "Course ID", "Course Name", "Course Fee", "Total Income");
        System.out.println("========================================================================================================================");

        // Iterate over each course in the course list
        for (HashMapInterface.Entry<String, Course> entry : courseList.entries()) {
            Course course = entry.getValue();
            String courseID = course.getCourseID();
            String courseName = course.getCourseName();
            double courseFee = course.getCourseFee();

            // Get the registered students for this course
            HashMapInterface<String, RegisteredStudent> registeredStudents = course.getRegisteredStudents();

            // Calculate the total income for this course
            double courseTotalIncome = courseFee * registeredStudents.size();

            // Update total income
            totalIncome += courseTotalIncome;

            // Update maximum income course
            if (isFirstCourse || courseTotalIncome > maxIncome) {
                maxIncome = courseTotalIncome;
                maxIncomeCourseID = courseID;
                isFirstCourse = false; // Reset flag after the first course
            }

            // Update minimum income course
            if (courseTotalIncome < minIncome) {
                minIncome = courseTotalIncome;
                minIncomeCourseID = courseID;
            }

            // Print the details for each course
            System.out.printf("%-15s %-35s %-15s %-15s\n", courseID, courseName, courseFee, courseTotalIncome);
        }

        // Print total income
        System.out.println("========================================================================================================================");
        System.out.printf("%-45s %-15s\n", "Total Income:(RM)", totalIncome);
        System.out.println("========================================================================================================================");

        // Print highest income course
        System.out.println("Highest Income Course: " + maxIncomeCourseID + " with income: " + maxIncome);

        // Print lowest income course
        System.out.println("Lowest Income Course: " + minIncomeCourseID + " with income: " + minIncome);
        System.out.println("========================================================================================================================");
        System.out.println("");
        System.out.println("\nPress Enter to return to the selection menu...");
        try {
            System.in.read(); // Wait for user to press Enter
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO 
        }
    }

    public void generateResitForStudent(Student student) {

        // Get the scholarship as a string
        String scholarshipString = student.getScholarship();

        // Parse the scholarship string to a double
        double scholarships;
        try {
            scholarships = DoubleScholarship(scholarshipString);
        } catch (NumberFormatException e) {
            System.err.println("Invalid scholarship value for student " + student.getName());
            return; // Exit the method if scholarship value is invalid
        }

        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);

        System.out.println("========================================================================================================================");
        System.out.println("                                  Tunku Abdul Rahman University of Management and Technology                            ");
        System.out.println("                                              Receipt For " + student.getName());
        System.out.println("Generated at : " + formattedDateTime);
        System.out.println("========================================================================================================================");
        double TotalFees = calculateTotalTuitionFees(student);
        double amountNeededToPay = TotalFees - (TotalFees * scholarships);
        System.out.println("========================================================================================================================");
        System.out.printf("Total Tuition Fees                                                                       : RM%.2f%n", TotalFees);
        System.out.printf("Scholarships                                                                             : RM%.2f%n", (TotalFees * scholarships));
        System.out.println("========================================================================================================================");
        System.out.printf("Total Needed To Pay                                                                      : RM%.2f%n", amountNeededToPay);
        System.out.println("========================================================================================================================");
        System.out.println("                              No signature is required as this document is computer-generated ");
        System.out.println("========================================================================================================================");
        System.out.println("");
        System.out.println("\nPress Enter to return to the selection menu...");
        try {
            System.in.read(); // Wait for user to press Enter
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO 
        }

    }

    public void addRegisteredStudentToCourse(String courseID, String studentID) {
        // Get the course and student objects using their IDs
        Course course = courseList.get(courseID);
        Student student = studentMap.get(studentID);

        if (course != null && student != null) {
            // Check if the student is already registered in the course
            if (course.isStudentRegistered(student)) {
                //System.out.println("Student with ID " + studentID + " is already registered in the course.");
            } else {
                // Create a new RegisteredStudent object
                RegisteredStudent registeredStudent = new RegisteredStudent(course, student);
                // Add the registered student to the course
                course.addRegisteredStudent(registeredStudent);
                // Add the registered student to the registeredStudents hashmap
                // Print a message indicating success
                //System.out.println("Student successfully added to the course.");
            }
        } else {
            System.out.println("Course with ID " + courseID + " or student with ID " + studentID + " not found.");
        }
    }

    public void initializeRegisterStudent() {
        addRegisteredStudentToCourse("BACS2063", "A1003");
        addRegisteredStudentToCourse("BACS2063", "A1001");
        addRegisteredStudentToCourse("BJEL1013", "A1002");
        addRegisteredStudentToCourse("BJEL1013[RP]", "A1003");
        addRegisteredStudentToCourse("BACS2063[R]", "A1004");
        addRegisteredStudentToCourse("BACS2063[E]", "A1006");
        addRegisteredStudentToCourse("BJEL1013", "A1005");
        addRegisteredStudentToCourse("BJEL1013[RP]", "A1006");
        addRegisteredStudentToCourse("BACS2042", "A1002");
        addRegisteredStudentToCourse("BACS1013", "A1005");
        addRegisteredStudentToCourse("BACS2163", "A1001");
        addRegisteredStudentToCourse("BAIT1023", "A1005");
        addRegisteredStudentToCourse("BAMS1613", "A1001");
        addRegisteredStudentToCourse("BACS1013", "A1001");
    }

    private boolean isValidFilterChoice(int filterChoice) {
        switch (filterChoice) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public void sortedStudent() {

        ArrayList<Student> students = studentMap.getValues();

        JCComparator<Student> comparator = new JCComparator<>(EnumComparator.studentID);
        studentMap.sort(students, comparator);
        System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
        System.out.print("| Student ID |            Name           |   Gender   | Programme ID |            Email          |  Phone Number |       Address       | Scholarship|\n");
        System.out.print("+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n");
        for (int i = 0; i < students.size(); i++) {
            System.out.print(students.get(i));
        }
    }

}
