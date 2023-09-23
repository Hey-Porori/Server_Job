package porori.backend.job.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {

    WAITING("서빙"),
    KITCHEN_ASSISTANT("주방보조/설거지"),
    CHEF("주방장/조리사"),
    STORE_MANAGEMENT("매장관리/판매"),
    BEVERAGE_MAKING("음료 제조"),
    BAKING("베이킹"),
    CONVENIENCE_STORE("편의점"),
    TUTORING("과외/학원"),
    FLYER_DISTRIBUTION("전단지 배포"),
    CLEANING("청소/미화"),
    HOUSEHOLD("가사/집정리"),
    CAREGIVING("돌봄/시팅"),
    SCHOOL_PICKUP("등하원도우미"),
    ERRANDS("심부름/소일거리"),
    OTHERS("기타");

    private final String message;
}
