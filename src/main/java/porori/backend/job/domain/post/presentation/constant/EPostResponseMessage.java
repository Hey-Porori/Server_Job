package porori.backend.job.domain.post.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EPostResponseMessage {
    CREATE_POST_SUCCESS("알바 게시글을 생성했습니다"),
    GET_ALL_POST_SUCCESS("알바 게시글 목록을 필터링 조회했습니다"),
    GET_ONE_POST_SUCCESS("알바 게시글 하나를 조회했습니다"),
    UPDATE_POST_SUCCESS("알바 게시글을 수정했습니다"),
    CLOSE_POST_SUCCESS("알바 게시글 모집을 종료했습니다");

    private final String message;
}
