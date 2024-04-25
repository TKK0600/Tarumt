package entity;

import adt.ArrayList;

/**
 *
 * @author jiachuan
 */
public class AssignmentTeam {

    private String assignmentTeam;
    private TutorialGroup tutorialGroup;
    private String maxSize;
    private ArrayList<Student> teamMembers;

    public AssignmentTeam(String assignmentTeam, TutorialGroup tutorialGroup, String maxSize) {
        this.assignmentTeam = assignmentTeam;
        this.tutorialGroup = tutorialGroup;
        this.maxSize = maxSize;
        this.teamMembers = new ArrayList<>();
    }

    public String getAssignmentTeam() {
        return assignmentTeam;
    }

    public void setAssignmentTeam(String assignmentTeam) {
        this.assignmentTeam = assignmentTeam;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public ArrayList<Student> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(ArrayList<Student> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public boolean addTeamMember(Student student) {
        if (teamMembers.size() < Integer.parseInt(getMaxSize())) {
            teamMembers.add(student);
            return true;
        }
        return false; // Limit reached, cannot add more members
    }

    @Override
    public String toString() {
        return "AssignmentTeam{"
                + "assignmentTeam='" + assignmentTeam + '\''
                + ", tutorialGroup='" + tutorialGroup + '\''
                + ", maxSize='" + maxSize + '\''
                + ", teamMembers=" + teamMembers
                + '}';
    }

    public void setAssignmentTeamName(String newTeamName) {
        this.assignmentTeam = newTeamName;
    }

    public String getAssignmentTeamName() {
        return assignmentTeam;
    }

    public void setTutorialGroup(String newTutorialGroup) {
        this.tutorialGroup.setTutorialGroup(newTutorialGroup);
    }
}
