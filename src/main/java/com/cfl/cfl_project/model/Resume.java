package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="resume_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private Long empId;
    private LocalDate date;
    private LocalTime time;
    @Column(name = "file_name")
    private String resumeFileName;
//    @Lob
//    @Column(name = "file_data", columnDefinition = "LONGBLOB")
//    private byte[] resumeFileData;

    @Lob
    @Column(name = "file_data", columnDefinition = "BYTEA")
    private byte[] resumeFileData;
}
