package SchoolManagementService.Project1.Repositary;

import SchoolManagementService.Project1.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Teacher findByTid(Integer tid);

}
