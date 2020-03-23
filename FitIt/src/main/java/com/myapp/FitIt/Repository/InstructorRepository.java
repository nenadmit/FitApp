package com.myapp.FitIt.Repository;

import com.myapp.FitIt.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    @Override
    Optional<Instructor> findById(Integer integer);

    Instructor findByUsername(String username);
}
