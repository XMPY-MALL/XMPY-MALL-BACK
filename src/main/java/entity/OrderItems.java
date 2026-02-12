package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    private long orderItemId;
    private long ordersId;
    private long ProductId;
    private long colorId;
    private long sizeId;
    private int quantity;
    private int price;
}
