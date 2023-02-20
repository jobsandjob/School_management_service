package SchoolManagementService.Project1.Repositary;

import SchoolManagementService.Project1.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Integer> {
    Student findBySid(Integer studentId);

    List<Student> findBySclass(Integer classId);
}
