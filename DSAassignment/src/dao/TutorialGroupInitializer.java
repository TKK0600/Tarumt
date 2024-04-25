package dao;

import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import adt.HashMap;
import adt.HashMapInterface;

/**
 *
 * @author YeeTing
 */
public class TutorialGroupInitializer {
    private static HashMapInterface<String, TutorialGroup> tList = new HashMap<>();
    private static HashMapInterface<String, Boolean> assignedStudents = new HashMap<>();
    private static HashMapInterface<String, TutorialGroup> sList = new HashMap<>();

    public HashMapInterface<String, TutorialGroup> initializeTutorialGroup() {
       
    // Create Programme objects
    Programme programme1 = new Programme("RSW", "Software Engineering", "FOCS");
    Programme programme2 = new Programme("RDS", "Data Science", "FOCS");
    Programme programme3 = new Programme("RST", "Computer Science (Honours) in Interactive Software Technology", "FOCS");
    Programme programme4 = new Programme("RMM", "Bachelor of Science (Honours) in Management Mathematics with Computing", "FOCS");
    Programme programme5 = new Programme("RBF", "Bachelor of Banking and Finance (Honours)", "FAFB");
    Programme programme6 = new Programme("RAC", "Bachelor of Accounting (Honours)", "FAFB");
    Programme programme7 = new Programme("RAF", "Bachelor of Business (Honours) Accounting and Finance", "FAFB");

        
    // Create TutorialGroup objects and add them to tList
    tList.put("RSW-Software Engineering-RSW-1", new TutorialGroup(programme1, "RSW-1", 10));
    tList.put("RSW-Software Engineering-RSW-2", new TutorialGroup(programme1, "RSW-2", 5));
    tList.put("RDS-Data Science-RDS-1", new TutorialGroup(programme2, "RDS-1", 5));
    tList.put("RDS-Data Science-RDS-2", new TutorialGroup(programme2, "RDS-2", 5));
    tList.put("RST-Bachelor of Computer Science (Honours) in Interactive Software Technology-RST-1", new TutorialGroup(programme3, "RST-1", 10));
    tList.put("RST-Bachelor of Computer Science (Honours) in Interactive Software Technology-RST-2", new TutorialGroup(programme3, "RST-2", 10));
    tList.put("RMM-Bachelor of Science (Honours) in Management Mathematics with Computing-RMM-1", new TutorialGroup(programme4, "RMM-1", 10));
    tList.put("RMM-Bachelor of Science (Honours) in Management Mathematics with Computing-RMM-2", new TutorialGroup(programme4, "RMM-2", 5));
    tList.put("RBF-Bachelor of Banking and Finance (Honours)-RBF-1", new TutorialGroup(programme5, "RBF-1", 15));
    tList.put("RBF-Bachelor of Banking and Finance (Honours)-RBF-2", new TutorialGroup(programme5, "RBF-2", 15));
    tList.put("RAC-Bachelor of Accounting (Honours)-RAC-1", new TutorialGroup(programme6, "RAC-1", 20));
    tList.put("RAC-Bachelor of Accounting (Honours)-RAC-2", new TutorialGroup(programme6, "RAC-2", 5));
    tList.put("RAF-Bachelor of Business (Honours) Accounting and Finance-RAF-1", new TutorialGroup(programme7, "RAF-1", 5));
    tList.put("RAF-Bachelor of Business (Honours) Accounting and Finance-RAF-2", new TutorialGroup(programme7, "RAF-2", 5));


        
        
    return tList;
    }

    public HashMapInterface<String, TutorialGroup> initializeStudentInGroup() {
       
        assignStudentToGroup("RSW-Software Engineering-RSW-1", new Student("A1001","Alex Lee","male",new Programme("RSW", "Software Engineering", "FOCS"),"alexlee@gmail.com","0128989","BestariJaya","100%"));
        assignStudentToGroup("RSW-Software Engineering-RSW-2",new Student ("A1002","Jeremy Lee","male",new Programme("RSW", "Software Engineering", "FOCS"),"jeremylhy@gmail.com","01790989","Klang","75%"));
     //   assignStudentToGroup("RSW-Software Engineering-RSW-2",new Student ("A1003","Jack Ee","male",new Programme("RSW", "Software Engineering", "FOCS"),"jackeeyf@gmail.com","012535374","BatuPahat",0.5));
        assignStudentToGroup("RSW-Software Engineering-RSW-2",new Student ("A1004","Yee Ting","female",new Programme("RSW", "Software Engineering", "FOCS"),"ongyeeting2003@gmail.com","012-111-1111","Pekan Nanas","100%"));
        assignStudentToGroup("RMM-Bachelor of Science (Honours) in Management Mathematics with Computing-RMM-1",new Student ("A1005","Jason Lim","male",new Programme("RMM", "Bachelor of Computer Science (Honours) in Interactive Software Technology", "FOCS"),"jasonLim@gmail.com","012-000-7777","Penang","0%"));
        assignStudentToGroup("RBF-Bachelor of Banking and Finance (Honours)-RBF-1",new Student ("A1006","SIMILAN","male",new Programme("RBF", "Bachelor of Banking and Finance (Honours)", "FAFB"),"similan2003@gmail.com","012-111-1111","Subang Jaya","0%"));

       
        
        return sList;
    }

    private void assignStudentToGroup(String groupKey, Student student) {
        if (!assignedStudents.containsKey(student.getStudentID()) || !assignedStudents.get(student.getStudentID())) {
            if (sList.containsKey(groupKey)) {
                TutorialGroup group = sList.get(groupKey);
                group.addStudent(student);
                sList.put(groupKey, group);
            } else {
                // Create a new tutorial group if it doesn't exist
                TutorialGroup group = tList.get(groupKey);
                if (group != null) {
                    group.addStudent(student);
                    sList.put(groupKey, group);
                }
            }
            assignedStudents.put(student.getStudentID(), true);
        } else {
            //System.out.println("Student with ID " + studentID + " is already assigned to a group.");
        }
    }
    
   public static HashMapInterface<String,TutorialGroup> getStudentInGroupList(){
        return sList;
    }
         
    public static HashMapInterface<String, TutorialGroup> getTutorialGroupList() {
        return tList;
    }
        
    public static void main(String[] args) {
        TutorialGroupInitializer t = new TutorialGroupInitializer();
        t.initializeTutorialGroup();
        t.initializeStudentInGroup();
 }
}
