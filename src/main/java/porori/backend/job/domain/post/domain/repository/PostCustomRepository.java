package porori.backend.job.domain.post.domain.repository;

import porori.backend.job.domain.post.application.dto.request.GetAllPostRequest;
import porori.backend.job.domain.post.application.dto.response.GetAllPostResponse;

public interface PostCustomRepository {
    GetAllPostResponse getAllPostResponse(GetAllPostRequest getAllPostRequest);
}
