package SchoolManagementService.Project1.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Class_Std {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_generator;
    @Column(name = "Class_ID",nullable = false)
    private Integer clid;
    @Column(name = "Class_Name",nullable = false)
    private String cname;
    @Column(name = "TID",nullable = true)
    private Integer tid;
    @Column(name = "Subject_Name",nullable = false)
   private String subname;

    public Integer getClid() {
        return clid;
    }

    public void setClid(Integer clid) {
        this.clid = clid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getId_generator() {
        return id_generator;
    }

    public void setId_generator(Integer id_generator) {
        this.id_generator = id_generator;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    @Override
    public String toString() {
        return "Class_Std{" +
                "id_generator=" + id_generator +
                ", clid=" + clid +
                ", cname='" + cname + '\'' +
                ", tid=" + tid +
                ", subname='" + subname + '\'' +
                '}';
    }
}