package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="mentor_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empId;
    private Long mentorId;
    private String mentorName;
    private String mentorEmail;
    private String mentorDepartment;
    private String mentorLocation;
    private String mentorDesignation;
    private String mentorFileName;

//    @Lob
//    @Column(name = "mentor_file_data", columnDefinition = "LONGBLOB", nullable = false)
//    private byte[] mentorFileData;


    @Lob
    @Column(name = "mentor_file_data", columnDefinition = "BYTEA", nullable = false)
    private byte[] mentorFileData;


}
