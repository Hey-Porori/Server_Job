package porori.backend.job.global.config.s3;

import org.springframework.beans.factory.annotation.Value;
import porori.backend.job.global.annotation.Util;

@Util
public class ImageUrlUtil {
    public static String prefix;

    @Value("${image.prefix}")
    public void setPrefix(String value) {
        prefix = value;
    }
}
