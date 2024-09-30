package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cfl_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cfl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "emp_id", nullable = true)
    private Long empId;

    @Column(name = "cfl_first_name", nullable = true)
    private String cflFirstName;

    @Column(name = "cfl_middle_name", nullable = true)
    private String cflMiddleName;

    @Column(name = "cfl_last_name", nullable = true)
    private String cflLastName;

    @Column(name = "joining_date", nullable = true)
    private String joiningDate;

    @Column(name = "sub_area", nullable = true)
    private String subArea;

    @Column(name = "cfl_designation", nullable = true)
    private String cflDesignation;

    @Column(name = "cfl_department", nullable = true)
    private String cflDepartment;

    @Column(name = "cfl_project", nullable = true)
    private String cflProject;

    @Column(name = "cfl_department_description", nullable = true)
    private String cflDepartmentDescription;

    @Column(name = "cfl_sub_department", nullable = true)
    private String cflSubDepartment;

    @Column(name = "cfl_sub_dept_description", nullable = true)
    private String cflSubDeptDescription;

    @Column(name = "cfl_project_classification", nullable = true)
    private String cflProjectClassification;

    @Column(name = "bu_head_name", nullable = true)
    private String buHeadName;

    @Column(name = "cfl_email", nullable = true)
    private String cflEmail;

    @Column(name = "contact", nullable = true)
    private String contact;

    @Column(name = "gender", nullable = true)
    private String gender;

    @Column(name = "category", nullable = true)
    private String category;

    @Column(name = "cfl_vertical", nullable = true)
    private String vertical;

    @Column(name = "cfl_location", nullable = true)
    private String cflLocation;

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

    @Column(name = "file_name", nullable = true)
    private String fileName;

//    @Lob
//    @Column(name = "file_data", columnDefinition = "LONGBLOB", nullable = true)
//    private byte[] fileData;

    @Lob
    @Column(name = "file_data", columnDefinition = "BYTEA", nullable = true)
    private byte[] fileData;

    // Mentor information
    @Column(name = "mentor_id", nullable = true)
    private Long mentorId;

    @Column(name = "reporting_manager", nullable = true)
    private String reportingManager;

    @Column(name = "manager_email", nullable = true)
    private String reportingManagerMail;

    @Column(name = "hr_mail", nullable = true)
    private String hrMail;

    @Column(name = "email_acceptance", nullable = true)
    private String emailAcceptance;

    @Column(name = "email_declined", nullable = true)
    private String emailDeclined;

    @Column(name = "goal_setting_status_hr_to_mgr", nullable = true)
    private Boolean goalSettingStatusHrToMgr;

    @Column(name = "probation_status", nullable = true)
    private Boolean probationStatus;

    @Column(name = "cfl_mgr_quarter_status", nullable = true)
    private Boolean cflMgrQuarterStatus;

    // cfl count
    @Column(name = "cfl_count", nullable = true)
    private Boolean cflCountStatus;

}
