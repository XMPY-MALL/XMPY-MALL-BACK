package entity;

import lombok.*;

@Getter
@Setter
public class Product {
    private long productId;
    private String productName;
    private long sizeId;
    private long colorId;
}
