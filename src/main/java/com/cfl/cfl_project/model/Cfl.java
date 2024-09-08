package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="cfl_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cfl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true, updatable = true)
    private Long id;

    @Column(name = "emp_id", nullable = true)
    private Long empId;

    @Column(name = "cfl_first_name", nullable = true)
    private String cflFirstName;

    @Column(name = "cfl_middle_name", nullable = true)
    private String cflMiddleName;

    @Column(name = "cfl_last_name", nullable = true)
    private String cflLastName;

    @Column(name = "cfl_email", nullable = true)
    private String cflEmail;

    @Column(name = "cfl_department", nullable = true)
    private String cflDepartment;

    @Column(name = "cfl_designation", nullable = true)
    private String cflDesignation;

    @Column(name = "reporting_manager", nullable = true)
    private String reportingManager;

    @Column(name = "cfl_location", nullable = true)
    private String cflLocation;

    @Column(name = "joining_date", nullable = true)
    private LocalDate joiningDate;

    @Column(name = "file_name", nullable = true)
    private String fileName;

    @Column(name = "year", nullable = true)
    private String year;

    @Column(name = "ssc_result", nullable = true)
    private String sscResult;

    @Column(name = "hsc_result", nullable = true)
    private String hscResult;

    @Column(name = "under_graduate_result", nullable = true)
    private String underGraduateResult;

    @Column(name = "post_graduate_result", nullable = true)
    private String postGraduateResult;

    @Column(name = "college_name", nullable = true)
    private String collegeName;

    @Column(name = "college_branch", nullable = true)
    private String collegeBranch;

    @Column(name = "technical_skills", nullable = true)
    private String technicalSkills;

    @Column(name = "non_technical_skills", nullable = true)
    private String nonTechnicalSkills;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB", nullable = true)
    private byte[] fileData;

    // ------------------------------------------

    @Column(name = "mentor_id", nullable = true)
    private Long mentorId;




}
