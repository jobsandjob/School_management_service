package SchoolManagementService.Project1.Repositary;

import SchoolManagementService.Project1.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Query(value = "SELECT * FROM subject WHERE subject.Teacher_ID=:tid", nativeQuery = true)
    List<Subject> findClassId(@Param("tid") Integer tid);

    @Query(value = "SELECT * FROM subject WHERE subject.Class_ID=:sid", nativeQuery = true)
    List<Subject> findAssignment(@Param("sid") Integer sid);

    Subject findBySid(Integer sid);

    @Query(value = "SELECT * FROM subject WHERE subject.issued_date=:date AND subject.Class_ID=:sid", nativeQuery = true)
    List<Subject> findTodayAssignment(@Param("sid") Integer sid, @Param("date") LocalDate date);

    List<Subject> findByTid(Integer teachTid);

    @Query(value = "SELECT * FROM subject WHERE subject.issued_date=:date AND subject.Teacher_ID=:teacher_id", nativeQuery = true)
    List<Subject> findTodayAssignmentTeacher(@Param("teacher_id") Integer teacher_id, @Param("date") LocalDate date);
}
