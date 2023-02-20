package SchoolManagementService.Project1.Entity;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Assignment_submitted {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
   private Integer aid;
   private String student_name;
   private String assignment_name;
   private Integer student_clas;
   private Integer student_id;
   private String subject;
   private LocalDate submitted_date;
   private LocalDate issued_date;
    @Lob
   private byte [] student_assignment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Integer getStudent_clas() {
        return student_clas;
    }

    public void setStudent_clas(Integer student_clas) {
        this.student_clas = student_clas;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public byte[] getStudent_assignment() {
        return student_assignment;
    }

    public void setStudent_assignment(byte[] student_assignment) {
        this.student_assignment = student_assignment;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public LocalDate getSubmitted_date() {
        return submitted_date;
    }

    public void setSubmitted_date(LocalDate submitted_date) {
        this.submitted_date = submitted_date;
    }

    public String getAssignment_name() {
        return assignment_name;
    }

    public void setAssignment_name(String assignment_name) {
        this.assignment_name = assignment_name;
    }

    public LocalDate getIssued_date() {
        return issued_date;
    }

    public void setIssued_date(LocalDate issued_date) {
        this.issued_date = issued_date;
    }

    @Override
    public String toString() {
        return "Assignment_submitted{" +
                "id=" + id +
                ", aid=" + aid +
                ", student_name='" + student_name + '\'' +
                ", assignment_name='" + assignment_name + '\'' +
                ", student_clas=" + student_clas +
                ", student_id=" + student_id +
                ", subject='" + subject + '\'' +
                ", submitted_date=" + submitted_date +
                ", issued_date=" + issued_date +
                ", student_assignment=" + Arrays.toString(student_assignment) +
                '}';
    }
}
