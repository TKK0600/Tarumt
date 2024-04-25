package dao;

import entity.AssignmentTeam;
import entity.Programme;
import entity.TutorialGroup;
import entity.Student;
import adt.ArrayList;
import adt.HashMap;
import adt.HashMapInterface;

public class AssignmentTeamInitializer {
    public static HashMapInterface<String, AssignmentTeam> initializeTeams() {
        HashMapInterface<String, AssignmentTeam> teamMap = new HashMap<>();

        // Create and add AssignmentTeam objects
        AssignmentTeam team1 = new AssignmentTeam("T1", new TutorialGroup(new Programme("RSW","Software Engineering","FOCS"),"RSW-1",10), "5");
        Student student1 = new Student("A1001","Alex Lee","male",new Programme("RSW", "Software Engineering", "FOCS"),"alexlee@gmail.com","0128989","BestariJaya","100%");
        team1.addTeamMember(student1);

        AssignmentTeam team2 = new AssignmentTeam("T2", new TutorialGroup(new Programme("RSW","Software Engineering","FOCS"),"RSW-1",10), "5");
        Student student2 = new Student("A1002","Jeremy Lee","male",new Programme("RSW", "Software Engineering", "FOCS"),"jeremylhy@gmail.com","01790989","Klang","75%");
        Student student3 = new Student("A1003","Jack Ee","male",new Programme("RSW", "Software Engineering", "FOCS"),"jackeeyf@gmail.com","012535374","BatuPahat","50%");
        //Student student4 = new Student("A1004", "Yee Ting", "female",new Programme("RSW","Software Engineering","FOCS"),"ongyeeting2003@gmail.com","012-111-1111","Pekan Nanas",1.0);
        team2.addTeamMember(student2);
        team2.addTeamMember(student3);
        //team2.addTeamMember(student4);
        
        AssignmentTeam team3 = new AssignmentTeam("T3", new TutorialGroup(new Programme("RMM","Bachelor of Science (Honours) in Management Mathematics with Computing","FOCS"),"RMM-1",10), "5");
        Student student5 = new Student("A1005","Jason Lim","male",new Programme("RMM","Bachelor of Science (Honours) in Management Mathematics with Computing","FOCS"),"jasonLim@gmail.com","012-000-7777","Penang","0%");    
        team3.addTeamMember(student5);
        
        AssignmentTeam team4 = new AssignmentTeam("T4", new TutorialGroup(new Programme("RBF","Bachelor of Banking and Finance (Honours)","FAFB"),"RBF-1",10), "5");
        Student student6 = new Student("A1006","SIMILAN","male",new Programme("RBF", "Bachelor of Banking and Finance (Honours)", "FAFB"),"similan2003@gmail.com","012-111-1111","Subang Jaya","0%");
        
        team4.addTeamMember(student6);

//
//        AssignmentTeam team3 = new AssignmentTeam("T3", "1", "6");
//        team3.addTeamMember("Mary");
//        team3.addTeamMember("David");

        // Add teams to the map with assignment team name as key
        teamMap.put(team1.getAssignmentTeam(), team1);
        teamMap.put(team2.getAssignmentTeam(), team2);
        teamMap.put(team3.getAssignmentTeam(), team3);
        teamMap.put(team4.getAssignmentTeam(), team4);
        return teamMap;
    }

    public static void displayTeams(HashMapInterface<String, AssignmentTeam> teamMap) {
        System.out.println("Initialized Assignment Teams:");
        for (HashMapInterface.Entry<String, AssignmentTeam> entry : teamMap.entries()) {
            String teamName = entry.getKey();
            AssignmentTeam team = entry.getValue();
            System.out.println("Team Name: " + teamName);
            System.out.println("Tutorial Group: " + team.getTutorialGroup());
            System.out.println("Max Size: " + team.getMaxSize());
            System.out.println("Team Members: " );
            ArrayList<Student> teamMembers = team.getTeamMembers();
            for (int i = 0; i < teamMembers.size(); i++) {
            Student member = teamMembers.get(i);
            System.out.println(member.getStudentID() + " " + member.getName());
}
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AssignmentTeamInitializer initializer = new AssignmentTeamInitializer();
        HashMapInterface<String, AssignmentTeam> teamMap = initializer.initializeTeams();
        displayTeams(teamMap);
    }
    
        public HashMapInterface<String, AssignmentTeam> getinitializeTeams() {
         return initializeTeams();
    }
}

    

