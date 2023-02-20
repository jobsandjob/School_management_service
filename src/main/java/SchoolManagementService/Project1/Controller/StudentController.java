package SchoolManagementService.Project1.Controller;

import SchoolManagementService.Project1.Entity.*;
import SchoolManagementService.Project1.Repositary.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Controller
public class StudentController {

    @Autowired
    private StudentRepository  studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private Class_StdRepository classStdRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentAttendanceRepositary studentAttendanceRepositary;

    @Autowired
    private Assignment_submittedRepository assignmentSubmittedRepository;
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;


    // <==============================STUDENT sector===========================>
    @GetMapping("/login_student")
    String displayRegistrationStudentForm()
    {
        return"student-login";
    }
    @PostMapping("/save_student")
    String saveStudentDisplayConfirmation(Student stdreg,Model model)
    {
        try {
            studentRepository.save(stdreg);
            System.out.println(stdreg);
            String status="Account Created Successfully";
            model.addAttribute("status",status);
            model.addAttribute("stdid",stdreg.getSid());
            return"confirmation-page-student";
        }
        catch(DataIntegrityViolationException e)
        {
            String status="Account Not Created Successfully";
            model.addAttribute("status",status);
            return"confirmation-page-student";
        }
    }

    @GetMapping("/login_student/student_homepage{student_id}")
   String displayStudentHomepage(@RequestParam Integer student_id,@RequestParam String password,Model model)
    {
       Student std = studentRepository.findBySid(student_id);
       Integer sid = std.getSclass();
       List<Subject> subjectList= subjectRepository.findAssignment(sid);
        model.addAttribute("subjectList",subjectList); //Adding all subjects where class id equals to student
        //Getting tname based on subjects tid
        ArrayList<String> listtname = new ArrayList<>() ;
       for (Subject obj:subjectList) {
           Teacher teach = teacherRepository.findByTid(obj.getTid());
           listtname.add(teach.getTname());
        }
       List<Teacher> teacherList=teacherRepository.findAll();
       model.addAttribute("std",std);
        model.addAttribute("listtname",listtname);
          return "student-homepage";
    }

    @PostMapping("/save_student_assignment{student_assignment_id}")
    String displayUploadStatusAndSave(@RequestParam byte[] student_assignment,
                                      @RequestParam Integer  student_assignment_id,
                                      @RequestParam String student_name,
                                      @RequestParam Integer student_id,
                                      @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                      @RequestParam("assignment_name") String assignment_name,
                                      @RequestParam("issued_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issued_date,
                                      Model model)
    {
        System.out.println(student_name);
        Subject assignment = subjectRepository.findBySid(student_assignment_id);
        assignment.setStudent_assignment(student_assignment);
        try {
            subjectRepository.save(assignment);
            String status = "Uploaded Successfully";
            Assignment_submitted assignmentsubmit = new Assignment_submitted();
            assignmentsubmit.setAid(assignment.getSid());
            assignmentsubmit.setStudent_name(student_name);
            assignmentsubmit.setStudent_clas(assignment.getClass_std());
            assignmentsubmit.setSubject(assignment.getSname())  ;
            assignmentsubmit.setStudent_assignment(assignment.getStudent_assignment());
            assignmentsubmit.setStudent_id(student_id);
            assignmentsubmit.setAssignment_name(assignment_name);
            assignmentsubmit.setSubmitted_date(date);
            assignmentsubmit.setIssued_date(issued_date);
            assignmentSubmittedRepository.save(assignmentsubmit);
            model.addAttribute("status",status);
            return "confirmation-page";
        }
        catch (DataIntegrityViolationException e)
        {
            String status = "Uploaded Not Successfully";
            model.addAttribute("status",status);
            return "confirmation-page";
        }
    }

    @GetMapping("/login_student/student_homepage/assignment_today")
    String displayStudentHomepageTodayAssignment(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                 @RequestParam("cid") Integer student_id,
                                                 Model model)
    {
        Student std = studentRepository.findBySid(student_id);
        Integer sid = std.getSclass();
        List<Subject> subjectList = subjectRepository.findTodayAssignment(sid,date);
        ArrayList<String> listtname = new ArrayList<>() ;
        for (Subject obj:subjectList) {
            Teacher teach = teacherRepository.findByTid(obj.getTid());
            listtname.add(teach.getTname());
        }
        List<Teacher> teacherList=teacherRepository.findAll();
        model.addAttribute("std",std);
        model.addAttribute("listtname",listtname);
        model.addAttribute("subjectList",subjectList);
        return "student-homepage";
    }

    @GetMapping("/login_student/student_homepage/assignment_change_automatically")
    String displayStudentHomepageChangeAssignmentAutomatically(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                               @RequestParam("cid") Integer student_id,
                                                               Model model)
    {
        System.out.println(date);
        Student std = studentRepository.findBySid(student_id);
        Integer sid = std.getSclass();
        System.out.println(sid);
        List<Subject> subjectList = subjectRepository.findTodayAssignment(sid,date);
        ArrayList<String> listtname = new ArrayList<>() ;
        for (Subject obj:subjectList) {
            Teacher teach = teacherRepository.findByTid(obj.getTid());
            listtname.add(teach.getTname());
        }
        List<Teacher> teacherList=teacherRepository.findAll();
        model.addAttribute("std",std);
        model.addAttribute("listtname",listtname);
        model.addAttribute("subjectList",subjectList);
        return "student-homepage";
    }

    @GetMapping("/student/profile")
    String displayProfile(@RequestParam("sid") Integer student_id,Model model)
    {
        Student std = studentRepository.findBySid(student_id);
        model.addAttribute("std",std);
        return "student-profile";
    }

    @PostMapping("/student_login/student_profile/student_edit/save")
    String displayEditSaveStudent(@RequestParam("sid") Integer student_id,
                                  @RequestParam ("sname") String sname,
                                  @RequestParam("sclass") Integer sclass,
                                  @RequestParam("scountry") String scountry,
                                  @RequestParam("saddress") String saddress,
                                  @RequestParam("sphone") String sphone,
                                  @RequestParam("semail") String semail,
                                  @RequestParam("spassword") String spassword,
                                  Model model)
    {
        Student std = studentRepository.findBySid(student_id);
        System.out.println(std);
        std.setSname(sname);
        std.setSclass(sclass);
        std.setScountry(scountry);
        std.setSaddress(saddress);
        std.setSphone(sphone);
        std.setSemail(semail);
        std.setSpassword(spassword);
        System.out.println(std);
        try{
            studentRepository.save(std);
            model.addAttribute("status","Successfull");
            return "confirmation-page";
        }
        catch (DataIntegrityViolationException e)
        {
            model.addAttribute("status","Not Successfull");
            return "confirmation-page";
        }
    }

    @GetMapping("/student_homepage/assignment_submitted")
    String displayListOfAssignmentSubmitted(@RequestParam("sid") Integer student_id,Model model)
    {
        Student std = studentRepository.findBySid(student_id);
        model.addAttribute("std",std);
        List<Assignment_submitted> assignmentSubmitteds = assignmentSubmittedRepository.findByStudent_id(student_id);
        model.addAttribute("assignmentSubmitteds",assignmentSubmitteds);
        System.out.println(assignmentSubmitteds);
        return"student-submitted-assignment";
    }


    // <==============================TEACHER sector===========================>
    @GetMapping("/login_teacher")
    String displayRegistrationTeacherForm()
    {
        return"teacher-login";
    }
    @GetMapping("/login_teacher/create_student")
    String displayStudentRegistrationForm(Model model)
    {
        model.addAttribute("stdreg",new Student());
        return"student-registration-form";
    }

    @GetMapping("/create_teacher")
    String displayTeacherRegistrationForm(Model model)
    {
        model.addAttribute("teachreg",new Teacher());
        return"teacher-registration-form";
    }

    @PostMapping("/create_teacher/save_teacher")
    String displayConfirmationMessageSavingTeacher(Model model, Teacher teachreg)
    {
        System.out.println(teachreg );
        try {
            teacherRepository.save(teachreg);
        }
        catch (DataIntegrityViolationException e){
            String error="Account Not Created Successfully";
            model.addAttribute("status",error);
            return "confirmation-page";
        }
        String successful="Account Created Successfully";
        model.addAttribute("status",successful);
        model.addAttribute("teachid",teachreg.getTid());
        return"confirmation-page";
    }
    @GetMapping("/login_teacher/teacher_homepage{teacherid}")
    String displayTeacherHomepage(@RequestParam("teacher_id") Integer teacher_id, @RequestParam("teacher_password") String teacher_password, Model model, HttpSession session)
    {
       Teacher teach =  teacherRepository.findByTid(teacher_id);
        int tid= teach.getTid();
        List<Class_Std> classStdList = classStdRepository.findTid(tid);
        model.addAttribute("classStdList",classStdList);
        model.addAttribute("teach",teach);
        session.setAttribute("teach",teach);
        return"teacher-homepage";
    }

    @GetMapping("/login_teacher/teacher_homepage/teacher_addclass")
    String displayTeacherAddclass(Model model,HttpSession session)
    {
        Teacher teach = (Teacher)session.getAttribute("teach");
        Integer tid = teach.getTid();
        System.out.println(teach);
        List<Class_Std> classStdList = classStdRepository.findTid(tid);
        System.out.println(classStdList);
        model.addAttribute("teach",teach);
        model.addAttribute("classStdList",classStdList);
        return"teacher-addclass";
    }
    @PostMapping("/subject/save_subject")
    String saveSubjectDisplayConfirmation(Subject subreg,Model model)
    {
        try{
            String status="Saved Successfully";
            subjectRepository.save(subreg);
            model.addAttribute("status",status);
            return "confirmationpage-teacher-addclass";
        }
        catch(DataIntegrityViolationException e)
        {
            String status = "Not Saved Successfully";
            model.addAttribute("status",status);
            return "confirmationpage-teacher-addclass";
        }

    }

    @GetMapping("/login_teacher/teacher_homepage/teacher_list_of_assignments")
    String displayListOfAssignment_submitted(HttpSession session,Model model)
    {
        Teacher teach=(Teacher)session.getAttribute("teach");
        System.out.println(teach);
        model.addAttribute("teach",teach);
        Integer teach_tid = teach.getTid();
       List<Subject> subjectList= subjectRepository.findByTid(teach_tid);
        System.out.println(subjectList);
        model.addAttribute("subjectList",subjectList); //Adding all subjects where class id equals to student
        //Getting tname based on subjects tid
        ArrayList<String> listtname = new ArrayList<>() ;
        for (Subject obj:subjectList) {
            Teacher teach1 = teacherRepository.findByTid(obj.getTid());
            listtname.add(teach1.getTname());
        }
        System.out.println(listtname);
        List<Teacher> teacherList=teacherRepository.findAll();
        model.addAttribute("listtname",listtname);
        return"teacher-list-of-assignments";
    }

    @GetMapping("/login_teacher/teacher_homepage/teacher_list_of_assignments/filter_date")
    String displayListOfAssignmentFilter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                         @RequestParam("tid") Integer teacher_id,
                                         Model model)
    {
        System.out.println(date);
        System.out.println(teacher_id);
        Teacher teach = teacherRepository.findByTid(teacher_id);
        model.addAttribute("teach",teach);
        Integer teach_tid = teach.getTid();
        List<Subject> subjectList= subjectRepository.findTodayAssignmentTeacher(teacher_id,date);
        System.out.println(subjectList);
        model.addAttribute("subjectList",subjectList); //Adding all subjects where class id equals to student
        //Getting tname based on subjects tid
        ArrayList<String> listtname = new ArrayList<>() ;
        for (Subject obj:subjectList) {
            Teacher teach1 = teacherRepository.findByTid(obj.getTid());
            listtname.add(teach1.getTname());
        }
        System.out.println(listtname);
        List<Teacher> teacherList=teacherRepository.findAll();
        model.addAttribute("listtname",listtname);
        return"teacher-list-of-assignments";

    }

    @GetMapping("/login_teacher/teacher_homepage/teacher_list_of_assignments/see_details")
    String displayStudentListAssignment(@RequestParam("subid") Integer sid,
                                        @RequestParam("tid") Integer tid,
                                        Model model)
    {
        Teacher teach = teacherRepository.findByTid(tid);
        model.addAttribute("teach",teach);
        List<Assignment_submitted> assignmentSubmitteds =assignmentSubmittedRepository.findByAid(sid);
        System.out.println(assignmentSubmitteds);
        model.addAttribute("assignment",assignmentSubmitteds);
        return "teacher-listof-students-assignments";
    }

    @GetMapping("/teacher/profile")
    String displayTeacherProfil(@RequestParam("tid") Integer tid,Model model)
    {
       Teacher teach =  teacherRepository.findByTid(tid);
       model.addAttribute("teach",teach);
       return "teacher-profile";
    }

    @PostMapping("/teacher_login/teacher_profile/teacher_edit/save")
    String displayTeacherProfileEdit(@RequestParam("tid") Integer tid,
                                     @RequestParam ("tname") String tname,
                                     @RequestParam("tcountry") String tcountry,
                                     @RequestParam("taddress") String taddress,
                                     @RequestParam("tphone") String tphone,
                                     @RequestParam("temail") String temail,
                                     @RequestParam("tpassword") String tpassword,
                                     Model model)    {

       Teacher teach =  teacherRepository.findByTid(tid);
       System.out.println(teach);
       teach.setTname(tname);
       teach.setTcountry(tcountry);
       teach.setTaddress(taddress);
       teach.setTphone(tphone);
       teach.setTemail(temail);
       teach.setTpassword(tpassword);
        System.out.println(teach);
        try {
            teacherRepository.save(teach);
            model.addAttribute("status","Successfull");
            return"confirmation-page";
        }
        catch (DataIntegrityViolationException e)
        {
            model.addAttribute("status","Not Successfull");
            return "confirmation-page";
        }
    }

    @GetMapping("/login_teacher/attendance_select_class/listofstudent")
    String displayListOfStudents(@RequestParam("tid") Integer tid,
                                 @RequestParam("class") Integer class_id,
                                 StudentAttendance studentAttendance,
                                 Model model)
    {
        System.out.println(studentAttendance);
        Teacher teach = teacherRepository.findByTid(tid);
        List<Student> studentList = studentRepository.findBySclass(class_id);
        System.out.println(studentList);
        if(studentAttendance != null) {
            for (Student student : studentList) {
                if (student.getSname().equals(studentAttendance.getStudent_name()))
                    studentList.remove(student);
            }
        }
        System.out.println(studentList);
        model.addAttribute("teach",teach);
        model.addAttribute("studentList",studentList);

        return"teacher-attendance-listof-students";
    }

    @GetMapping("/login_teacher/attendance_select_class")
    String displaySelectClassAttendancePage(@RequestParam("tid") Integer tid,
                                 Model model)

    {
        Teacher teach = teacherRepository.findByTid(tid);
        model.addAttribute("teach",teach);
        //List<Student> studentList = studentRepository.findBySclass(class_id);
       // System.out.println(studentList);
        List<Class_Std> classStdList = classStdRepository.findTid(tid);
        ArrayList listclass = new ArrayList();
        for (Class_Std class_std: classStdList)
        {
            listclass.add(class_std.getCname());
        }
        model.addAttribute("student_attedance",new StudentAttendance());
        model.addAttribute("classStdList",classStdList);
        model.addAttribute("listclass",listclass);
        return "teacher-attendance-select class";
    }

    @PostMapping("/login_teacher/attendance_select_class/listofstudent/save_attendance")
    RedirectView displayAttendanceAddedStatus(@RequestParam("tid") Integer tid,
                                              @RequestParam("class") Integer class_std,
                                              StudentAttendance student_attendance,
                                              RedirectAttributes attributes)
    {
        studentAttendanceRepository.save(student_attendance);
        RedirectView redirectView = new RedirectView();
        attributes.addAttribute(student_attendance);
        attributes.addAttribute("tid",tid);
        attributes.addAttribute("class",class_std);
        redirectView.setUrl("/login_teacher/attendance_select_class/listofstudent");
        return redirectView;
    }





    // <==============================MANAGEMENT sector===========================>
    @GetMapping("/management")
    String displayManagementPage(Model model)
    {
        model.addAttribute("classreg",new Class_Std());
        return"management-homepage";
    }
    @PostMapping("/management/save_class")
    String displayConfirmationPage(Model model,Class_Std classreg)
    {
        System.out.println(classreg);
        try {

            classStdRepository.save(classreg);
            String success = "Saved Successfully";
            model.addAttribute("status",success);
            return"confirmation-page-management";
        }
        catch (DataIntegrityViolationException e)
        {
            String failure = "OOPS...!!! Not Saved Successfully";
            model.addAttribute("status",failure);
            return"confirmation-page-management";
        }

    }

    @GetMapping("/management/see_class")
    String displayListOfClass(Model model)
    {
       List<Class_Std> classStdList= classStdRepository.findAll();
       if(classStdList==null){
           String status="NO ENTRIES ARE THERE";
           model.addAttribute("status",status);
           return "confirmation-page-management";
       }
       else{
           model.addAttribute("classStdList",classStdList);
           return "management-class-details";
       }

    }

    // <==============================Common Method===========================>


}