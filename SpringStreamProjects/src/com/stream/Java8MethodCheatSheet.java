package com.stream;

import com.data.Employee;
import com.data.EmployeeDatabase;
import com.data.Project;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8MethodCheatSheet {
    public static void main(String[] args) {

        List<Employee> employees = EmployeeDatabase.getAllEmployees();

        // foreach  takes Consumer interface which is functional Interface with single method apply(T t)
//        employees.forEach(e-> System.out.println(e.getName()+" "+e.getDept()));

        // filter
        // show employees who belong to development

        Map<Integer, List<Project>> map = employees.stream().filter(e -> e.getDept().equals("Development") && e.getSalary() > 80000).collect(Collectors.toMap(Employee::getId, Employee::getProjectList));
//        System.out.println(map);

        // map

        Set<String> deptSet = employees.stream().map(Employee::getDept).collect(Collectors.toSet());
//        System.out.println(deptSet);

        List<String> deptList = employees.stream().map(Employee::getDept).distinct().collect(Collectors.toList());

        // cannot be used normal map we have to use flatmap
//        Set<Stream<String>> projectList = employees.stream().map(e -> e.getProjectList().stream().map(Project::getName)).collect(Collectors.toSet());

        // IN one to many mapping, if we want to show the child class data then we have to use flatmap instead of map
        Set<String> projectSet = employees.stream().flatMap(e -> e.getProjectList().stream().map(Project::getName)).collect(Collectors.toSet());
//        System.out.println(projectSet);

        // sorted
        // sort list of employee based on salary
        List<Employee> eSortedSal = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
//        System.out.println(eSortedSal);

        // min && max
//       show employee with the highest salary

        Optional<Employee> maxSalary = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));

        Optional<Employee> minSalary = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));

        // show male and female employee
        Map<String, List<String>> genderName = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.mapping(Employee::getName, Collectors.toList())));
//        System.out.println(genderName);

        Map<String, Long> genderCount = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
//        System.out.println(genderCount);

        // Filter find first

        Optional<Employee> eFindFirst = employees.stream().filter(e -> e.getDept().equals("Development")).findFirst();
        if (eFindFirst.isPresent()) {
//            System.out.println(eFindFirst.get());
        }


        Employee eFirst = employees.stream().filter(e -> e.getDept().equals("Development")).findFirst().orElseThrow(() -> new RuntimeException("No employee found"));

//        System.out.println(eFirst);

        //findany

        Optional<Employee> eAny = employees.stream().filter(e -> e.getDept().equals("Development")).findAny();
        if (eAny.isPresent()) {
//            System.out.println(eAny.get());
        }

        //anymatch(Predicate),allmatch,nonematch

        boolean anyMatchObj = employees.stream().anyMatch(e -> e.getDept().equals("Development"));
//        System.out.println(anyMatchObj);

        boolean allMatch = employees.stream().allMatch(employee -> employee.getDept().equals("Development"));
//        System.out.println(allMatch);
        boolean allMatchSalary = employees.stream().allMatch(e -> e.getSalary() > 50000);
//        System.out.println(allMatchSalary);

        // limit
        List<String> topSalary = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).map(Employee::getName).limit(3).collect(Collectors.toList());
//        System.out.println(topSalary);

        // skip(long)

        Set<Employee> skipThree = employees.stream().skip(5).collect(Collectors.toSet());
//        System.out.println(skipThree);
    }
}
