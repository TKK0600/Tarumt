/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.HashMapInterface;

/**
 *
 * @author kahki
 */
public class LinkedProgrammeCourse {
    private String programmeCode;
    private String courseID;

    public LinkedProgrammeCourse() {
    }

    public LinkedProgrammeCourse(String programmeCode, String courseID) {
        this.programmeCode = programmeCode;
        this.courseID = courseID;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    
    @Override
    public String toString() {
        return "linkedProgrammeCourse{" + "programmeCode=" + programmeCode + ", courseID=" + courseID + '}';
    }
    
    
}

