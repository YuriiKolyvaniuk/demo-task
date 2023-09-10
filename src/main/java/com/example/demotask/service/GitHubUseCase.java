package com.example.demotask.service;

import com.example.demotask.dto.GitHubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubUseCase {
    private final RestTemplate restTemplate;
    private final String GITHUB_API_URL = "https://api.github.com/users/%s/repos";

    public GitHubResponse getGitHubUserMetadata(String username) {
        String apiUrl = String.format(GITHUB_API_URL, username);
        GitHubResponse.GitHubRepository[] repositories = restTemplate.getForObject(apiUrl, GitHubResponse.GitHubRepository[].class);
        GitHubResponse response = new GitHubResponse(List.of(repositories));
        return (response);
    }
}
