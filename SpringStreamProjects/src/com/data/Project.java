package com.data;

import lombok.*;

@AllArgsConstructor
@Data
@Setter
@Getter
public class Project {
    private String projectCode;
    private String name ;
    private String client;
    private String buLeadName;
}
