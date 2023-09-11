package com.example.demotask;

import com.example.demotask.dto.GitHubResponse;
import com.example.demotask.usecase.GitHubUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GitHubUseCaseTest {

    @MockBean
    private RestTemplate restTemplate;

    private final String GITHUB_API_URL = "https://api.github.com/users/%s/repos";


    @Test
    public void testGetGitHubUserMetadata() {
        String username = "YuriiKolyvaniuk";
        String apiUrl = String.format(GITHUB_API_URL, username);
        GitHubResponse.GitHubRepository[] repositories = new GitHubResponse.GitHubRepository[0];
        ResponseEntity<GitHubResponse.GitHubRepository[]> responseEntity = new ResponseEntity<>(repositories, HttpStatus.OK);

        when(restTemplate.getForObject(apiUrl, GitHubResponse.GitHubRepository[].class)).thenReturn(responseEntity.getBody());

        GitHubUseCase useCase = new GitHubUseCase(restTemplate);
        GitHubResponse response = useCase.getGitHubUserMetadata(username);

        assertEquals(0, response.getData().size());
    }

    @Test
    public void testGetGitHubUserMetadataWhenHttpClientErrorExceptionThrown() {
        String username = "YuriiKolyvaniuk";
        String apiUrl = String.format(GITHUB_API_URL, username);
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_FOUND);

        when(restTemplate.getForObject(apiUrl, GitHubResponse.GitHubRepository[].class)).thenThrow(exception);

        GitHubUseCase useCase = new GitHubUseCase(restTemplate);
        try {
            useCase.getGitHubUserMetadata(username);
        } catch (HttpClientErrorException e) {
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }
}