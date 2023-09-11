package com.example.demotask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubResponse {
    private List<GitHubRepository> data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GitHubRepository {
        private String name;
        private Owner owner;
        private List<Branch> branches;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Owner {
            private String login;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Branch {
            private String name;
            private Commit commit;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Commit {
                private String sha;
            }
        }


    }
}

