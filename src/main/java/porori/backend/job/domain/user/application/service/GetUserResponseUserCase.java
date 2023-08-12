package porori.backend.job.domain.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import porori.backend.job.domain.user.application.dto.response.UserResponse;
import porori.backend.job.global.dto.ResponseDto;
import porori.backend.job.global.exception.InternalServerErrorException;
import porori.backend.job.global.exception.TokenInvalidException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetUserResponseUserCase {
    private final WebClient webClient;

    public UserResponse getUserData(String token) {
        return  webClient.get()
                .uri("/token/me")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response ->
                        Mono.error(new TokenInvalidException()))
                .onStatus(HttpStatus::is5xxServerError, response ->
                        Mono.error(new InternalServerErrorException("User Internal Server Error")))
                .bodyToMono(new ParameterizedTypeReference<ResponseDto<UserResponse>>() {})
                .map(ResponseDto::getData)
                .block();
    }


}
