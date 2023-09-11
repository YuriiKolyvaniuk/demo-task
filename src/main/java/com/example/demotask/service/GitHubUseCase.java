package com.example.demotask.service;

import com.example.demotask.dto.GitHubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubUseCase {
    private final RestTemplate restTemplate;
    private final String GITHUB_API_URL = "https://api.github.com/users/%s/repos";

    public GitHubResponse getGitHubUserMetadata(String username) {
        String apiUrl = String.format(GITHUB_API_URL, username);
        GitHubResponse.GitHubRepository[] repositories = restTemplate.getForObject(apiUrl, GitHubResponse.GitHubRepository[].class);

        for (GitHubResponse.GitHubRepository repo : repositories) {
            String owner = repo.getOwner().getLogin();
            String repoName = repo.getName();
            String branchesUrl = String.format("https://api.github.com/repos/%s/%s/branches", owner, repoName);

            GitHubResponse.GitHubRepository.Branch[] branches = restTemplate.getForObject(branchesUrl, GitHubResponse.GitHubRepository.Branch[].class);
            repo.setBranches(Arrays.asList(branches));
        }

        return (new GitHubResponse(Arrays.asList(repositories)));
    }
}
