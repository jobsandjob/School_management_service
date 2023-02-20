package SchoolManagementService.Project1.Repositary;

import SchoolManagementService.Project1.Entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAttendanceRepositary extends JpaRepository<StudentAttendance,Integer> {
}
