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
    private boolean best;
    private String imgUrl;
    private int price;
    private String quickRundown;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
