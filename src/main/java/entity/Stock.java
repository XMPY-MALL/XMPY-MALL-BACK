package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private long stockId;
    private long productId;
    private long userId;
    private long sizeId;
    private long colorId;
    private LocalDateTime updateAt;
}
