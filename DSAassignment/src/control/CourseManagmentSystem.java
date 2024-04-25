/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.CourseInitializer;
import dao.ProgramInitializer;
import entity.Course;
import entity.LinkedProgrammeCourse;
import entity.Programme;
import adt.ArrayList;
import adt.HashMapInterface;
import boundary.courseUI;
import adt.ArrayListInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import utility.*;

/**
 *
 * @author jiachuan
 */
public class CourseManagmentSystem {

    ProgramInitializer programInitializer = new ProgramInitializer();
    CourseInitializer courseInitializer = new CourseInitializer();
    HashMapInterface<String, Programme> programmeList = programInitializer.getProgrammeList();
    HashMapInterface<String, Course> courseList = courseInitializer.getCourseList();
    static ArrayListInterface<LinkedProgrammeCourse> linkedProgrammeCourse = new ArrayList<>();
    private final courseUI courseUI = new courseUI();

    public void startCourseManagement() {
        int choice = 0;
        do {
            choice = courseUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addProgramme();
                    break;
                case 2:
                    removeProgramme();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    removeProgramme();
                    break;
                case 5:
                    searchCourse();
                    break;
                case 6:
                    amendCourseDetails();
                    break;
                case 7:
                    listAllCourseUnderFaculty();
                    break;
                case 8:
                    displayAllCourseInProgramme();
                    break;
                case 9:
                    chooseSummaryReport();
                    break;
                case 10:
                    InputUtility.displaySuccessMessage("Exit to main menu");
                    break;
                default:
                    InputUtility.displayFailureMessage("Invalid Input. Please try again");
            }
        } while (choice != 10);
    }

    public void listProgramme() {
        MessageUI.displayProgrammeTableHead();
        for (HashMapInterface.Entry<String, Programme> entry : programmeList.entries()) {
            System.out.println(entry.getValue());
        }
    }

    public void listCourse() {
        MessageUI.displayCourseTableHead();
        for (HashMapInterface.Entry<String, Course> entry : courseList.entries()) {
            System.out.println(entry.getValue());
        }
        MessageUI.displayCourseTableTail();
    }

    public void addProgramme() {
        listProgramme();
        listCourse();
        String again;
        do {
            String programmeAdd = courseUI.inputProgrammeCode();
            if (programmeList.containsKey(programmeAdd)) {
                Programme programme = programmeList.get(programmeAdd);
                String courseAdd = courseUI.inputCourseId();
                if (courseList.containsKey(courseAdd)) {
                    if (linkedProgrammeCourse.isEmpty()) {
                        linkedProgrammeCourse.add(new LinkedProgrammeCourse(programmeAdd, courseAdd));
                        InputUtility.displaySuccessMessage("Successfully add a programme to course");
                    } else if (isExist(programmeAdd, courseAdd)) {
                        InputUtility.displayFailureMessage("The programme have this course already");
                    } else {
                        linkedProgrammeCourse.add(new LinkedProgrammeCourse(programmeAdd, courseAdd));
                        InputUtility.displaySuccessMessage("Successfully add a programme to course");
                    }
                } else {
                    InputUtility.displayFailureMessage("Course not found");
                }
            } else {
                InputUtility.displayFailureMessage("Programme not found");
            }
            again = courseUI.inputAgain();

        } while (!"N".equals(again));
    }

    public boolean isExist(String programmeCode, String courseCode) {
        for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
            LinkedProgrammeCourse item = linkedProgrammeCourse.get(i);
            if (item.getProgrammeCode().equals(programmeCode) && item.getCourseID().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void removeProgramme() {
        String again;
        do {
            String programmeAdd = courseUI.inputProgrammeCode();
            if (programmeList.containsKey(programmeAdd)) {
                String courseAdd = courseUI.inputCourseId();
                if (courseList.containsKey(courseAdd)) {
                    boolean removed = false;
                    for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                        LinkedProgrammeCourse item = linkedProgrammeCourse.get(i);
                        if (item.getProgrammeCode().equals(programmeAdd) && item.getCourseID().equals(courseAdd)) {
                            linkedProgrammeCourse.removeBoolean(i);
                            InputUtility.displaySuccessMessage("The programme has been removed from the course");
                            removed = true;
                            break; // Exit the loop after removing the programme-course pair
                        }
                    }
                    if (!removed) {
                        InputUtility.displayFailureMessage("No course under this programme");
                    }
                } else {
                    InputUtility.displayFailureMessage("Course not found");
                }
            } else {
                InputUtility.displayFailureMessage("Programme not found");
            }
            again = courseUI.inputAgain();
        } while (!"N".equals(again));
    }

    public void addCourse() {
        String again;
        do {
            String id = courseUI.inputCourseId();
            String name = courseUI.inputCourseName();
            int chours = courseUI.inputCourseCreditHours();
            double courseFee = courseUI.inputCourseFee();
            String cType = courseUI.inputCourseType();
            if (!isValidCourseType(cType)){
                InputUtility.displayFailureMessage("Invalid course type. Please try again");
                again = courseUI.inputAgain();
            }else if (courseList.containsKey(id)) {
                InputUtility.displayFailureMessage("The course already exist. Please enter another course.");
                again = courseUI.inputAgain();
            }else {
                courseList.put(id, new Course(id, name, courseFee, cType, chours));
                String programmeAdd = courseUI.inputProgrammeCode2();
                if (programmeList.containsKey(programmeAdd)) {
                    linkedProgrammeCourse.add(new LinkedProgrammeCourse(programmeAdd, id));
                    InputUtility.displaySuccessMessage("Successfully add the course to the programme");
                } else {
                    InputUtility.displayFailureMessage("Programme not found");
                }
                again = courseUI.inputAgain();
            }
        } while (!"N".equals(again));
    }

    public void displaylinkedProgrammeCourse() {
        for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
            LinkedProgrammeCourse item = linkedProgrammeCourse.get(i);
            System.out.println(i + "" + item);
        }
    }

    public void displayAllCourseInProgramme() {
        String again;
        do {
            String programmeCode = courseUI.inputProgrammeCode();
            boolean found = false;
            if (linkedProgrammeCourse.isEmpty()) {
                InputUtility.displayFailureMessage("No course in this programme.");
            } else {
                MessageUI.displayCourseTableHead();
                for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                    if (linkedProgrammeCourse.get(i).getProgrammeCode().equals(programmeCode)) {
                        found = true;
                        System.out.println(courseList.get(linkedProgrammeCourse.get(i).getCourseID()));
                    }
                }
                MessageUI.displayCourseTableTail();
                if (!found) {
                    InputUtility.displayFailureMessage("No course found for the specified programme.");
                }
            }
            again = courseUI.inputAgain();
        } while (!"N".equals(again));
    }

    public void searchCourse() {
        String again;
        do {
            String courseSearch = courseUI.inputCourseId();
            if (courseList.containsKey(courseSearch)) {
                System.out.println("Course found");
                MessageUI.displayCourseTableHead();
                System.out.println(courseList.get(courseSearch));
                MessageUI.displayCourseTableTail();
            } else {
                InputUtility.displayFailureMessage("Course not found");
            }
            again = courseUI.inputAgain();
        } while (!"N".equals(again));
    }

    public void amendCourseDetails() {
        String again;
        do {
            String pCode = courseUI.inputProgrammeCode();
            for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                String pCodeOri = linkedProgrammeCourse.get(i).getProgrammeCode();
                if (!programmeList.containsKey(pCode)) {
                    InputUtility.displayFailureMessage("Programme not found");
                } else if (!pCodeOri.equals(pCode)) {
                    InputUtility.displayFailureMessage("This programme does not have the course");
                } else {
                    String courseID = courseUI.inputCourseId();
                    String cCodeOri = linkedProgrammeCourse.get(i).getCourseID();
                    if (!courseList.containsKey(courseID)) {
                        InputUtility.displayFailureMessage("Course not found");
                    } else if (!cCodeOri.equals(courseID)) {
                        InputUtility.displayFailureMessage("This programme does not have the course");
                    } else {
                        String newCourseID = courseUI.inputNewCourseId();
                        String newCourseName = courseUI.inputNewCourseName();
                        int newCourseCreditHours = courseUI.inputNewCourseCreditHours();
                        double newCourseFee = courseUI.inputNewCourseFee();
                        String newCourseType = courseUI.inputNewCourseType();
                        if (!isValidCourseType(newCourseType)) {
                            InputUtility.displayFailureMessage("Invalid course type.");
                        } else {
                            int a = i + 1;
                            LinkedProgrammeCourse item = new LinkedProgrammeCourse(pCode, newCourseID);
                            courseList.put(newCourseID, new Course(newCourseID, newCourseName, newCourseFee, newCourseType, newCourseCreditHours));
                            linkedProgrammeCourse.replace(a, item);
                            InputUtility.displaySuccessMessage("Successfully amend the course details");
                        }
                    }
                }
            }
            again = courseUI.inputAgain();
        } while (!"N".equals(again));
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

    public void listAllCourseUnderFaculty() {
        String again;
        boolean haveCourse = false;
        ArrayList<String> courseId = new ArrayList<>();
        do {
            System.out.println("==============================================================================================");
            System.out.println("| Faculty Code   | Faculty Name                                                              |");
            System.out.println("==============================================================================================");
            System.out.println("|  FOCS          |Faculty of Computing and Information Technology                            |");
            System.out.println("|  FAFB          |Faculty of Accountancy, Finance and Business                               |");
            System.out.println("|  FCCI          |Faculty of Communication and Creative Industries                           |");
            System.out.println("|  FSSH          |Faculty of Social Science and Humanities                                   |");
            System.out.println("|  FOBE          |Faculty of Built Environment                                               |");
            System.out.println("|  FAFB          |Faculty of Built Environment                                               |");
            System.out.println("==============================================================================================");
            String facultyShort = courseUI.inputFaculty();
            if (linkedProgrammeCourse.isEmpty()) {
                InputUtility.displayFailureMessage("No course under this faculty");
                haveCourse = true;
            } else {
                ArrayList<Programme> programmeArrayList = programmeList.getValues();
                for (int i = 0; i < programmeArrayList.size(); i++) {
                    if (programmeArrayList.get(i).getProgrammeFac().equals(facultyShort)) {
                        Programme item = programmeArrayList.get(i);
                        for (int j = 0; j < linkedProgrammeCourse.size(); j++) {
                            if (linkedProgrammeCourse.get(j).getProgrammeCode().equals(item.getProgrammeID())) {
                                courseId.addNoDuoplicated(linkedProgrammeCourse.get(j).getCourseID());
                                haveCourse = true;
                            }
                        }
                    }
                }
            }
            if (!haveCourse) {
                InputUtility.displayFailureMessage("No course under this faculty");
            } else {
                MessageUI.displayCourseTableHead();
                for (int i = 0; i < courseId.size(); i++) {
                    if (courseList.containsKey(courseId.get(i))) {
                        System.out.println(courseList.get(courseId.get(i)));
                    }

                }
                MessageUI.displayCourseTableTail();
            }
            again = courseUI.inputAgain();
        } while (!"N".equals(again));
    }

    public void chooseSummaryReport() {
        int choose;
        String again;
        do {
            choose = courseUI.inputChoice();
            switch (choose) {
                case 1:
                    summaryReport1();
                    break;
                case 2:
                    summaryReport2();
                    break;
                default:
                    InputUtility.displayFailureMessage("Invalid Input. Please try again.");
            }
            again = courseUI.inputAgain();
        } while (!"N".equals(again));
    }

    public void summaryReport1() {
        Date currentDate = new Date();
        int maxProgramme = Integer.MIN_VALUE;
        int minProgramme = Integer.MAX_VALUE;
        int maxFaculty = Integer.MIN_VALUE;
        int minFaculty = Integer.MAX_VALUE;
        int maxProgrammePos = 0;
        int minProgrammePos = 0;
        int maxFacultyPos = 0;
        int minFacultyPos = 0;

        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);
        System.out.println("========================================================================================================================");
        System.out.println("                                              COURSE MANAGEMENT SUBSYSTEM                                              ");
        System.out.println("");
        System.out.println("                                                COURSE SUMMARY REPORT                                                  ");
        System.out.println("Generated at : " + formattedDateTime);
        System.out.println("========================================================================================================================");
        System.out.println("|    CODE     | COURSE NAME                                    |  STATUS  | CREDIT HOUR |  FACULTIES/PROGRAMME OFFERED |");
        System.out.println("========================================================================================================================");
        ArrayList<Course> courseArrayList = courseList.getValues();
        for (int i = 0; i < courseArrayList.size(); i++) {
            String courseCode = courseArrayList.get(i).getCourseID();
            String courseName = courseArrayList.get(i).getCourseName();
            String courseStatus = courseArrayList.get(i).getCourseType();
            int courseCreditHours = courseArrayList.get(i).getCreditHours();
            int totalFaculty = countFaculty(courseArrayList.get(i));
            int totalProgramme = countProgramme(courseArrayList.get(i));
            if (totalProgramme > maxProgramme && totalProgramme != 0) {
                maxProgramme = totalProgramme;
                maxProgrammePos = i;

            }
            if (totalProgramme < minProgramme && totalProgramme != 0) {
                minProgramme = totalProgramme;
                minProgrammePos = i;
            }
            if (totalFaculty > maxFaculty && totalFaculty != 0) {
                maxFaculty = totalFaculty;
                maxFacultyPos = i;

            }
            if (totalFaculty < minFaculty && totalFaculty != 0) {
                minFaculty = totalFaculty;
                minFacultyPos = i;
            }
            System.out.printf("| %-12s| %-47s| %-9s| %-12d| %14d/%-14d|\n", courseCode, courseName, courseStatus, courseCreditHours, totalFaculty, totalProgramme);
        }
        System.out.println(" ");
        System.out.println("Course Status: M = Main / R = Resit / RP = Repeat / E = Elective ");
        System.out.println("Total " + courseList.size() + " Courses: " + countMain() + "Main | " + countRepeat() + "Repeat | " + countResit() + "Resit | " + countElective() + "Elective");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        if (maxFaculty == 0 || maxFaculty == Integer.MIN_VALUE) {
            System.out.println("Highest Faculties Offered: [0 Faculties] <null> null");
        } else {
            System.out.println("Highest Faculties Offered: [" + maxFaculty + " Faculties] <" + courseArrayList.get(maxFacultyPos).getCourseID() + "> " + courseArrayList.get(maxFacultyPos).getCourseName());
        }

        if (minFaculty == 0 || minFaculty == Integer.MAX_VALUE) {
            System.out.println("Lowest Faculties Offered : [0 Faculties] <null> null");
        } else {
            System.out.println("Lowest Faculties Offered : [" + minFaculty + " Faculties] <" + courseArrayList.get(minFacultyPos).getCourseID() + "> " + courseArrayList.get(minFacultyPos).getCourseName());
        }

        System.out.println("[Note: 0 faculties is not counted]");
        System.out.println("*************************************************");

        if (maxProgramme == 0 || maxProgramme == Integer.MIN_VALUE) {
            System.out.println("Highest Programme Offered: [0 Programme] <null> null");
        } else {
            System.out.println("Highest Programme Offered: [" + maxProgramme + " Programme] <" + courseArrayList.get(maxProgrammePos).getCourseID() + "> " + courseArrayList.get(maxProgrammePos).getCourseName());
        }

        if (minProgramme == 0 || minProgramme == Integer.MAX_VALUE) {
            System.out.println("Lowest Programme Offered: [0 Programme] <null> null");
        } else {
            System.out.println("Lowest Programme Offered: [" + minProgramme + " Programme] <" + courseArrayList.get(minProgrammePos).getCourseID() + "> " + courseArrayList.get(minProgrammePos).getCourseName());
        }
        System.out.println("[Note: 0 programme is not counted]");
        System.out.println("----------------------------------------------END OF THE SUMMARY REPORT--------------------------------------------------");
        System.out.println("=========================================================================================================================");

    }

    public void summaryReport2() {
        Date currentDate = new Date();
        int totalProgrammeFee = 0;
        int totalSemesterFee = 0;
        int bigFee = 0;
        int lowFee = 0;
        int maxCourseFee = Integer.MIN_VALUE;
        int minCourseFee = Integer.MAX_VALUE;
        int maxCoursePos = 0;
        int minCoursePos = 0;
        // Format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy, hh:mm a", Locale.ENGLISH);
        String formattedDateTime = dateFormat.format(currentDate);
        System.out.println("================================================================================================================================");
        System.out.println("                                               Programme MANAGEMENT SUBSYSTEM                                                   ");
        System.out.println("");
        System.out.println("                                                Programme SUMMARY REPORT                                                         ");
        System.out.println("Generated at : " + formattedDateTime);
        System.out.println("================================================================================================================================");
        System.out.println("|    CODE    | PRROGRAMEE NAME                                                           | TOTAL COURSE | TOTAL COURSE FEE(RM) |");
        System.out.println("================================================================================================================================");
        ArrayList<Programme> programmeArrayList = programmeList.getValues();
        for (int i = 0; i < programmeArrayList.size(); i++) {
            String programmeCode = programmeArrayList.get(i).getProgrammeID();
            String programmeName = programmeArrayList.get(i).getProgrammeName();
            int totalCourse = countCourse(programmeArrayList.get(i));
            int totalCourseFee = countCourseFee(programmeArrayList.get(i));
            totalProgrammeFee += totalCourseFee;
            if (totalCourseFee > maxCourseFee && totalCourse != 0) {
                bigFee = totalCourseFee;
                maxCourseFee = totalCourse;
                maxCoursePos = i;
            }
            if (totalCourseFee < minCourseFee && totalCourse != 0) {
                lowFee = totalCourseFee;
                minCourseFee = totalCourseFee;
                minCoursePos = i;
            }
            System.out.printf("| %-11s| %-75s| %-13s| %-20d|\n", programmeCode, programmeName, totalCourse, totalCourseFee);
        }
        System.out.println("The total semester fee is RM " + totalProgrammeFee);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        if (maxCourseFee == 0 || maxCourseFee == Integer.MIN_VALUE) {
            System.out.println("Highest Programme Fee: [RM 0] <null> null");
        } else {
            System.out.println("Highest Programme Fee: [RM " + bigFee + "] <" + programmeArrayList.get(maxCoursePos).getProgrammeID() + "> " + programmeArrayList.get(maxCoursePos).getProgrammeName());
        }
        if (minCourseFee == 0 || minCourseFee == Integer.MAX_VALUE) {
            System.out.println("Lowest Programme Fee : [RM 0] <null> null");
        } else {
            System.out.println("Lowest Programme Fee : [RM " + lowFee + "] <" + programmeArrayList.get(minCoursePos).getProgrammeID() + "> " + programmeArrayList.get(minCoursePos).getProgrammeName());
        }
        System.out.println("[Note: Programme with 0 course fee not count]");
        System.out.println("----------------------------------------------END OF THE SUMMARY REPORT------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
    }

    public int countCourse(Programme programme) {
        int totalCourse = 0;
        if (!linkedProgrammeCourse.isEmpty()) {
            for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                Programme programmeNeed = programmeList.get(linkedProgrammeCourse.get(i).getProgrammeCode());
                if (programmeNeed != null && programmeNeed.getProgrammeID().equals(programme.getProgrammeID())) {
                    totalCourse++;
                }
            }
        }
        return totalCourse;
    }

    public int countCourseFee(Programme programme) {
        int totalCourseFee = 0;
        if (!linkedProgrammeCourse.isEmpty()) {
            for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                Programme programmeNeed = programmeList.get(linkedProgrammeCourse.get(i).getProgrammeCode());
                Course courseNeed = courseList.get(linkedProgrammeCourse.get(i).getCourseID());
                if (programmeNeed != null && programmeNeed.getProgrammeID().equals(programme.getProgrammeID())) {
                    totalCourseFee += courseNeed.getCourseFee();
                }
            }
        }
        return totalCourseFee;
    }


    public int countFaculty(Course course) {
        int totalFaculty = 0;
        boolean haveFocs = false;
        boolean haveFcci = false;
        boolean haveFafb = false;
        boolean haveFobe = false;
        boolean haveFssh = false;
        ArrayList<Course> courseArrayList = courseList.getValues();
        ArrayList<Programme> programmeArrayList = programmeList.getValues();
        if (!linkedProgrammeCourse.isEmpty()) {
            for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                if (course.getCourseID().equals(linkedProgrammeCourse.get(i).getCourseID())) {
                    for (int j = 0; j < programmeArrayList.size(); j++) {
                        if (linkedProgrammeCourse.get(i).getProgrammeCode().equals(programmeArrayList.get(j).getProgrammeID())) {
                            switch (programmeArrayList.get(j).getProgrammeFac()) {
                                case "FOCS" ->
                                    haveFocs = true;
                                case "FCCI" ->
                                    haveFcci = true;
                                case "FAFB" ->
                                    haveFafb = true;
                                case "FOBE" ->
                                    haveFobe = true;
                                case "FSSH" ->
                                    haveFssh = true;
                                default -> {
                                }
                            }
                        }
                    }
                }
            }
        }
        if (haveFocs) {
            totalFaculty += 1;
        }
        if (haveFcci) {
            totalFaculty += 1;
        }
        if (haveFafb) {
            totalFaculty += 1;
        }
        if (haveFobe) {
            totalFaculty += 1;
        }
        if (haveFssh) {
            totalFaculty += 1;
        }
        return totalFaculty;
    }

    public int countProgramme(Course course) {
        int totalProgramme = 0;
        if (!linkedProgrammeCourse.isEmpty()) {
            for (int i = 0; i < linkedProgrammeCourse.size(); i++) {
                Course courseNeed = courseList.get(linkedProgrammeCourse.get(i).getCourseID());
                if (courseNeed != null && courseNeed.getCourseID().equals(course.getCourseID())) {
                    totalProgramme++;
                }
            }
        }
        return totalProgramme;
    }

    public int countMain() {
        int totalMain = 0;
        ArrayList<Course> courseArrayList = courseList.getValues();
        for (int i = 0; i < courseArrayList.size(); i++) {
            if (courseArrayList.get(i).getCourseType().equals("M")) {
                totalMain++;
            }
        }
        return totalMain;
    }

    public int countResit() {
        int totalResit = 0;
        ArrayList<Course> courseArrayList = courseList.getValues();
        for (int i = 0; i < courseArrayList.size(); i++) {
            if (courseArrayList.get(i).getCourseType().equals("R")) {
                totalResit++;
            }
        }

        return totalResit;
    }

    public int countRepeat() {
        int totalRepeat = 0;
        ArrayList<Course> courseArrayList = courseList.getValues();
        for (int i = 0; i < courseArrayList.size(); i++) {
            if (courseArrayList.get(i).getCourseType().equals("RP")) {
                totalRepeat++;
            }
        }

        return totalRepeat;
    }

    public int countElective() {
        int totalElective = 0;
        ArrayList<Course> courseArrayList = courseList.getValues();
        for (int i = 0; i < courseArrayList.size(); i++) {
            if (courseArrayList.get(i).getCourseType().equals("E")) {
                totalElective++;
            }
        }
        return totalElective;
    }

    public static ArrayListInterface<LinkedProgrammeCourse> getLinkedProgrammeCourse() {
        // Assuming linkedProgrammeCourse is an attribute of CourseManagementSystem
        return linkedProgrammeCourse;
    }

    public HashMapInterface<String, Programme> getProgrammeList() {
        return programmeList;
    }
}
