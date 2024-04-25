package entity;

public class RegisteredStudent {
    private Course course;
    private Student student;

    public RegisteredStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    @Override
public String toString() {
    String format = "| %-10s | %-25s | %-10s | %-12s | %-25s | %-13s | %-19s | %-10s |\n"
                  + "+------------+---------------------------+------------+--------------+---------------------------+---------------+---------------------+------------+\n";
    return String.format(format, student.getStudentID(), student.getName(), student.getGender(), student.getProgramme().getProgrammeID(), student.getEmail(), student.getPhoneNumber(), student.getAddress(), student.getScholarship());
}

}
