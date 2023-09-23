package porori.backend.job.domain.post.application.service;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class CalculateWriteDateBefore {

    public String calculateWriteDateBefore(LocalDateTime createdDate) {
        ZonedDateTime seoulNow = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime now = seoulNow.toLocalDateTime();
        Duration duration = Duration.between(createdDate, now);

        if (duration.toDays() >= 1) {
            return duration.toDays() + "일 전";
        } else if (duration.toHours() >= 1) {
            return duration.toHours() + "시간 전";
        } else {
            return duration.toMinutes() + "분 전";
        }
    }

}
