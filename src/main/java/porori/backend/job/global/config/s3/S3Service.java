package porori.backend.job.global.config.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.image.application.dto.ImageFileExtension;
import porori.backend.job.domain.image.application.dto.ImageUrlDto;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public ImageUrlDto issuePreSignedUrl(ImageFileExtension fileExtension) {

        String valueFileExtension = fileExtension.getUploadExtension();

        String fileName = createFileName(valueFileExtension);

        GeneratePresignedUrlRequest request = getGeneratePreSignedUrlRequest(bucket, fileName, valueFileExtension);
        String url = amazonS3.generatePresignedUrl(request).toString();

        return ImageUrlDto.of(url, fileName);
    }

    private String createFileName(String fileExtension) {
        return "jobs/" + UUID.randomUUID() + "." + fileExtension;
    }

    // 업로드용 Pre-Signed URL을 생성하기 때문에, PUT을 지정
    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(
            String bucket, String fileName, String fileExtension) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withKey(fileName)
                        .withContentType("image/" + fileExtension)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        // 유효 기간 설정 (5분)
        expTimeMillis += 1000 * 60 * 5;
        expiration.setTime(expTimeMillis);
        return expiration;
    }

}