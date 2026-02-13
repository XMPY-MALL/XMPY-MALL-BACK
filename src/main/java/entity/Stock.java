package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    private long stockId;
    private long productId;
    private long sizeId;
    private long colorId;
    private boolean isSoldOut;
    private LocalDateTime updateAt;
}
