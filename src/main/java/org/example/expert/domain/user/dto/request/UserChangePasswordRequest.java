package org.example.expert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank
    private String oldPassword;

    // 1-3: service의 if문 로직을 validation의 패턴으로 간단하게 dto에서 검증
    // UserController에서 이 dto를 request로 사용할때 @Valid 어노테이션 추가
    /*
    이런 방법으로도 사용할 수 있다!
    분리했을때의 이점: 수정(유지보수)하기에 더 용이, 에러 메시지를 어떤 유효성 검사에 걸리는지 더 상세히 표현 가능
    단점(?): 코드가 길다!

    @Size(min=8, message = "8자 이상으로 입력하세요.")
    @Pattern(regexp = ".*\\d.*", message = "숫자를 포함하세요.")
    @Pattern(regexp = ".*[A-Z].*", message = "대문자를 포함하세요.")
     */
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]){8,}$",
            message = "새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함해야 합니다.")
    private String newPassword;
}