package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private long reviewId;
    private long productId;
    private long orderItemId;
    private long userId;
    private int rating;
    private String content;
    private LocalDateTime createdAt;

}
