package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Inquiry {
    private long inquiryId;
    private long userId;
    private long productId;
    private String title;
    private String content;
    private String status;
    private String answerContent;
    private LocalDateTime creatdAt;
    private LocalDateTime answeredAt;

}
