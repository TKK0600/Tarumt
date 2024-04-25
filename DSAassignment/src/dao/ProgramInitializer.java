/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Course;
import entity.Programme;
import adt.HashMap;
import adt.HashMapInterface;

/**
 *
 * @author jiachuan
 */
public class ProgramInitializer {
    private static HashMapInterface<String, Programme> ProgramList = new HashMap<>();

    
    public ProgramInitializer(){
        initialise_Programme();
        
    }
    
    public void initialise_Programme(){
        ProgramList.put("RSW", new Programme("RSW", "Software Engineering", "FOCS"));
        ProgramList.put("RDS", new Programme("RDS", "Data Science", "FOCS"));
        ProgramList.put("RST", new Programme("RST", "Bachelor of Computer Science (Honours) in Interactive Software Technology", "FOCS"));
        ProgramList.put("RMM", new Programme("RMM", "Bachelor of Science (Honours) in Management Mathematics with Computing", "FOCS"));
        ProgramList.put("RBF", new Programme("RBF", "Bachelor of Banking and Finance (Honours)", "FAFB"));
        ProgramList.put("RAC", new Programme("RAC", "Bachelor of Accounting (Honours)", "FAFB"));
        ProgramList.put("RAF", new Programme("RAF", "Bachelor of Business (Honours) Accounting and Finance", "FAFB"));
        ProgramList.put("RBC", new Programme("RBC", "Bachelor of Commerce (Honours)", "FAFB"));
        ProgramList.put("RED", new Programme("RED", "Bachelor of Communication Studies (Honours)", "FCCI"));
        ProgramList.put("RAD", new Programme("RAD", "Bachelor of Early Childhood Education (Honours)", "FCCI"));
        ProgramList.put("RTY", new Programme("RTY", "Bachelor of Economics (Honours)", "FSSH"));
        ProgramList.put("RRP", new Programme("RRP", "Bachelor in Marketing (Honours)", "FSSH"));
        ProgramList.put("RWQ", new Programme("RWQ", "Bachelor of Mechanical Engineering with Honours ", "FOBE"));
        ProgramList.put("ROP", new Programme("ROP", "Bachelor of Mechatronics Engineering with Honours", "FOBE"));
        ProgramList.put("RTP", new Programme("RTP", "Bachelor of Communication (Honours) in Media Studies", "FOBE"));
        ProgramList.put("ROK", new Programme("ROK", "Bachelor of Science (Hons) in Nutrition", "FCCI"));
        ProgramList.put("RKO", new Programme("RKO", "Bachelor of Quantity Surveying (Honours)", "FCCI"));
    }
    

    
    public static void printAllProgramme(HashMapInterface<String, Programme> hashmap) {
    for (HashMapInterface.Entry<String, Programme> entry : hashmap.entries()) {
        System.out.println(entry.getValue());
    }
}

    public static HashMapInterface<String, Programme> getProgrammeList() {
        return ProgramList;
    }
  
    public static void main(String[] args) {

        ProgramInitializer initializer = new ProgramInitializer();
        System.out.println("Program List:");
        HashMapInterface<String, Programme> programList = initializer.getProgrammeList();
        printAllProgramme(ProgramList);
    }

}
