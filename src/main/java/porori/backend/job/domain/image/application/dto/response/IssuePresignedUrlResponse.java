package porori.backend.job.domain.image.application.dto.response;


import lombok.Builder;
import lombok.Getter;
import porori.backend.job.domain.image.application.dto.ImageUrlDto;
import porori.backend.job.global.config.s3.ApiImageUrlUtil;

@Getter
@Builder
public class IssuePresignedUrlResponse {

    private final String presignedUrl;

    private final String imgUrl;

    public static IssuePresignedUrlResponse from(ImageUrlDto urlDto) {
        String imgUrl = ApiImageUrlUtil.prefix + urlDto.getKey();

        return IssuePresignedUrlResponse.builder()
                .presignedUrl(urlDto.getPresignedUrl())
                .imgUrl(imgUrl)
                .build();
    }
}
