package entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long productId;
    private long categoryId;
    private long categoryDetailId;
    private String productName;
    private String description;
    private boolean isBest;
    private String imgUrl;
    private int price;
    private String quickRunDown;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


}
