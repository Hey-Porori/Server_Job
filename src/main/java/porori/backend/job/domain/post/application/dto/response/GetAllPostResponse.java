package porori.backend.job.domain.post.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllPostResponse {
    private Integer numOfPost; //게시글 개수
    private List<PostBlocks> posts;
}
