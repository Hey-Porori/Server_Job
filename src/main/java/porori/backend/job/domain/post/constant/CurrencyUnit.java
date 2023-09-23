package porori.backend.job.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyUnit {
    WON("원"),
    TEN_THOUSAND_WON("만원");
    private String message;
}
