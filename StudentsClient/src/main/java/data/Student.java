package data;

public class Student {
    private String firstName;
    private boolean isEnrolled;
    private double GPA;
    private String studentID;

    public Student(String firstName, boolean isEnrolled, double GPA, String studentID) {
        this.firstName = firstName;
        this.isEnrolled = isEnrolled;
        this.GPA = GPA;
        this.studentID = studentID;
    }

    public Student() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }



    public boolean isEnrolled() {
        return isEnrolled;
    }

    public double getGPA() {
        return GPA;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", isEnrolled=" + isEnrolled +
                ", GPA=" + GPA +
                ", studentID='" + studentID + '\'' +
                '}';
    }
}
