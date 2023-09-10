package com.example.demotask.controllers;

import com.example.demotask.dto.GitHubResponse;
import com.example.demotask.service.GitHubUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class TaskController {
    private final GitHubUseCase gitHubUseCase;

    @GetMapping(value = "/repositories/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GitHubResponse getGitHubRepositories(@PathVariable String username) {
        return gitHubUseCase.getGitHubUserMetadata(username);
    }
}
