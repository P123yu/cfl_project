package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor,Long> {
    Boolean existsByMentorEmail(String mentorEmail);
}
