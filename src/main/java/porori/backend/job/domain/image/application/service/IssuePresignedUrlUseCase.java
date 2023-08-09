package porori.backend.job.domain.image.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.image.application.dto.request.IssuePresignedUrlRequest;
import porori.backend.job.domain.image.application.dto.response.IssuePresignedUrlResponse;
import porori.backend.job.global.config.s3.S3Service;

@Service
@RequiredArgsConstructor
public class IssuePresignedUrlUseCase {

    private final S3Service s3Service;

    public IssuePresignedUrlResponse execute(IssuePresignedUrlRequest issuePresignedUrlRequest) {

        return IssuePresignedUrlResponse.from(
                s3Service.issuePreSignedUrl(
                        issuePresignedUrlRequest.getImageFileExtension()));
    }
}
