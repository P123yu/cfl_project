package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="manager_rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ManagerRating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private Long empId;
    private String internal1;
    private String internal2;
    private String internal3;
    private String project1;
    private String project2;
    private String project3;
    private String talentLevel;
    private String potentialLevel;
    private String performanceLevel;
    private String quarter;

}
