package com.xmpy.demo.dto.req.auth;


import com.xmpy.demo.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupReqDto {
    // 회원가입 창 백엔드 구현..

    // validation은 간략하게 구현
    // jackson이 알아서 순서를 맞춰준다

    // 아이디 역할 = email
    @NotBlank(message = "올바른 이메일을 입력해주세요0")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    // 비밀번호
    @NotBlank(message = "패스워드를 입력해주세요")
    private String userPassword;

    // 첫번째 줄은 진짜 이름...
    @NotBlank(message = "이름을 입력해주세요")
    private String userName;

    @NotNull(message = "전화번호를 입력해주세요")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String userPhone;

    @NotBlank(message = "주소를 입력해주세요")
    private String address;
    // 카카오 우편번호 api로 배송지 넣기 - address가 필드3개
    // 장바구니에서 결재 눌렀을 때, 배송지 설정하기...
    // address를 따로 빼는 방안도 생각

    // dto -> entity 로 변환시킨다..
    public User toEntity(){
        return User.builder() // password는 차후에 set해준다
                .email(this.email)
                .userName(this.userName)
                .userPhone(this.userPhone)
                .address(this.address)
                .build();
    }
    // dto를 entity로 변환시키는 코드임
}
