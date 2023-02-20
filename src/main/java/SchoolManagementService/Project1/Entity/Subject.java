package SchoolManagementService.Project1.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Subject_ID",nullable = false)
    private Integer sid;
    @Column(name = "Subject_Name")
    private String sname;
    @Column(name = "Assignment",nullable = true)
    private String assignment;
   @Column(name = "Class_ID")
    private Integer class_std;
    @Column(name = "Teacher_ID")
    private Integer tid;
    @Column(name = "Issued_Date")
    private LocalDate issue_date;
    @Column(name = "Last_Date")
    private LocalDate last_date;

    @Lob
    @Column(nullable = true)
    private byte[] student_assignment;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public Integer getClass_std() {
        return class_std;
    }

    public void setClass_std(Integer class_std) {
        this.class_std = class_std;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public byte[] getStudent_assignment() {
        return student_assignment;
    }

    public void setStudent_assignment(byte[] student_assignment) {
        this.student_assignment = student_assignment;
    }

    public LocalDate getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(LocalDate issue_date) {
        this.issue_date = issue_date;
    }

    public LocalDate getLast_date() {
        return last_date;
    }

    public void setLast_date(LocalDate last_date) {
        this.last_date = last_date;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", assignment='" + assignment + '\'' +
                ", class_std=" + class_std +
                ", tid=" + tid +
                ", issue_date=" + issue_date +
                ", last_date=" + last_date +
                ", student_assignment=" + Arrays.toString(student_assignment) +
                '}';
    }
}
