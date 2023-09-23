package porori.backend.job.domain.post.presentation;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porori.backend.job.domain.post.application.dto.request.CreatePostRequest;
import porori.backend.job.domain.post.application.dto.request.GetAllPostRequest;
import porori.backend.job.domain.post.application.dto.response.GetAllPostResponse;
import porori.backend.job.domain.post.application.dto.response.GetPostResponse;
import porori.backend.job.domain.post.application.service.CreatePostUserCase;
import porori.backend.job.domain.post.application.service.GetPostUserCase;
import porori.backend.job.global.dto.ResponseDto;

import javax.validation.Valid;

import static porori.backend.job.domain.post.presentation.constant.EPostResponseMessage.*;

@RestController
@RequestMapping("/api/jobs/post")
@RequiredArgsConstructor
public class PostController {
    private final CreatePostUserCase createPostUserCase;
    private final GetPostUserCase getPostUserCase;

    @ApiOperation(value = "알바 게시글 작성", notes = "알바 게시글을 작성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@RequestHeader("Authorization") String token, @Valid @RequestBody CreatePostRequest createPostRequest) {
        this.createPostUserCase.createPost(token, createPostRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),
                CREATE_POST_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "알바 게시글 상세 조회", notes = "알바 게시글을 상세 조회합니다.")
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto<GetPostResponse>> getPost(@RequestHeader("Authorization") String token, @PathVariable Long postId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_ONE_POST_SUCCESS.getMessage(), this.getPostUserCase.getPost(token, postId)));
    }

    @ApiOperation(value = "알바 게시글 필터 조회", notes = "알바 게시글을 필터로 조회합니다.")
    @PostMapping("/search")
    public ResponseEntity<ResponseDto<GetAllPostResponse>> getAllPost(@RequestHeader("Authorization") String token,
                                                                      @Valid @RequestBody GetAllPostRequest getAllPostRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_ALL_POST_SUCCESS.getMessage(), this.getPostUserCase.getAllPost(token, getAllPostRequest)));
    }

    //TODO: 알바 지원하기
    //TODO: 알바 게시글 수정하기
    //TODO: 알바 게시글 종료하기
    //TODO: 내가 작성한 게시글 조회하기

    //TODO: 게시글의 지원서 전체 보기
    //TODO: 내 전체 지원서 전체 보기
    //TODO: 이력서 상세 조회
    //TODO: 이력서 작성하기
    //TODO: 이력서 수정하기
    //TODO: 내 프로필 회원정보 조회하기

    //TODO: 내 지원내역 전체 조회하기
}
