package com.example.demotask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GitHubResponse {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<GitHubRepository> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GitHubRepository {
        private String name;
        private Map<String, Object> owner;
    }
}
