package entity;

import adt.ArrayList;
import java.io.Serializable;

/**
 *
 * @author YeeTing
 */

public class TutorialGroup implements Serializable {
    private Programme programme;
    private String tutorialGroupName;
    private int maxCapacity;
    private ArrayList<Student> students;

 
    public TutorialGroup(Programme programme,String tutorialGroupName,int maxCapacity, ArrayList<Student> students) {
        this.programme = programme;
        this.tutorialGroupName = tutorialGroupName;
        this.maxCapacity=maxCapacity;
        this.students = students; // Initialize the students list with the provided students
    }
    
      public TutorialGroup(Programme programme, String tutorialGroupName, int maxCapacity) {
        this.programme = programme;
        this.tutorialGroupName = tutorialGroupName;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>(); // Initialize the students list

    }
      
      public TutorialGroup(String tutorialGroupName, int maxCapacity) {
        this.tutorialGroupName = tutorialGroupName;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>(); // Initialize the students list
    }
    

        
    public String getTutorialGroupName() {
        return tutorialGroupName;
    }

    public void setTutorialGroupName(String tutorialGroupName) {
        this.tutorialGroupName = tutorialGroupName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    
    public ArrayList<Student> getStudents() {
        return students;
    }

   public void addStudent(Student student) {
    // Add the student to the list
    this.students.add(student);
}

public void removeStudent(Student student) {
    // Remove the student from the list
    this.students.remove(student);
}

    public Programme getProgramme(){
        return programme;
    }
    
     public void setTutorialGroup(String newTutorialGroup) {
        this.tutorialGroupName = newTutorialGroup;
    }


    @Override
    public String toString() {
        return String.format("%-20s%-20s%10s%n", programme.getProgrammeID(), programme.getProgrammeName(), tutorialGroupName);
    }
}
