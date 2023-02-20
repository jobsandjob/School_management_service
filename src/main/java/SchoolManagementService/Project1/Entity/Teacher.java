package SchoolManagementService.Project1.Entity;

import SchoolManagementService.Project1.Repositary.Class_StdRepository;
import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Teacher_ID",nullable = false)
    private Integer tid;

    @Column(name = "Teacher_Name",nullable = false)
    private String tname;
    @Column(name = "Teacher_Password",nullable = false)
    private String tpassword;
    @Column(name = "Teacher_SubjectID",nullable = true)
    private Integer tsubject;
    @Column(name = "Teacher_SubjectName",nullable = true)
    private String tsubjectname;
    @Column(name = "Teacher_Country")
    private String tcountry;
    @Column(name = "Teacher_Address")
    private String taddress;
    @Column(name = "Teacher_Phone")
    private String tphone;
    @Column(name = "Teacher_Email")
    private String temail;


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getTsubject() {
        return tsubject;
    }

    public void setTsubject(Integer tsubject) {
        this.tsubject = tsubject;
    }

    public String getTsubjectname() {
        return tsubjectname;
    }

    public void setTsubjectname(String tsubjectname) {
        this.tsubjectname = tsubjectname;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    public String getTcountry() {
        return tcountry;
    }

    public void setTcountry(String tcountry) {
        this.tcountry = tcountry;
    }

    public String getTaddress() {
        return taddress;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tpassword='" + tpassword + '\'' +
                ", tsubject=" + tsubject +
                ", tsubjectname='" + tsubjectname + '\'' +
                ", tcountry='" + tcountry + '\'' +
                ", taddress='" + taddress + '\'' +
                ", tphone='" + tphone + '\'' +
                ", temail='" + temail + '\'' +
                '}';
    }
}