package com.itmo.tpo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProblemRow {

    private List<String> tags;
    private Long difficulty;
    private String name;
    private String problemNum;
    private Long solutionNumber;
}
