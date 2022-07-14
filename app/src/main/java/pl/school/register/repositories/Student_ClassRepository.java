package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Student_Class;

import java.util.List;

@Repository
public interface Student_ClassRepository extends JpaRepository<Student_Class, Long> {
    List<Student_Class> findAllBySchoolClassIdOrderByIndex(Long school_class_id);
}
