package porori.backend.job.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocationDistance {
    LEVEL_1("2km 이하"),
    LEVEL_2("4km 이하"),
    LEVEL_3("6km 이하");
    private final String message;
}
