package SchoolManagementService.Project1.Repositary;
import SchoolManagementService.Project1.Entity.Class_Std;
import SchoolManagementService.Project1.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Class_StdRepository extends JpaRepository<Class_Std,Integer> {

    @Query(value = "SELECT * FROM class_std WHERE tid=:tid",nativeQuery = true)
    List<Class_Std> findTid(@Param("tid") Integer tid);
}
