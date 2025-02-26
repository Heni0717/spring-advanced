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
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]){8,}$",
            message = "새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함해야 합니다.")
    private String newPassword;
}