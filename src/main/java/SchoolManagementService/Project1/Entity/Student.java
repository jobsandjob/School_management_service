package SchoolManagementService.Project1.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Arrays;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Student_ID",nullable = false)
    private Integer sid;
    @Column(name = "Student_Name",nullable = false)
    private String sname;
    @Column(name = "Student_Gender",nullable = false)
    private String sgender;
    @Column(name = "Student_Class",nullable = false)
    private Integer sclass;
    @Column(name = "Student_Password",nullable = false)
    private String spassword;
    @Column(name="Student_Country")
    private String scountry;
    @Column(name = "Student_Address")
    private String saddress;
    @Column(name = "Student_Phone")
    private String sphone;
    @Column(name = "Student_Email")
    private String semail;


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

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    public Integer getSclass() {
        return sclass;
    }

    public void setSclass(Integer sclass) {
        this.sclass = sclass;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getScountry() {
        return scountry;
    }

    public void setScountry(String scountry) {
        this.scountry = scountry;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }


    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sgender='" + sgender + '\'' +
                ", sclass=" + sclass +
                ", spassword='" + spassword + '\'' +
                ", scountry='" + scountry + '\'' +
                ", saddress='" + saddress + '\'' +
                ", sphone='" + sphone + '\'' +
                ", semail='" + semail + '\'' +
                '}';
    }
}