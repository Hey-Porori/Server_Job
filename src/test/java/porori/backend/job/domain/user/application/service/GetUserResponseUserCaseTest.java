//package porori.backend.job.domain.user.application.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.reactive.function.client.WebClient;
//import porori.backend.job.domain.user.application.dto.response.UserResponse;
//import porori.backend.job.global.dto.ResponseDto;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//
//@SpringBootTest
//public class GetUserResponseUserCaseTest {
//
//    @Autowired
//    private GetUserResponseUserCase getUserResponseUserCase;
//
//    @Autowired
//    private WebClient webClient;
//
//    @Test
//    public void testGetUserDataSuccess() {
//        //given
//        //Token 받아오기
//        Map<String, String> requestBody = Map.of("appleId", "test001");
//        JsonNode jsonNode = webClient.post()
//                .uri("/test/login")
//                .bodyValue(requestBody)
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, response ->
//                        Mono.error(new RuntimeException("Client error: " + response.statusCode())))
//                .onStatus(HttpStatus::is5xxServerError, response ->
//                        Mono.error(new RuntimeException("Server error: " + response.statusCode())))
//                .bodyToMono(JsonNode.class)  // JSON을 JsonNode로 변환
//                .block();
//
//        String Token = jsonNode.path("data").path("accessToken").asText();
//
//        // UserData 가져오기
//        UserResponse userResponse=  webClient.get()
//                .uri("/token/me")
//                .header("Authorization", "Bearer " + Token)
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, response ->
//                        Mono.error(new RuntimeException("Client error: " + response.statusCode())))
//                .onStatus(HttpStatus::is5xxServerError, response ->
//                        Mono.error(new RuntimeException("Server error: " + response.statusCode())))
//                .bodyToMono(new ParameterizedTypeReference<ResponseDto<UserResponse>>() {})
//                .map(ResponseDto::getData)
//                .block();
//
//        //when
//        UserResponse userResult=getUserResponseUserCase.getUserData(Token);
//
//        //then
//        Assertions.assertThat(userResponse.getUserId()).isEqualTo(userResult.getUserId());
//    }
//}
//
//
//
//
