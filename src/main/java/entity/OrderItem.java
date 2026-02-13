package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private long orderItemId;
    private long ordersId;
    private long productId;
    private long colorId;
    private long sizeId;
    private int quantity;
    private int price;
}
