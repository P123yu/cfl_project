package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cfl_skill")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CflSkill {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long empId;
    private String primarySkills;
    private String secondarySkills;
    private String otherSkills;
}
