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
    @Lob
    @Column(columnDefinition = "TEXT")
    private String primarySkills;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String secondarySkills;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String otherSkills;
    private String quarter;
}
