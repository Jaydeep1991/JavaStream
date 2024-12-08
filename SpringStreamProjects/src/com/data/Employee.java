package com.data;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {
    private int id;
    private String name;
    private String dept;
    private List<Project> projectList;
    private double salary;
    private String gender;

}
