package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private long userId;
    private long roleId;
    private String email;
    private String userPassword;
    private String userName;
    private long userPhone;
    private String address;
    private LocalDateTime createAt;
}
