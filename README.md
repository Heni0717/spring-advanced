# SPRING ADVANCED
Spring 심화 주차 개인 과제

---
### Lv1. 코드 개선
- 1-1 Early Return
  - `org.example.expert.domain.auth.service` - `AuthService`
  - 이메일 중복 체크를 signup() 메소드의 가장 앞으로 이동해 해당 에러 발생 시 이후의 불필요한 동작이 발생하지 않도록 함
- 1-2 불필요한 if-else 피하기
  - `org.exmple.expert.client` - `WeatherClient`
  - getTodayWeather()의 if-else문을 두개의 if문으로 분리
  - 분리후 먼저 정의된 if문에서 에러 발생 → 이후 로직이 실행되지 않음 → 아래의 if문에서 사용하기 위해 선언한 변수 위치 적절한 위치로 변경
- 1-3 Validation
  - `org.example.expert.domain.user.service` - `UserService`
  - changePassword()의 비밀번호 입력 조건을 `UserChangePasswordRequest` dto에 Validation Annotation으로 검증
    - 조건 : 새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함
    - `@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]){8,}$",
      message = "새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함해야 합니다.")`
  - `UserController`에 `@Valid`추가

### Lv2. N+1 문제
- `TodoRepository`
- 모든 Todo를 조회할 때 발생하는 N+1 문제를 JPQL fetch join 으로 해셜하고 있음
- 이를 `@EntityGraph` 사용 방식으로 수정
  - `@EntityGraph(attributePaths = {"user_id"})`

### Lv3. 테스트코드 연습 `@Test`
- 3-1
  - `org.example.expert.config` - `PassEncoderTest`
  - matches_메서드가_정상적으로_동작한다() 에서 passwordEncoder의 matches 메소드가 정상적으로 동작하지 않음을 확인
  - 매개값의 순서 변경 `encodedPassword`↔`rawPassword`
- 3-2
  - `org.example.expert.domain.manager.service` - `ManagerServiceTest` - manager_목록_조회_시_Todo가_없다면_NPE_에러를_던진다()
    - 던지는 에러가 NPE가 아닌 IRE이므로 메소드명 수정
    - manager가 아닌 todo가 없음을 테스트하는 것이므로 expected 메시지 또한 Todo로 수정
  - `org.example.expert.domain.comment.service` - `CommentServiceTest` - comment_등록_중_할일을_찾지_못해_에러가_발생한다()
    - when 단계에서 `ServerException` → `InvalidRequestException` 으로 변경
  - `org.example.expert.domain.manager.service` - `ManagerServiceTest` - todo의_user가_null인_경우_예외가_발생한다()
    - ManagerService에 todo의 user가 null인 경우를 검증하는 로직이 없음
    - todo.getUser()가 null인 경우 추가

---
