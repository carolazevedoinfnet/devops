package br.edu.infnet.devops.repository;

import br.edu.infnet.devops.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
}


