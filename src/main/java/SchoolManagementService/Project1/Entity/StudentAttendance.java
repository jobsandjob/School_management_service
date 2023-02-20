package SchoolManagementService.Project1.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_generator")
    @SequenceGenerator(sequenceName = "sequence_generator",name = "my_generator",initialValue = 65500,allocationSize = 50)
    private Integer id;
    private String student_name;
    private String teacher_name;
    private String subject;
    private LocalDate date;
    private String attendance;

    public StudentAttendance() {
    }

    public StudentAttendance(String student_name, String teacher_name, String subject, LocalDate date, String attendance) {

        this.student_name = student_name;
        this.teacher_name = teacher_name;
        this.subject = subject;
        this.date = date;
        this.attendance = attendance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "id=" + id +
                ", student_name='" + student_name + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                ", attendance='" + attendance + '\'' +
                '}';
    }
}
