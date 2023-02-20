package SchoolManagementService.Project1.Repositary;

import SchoolManagementService.Project1.Entity.Assignment_submitted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Assignment_submittedRepository extends JpaRepository<Assignment_submitted,Integer> {
    List<Assignment_submitted> findByAid(Integer sid);

    @Query(value = "SELECT * FROM assignment_submitted WHERE assignment_submitted.student_id = :student_id", nativeQuery = true)
    List<Assignment_submitted> findByStudent_id(@Param("student_id") Integer student_id);
}
