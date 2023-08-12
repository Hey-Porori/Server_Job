package porori.backend.job.domain.user.application.dto.response;

import lombok.Getter;

@Getter
public class UserResponse {
    private Long userId;
    private String address;
    private String backgroundColor;
    private String email;
    private boolean gender;
    private String imageUrl;
    private String name;
    private String nickName;
    private String phoneNumber;

}
