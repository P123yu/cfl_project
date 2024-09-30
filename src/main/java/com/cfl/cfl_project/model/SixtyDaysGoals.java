package com.cfl.cfl_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SixtyDaysGoals {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private Long empId;
    private String goal;
    private String deliverable;
    private String status;
    private String weightage;
    private String quarter;
}
