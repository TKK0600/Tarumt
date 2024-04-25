/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author e-default
 */
public class Programme {
    private String programmeID;
    private String programmeName;
    private String programmeFac;

    public Programme() {
    }
    

    public Programme(String programmeID, String programmeName, String programmeFac) {
        this.programmeID = programmeID;
        this.programmeName = programmeName;
        this.programmeFac = programmeFac;
    }

    public String getProgrammeID() {   //getCode
        return programmeID;
    }

    public String getProgrammeName() {  //get name
        return programmeName;
    }

    public String getProgrammeFac() {  //get program Faculty
        return programmeFac;
    }

    public void setProgrammeID(String programmeID) {  // set Code
        this.programmeID = programmeID;
    }

    public void setProgrammeName(String programmeName) {  //set Name
        this.programmeName = programmeName;
    }

    public void setProgrammeFac(String programmeFac) {
        this.programmeFac = programmeFac;
    }

  @Override
public String toString() {
    String format = "| %-10s | %-75s | %-10s |\n";
    StringBuilder sb = new StringBuilder();
    sb.append(String.format(format, programmeID, programmeName, programmeFac));
    sb.append("+------------+-----------------------------------------------------------------------------+------------+");
    return sb.toString();
}


    
    
}
