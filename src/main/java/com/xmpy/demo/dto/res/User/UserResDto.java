package com.xmpy.demo.dto.res.User;

import lombok.Builder;
import lombok.Data;

// UserResDto는
// 서버에서 프론트로 데이터를 보낼때, 사용하는 dto
@Data
@Builder
public class UserResDto {
    private long userId;
    private String email;
    private String userName;
    // userPassword만 빼고 다 들고 온다
    private String userPhone;
    private String address;

    private long roleId;
    // role을 뭘 들고와야 하는건지를 모르겠다
    // 강사님처럼 roleName을 들고와야 하는 건지
    // 아니면, roleId를 들고와야 하는 건지를 모르겠다..
    // 우리는 따로 updateAt없음..
    // Dto는 끝임 이제..
}
