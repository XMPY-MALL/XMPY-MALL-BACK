package com.xmpy.demo.dto.res.stock;

import com.xmpy.demo.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResByProductId {
    private List<OptionDto> colors;
    private List<OptionDto> sizes;

    public static StockResByProductId from(List<Stock> stocks) {
        List<OptionDto> colors = stocks.stream()
                .collect(Collectors.groupingBy(s -> s.getColor().getColorId()))
                .values().stream()
                .map(group -> {
                    Stock s = group.get(0);
                    boolean allSoldOut = group.stream().allMatch(Stock::isSoldOut);
                    return OptionDto.builder()
                            .id(s.getColor().getColorId())
                            .label(s.getColor().getColorName())
                            .soldOut(allSoldOut)
                            .build();
                })
                .collect(Collectors.toList());

        List<OptionDto> sizes = stocks.stream()
                .collect(Collectors.groupingBy(s -> s.getSize().getSizeId()))
                .values().stream()
                .map(group -> {
                    Stock s = group.get(0);
                    boolean allSoldOut = group.stream().allMatch(Stock::isSoldOut);
                    return OptionDto.builder()
                            .id(s.getSize().getSizeId())
                            .label(s.getSize().getSizeName())
                            .soldOut(allSoldOut)
                            .build();
                })
                .collect(Collectors.toList());

        return StockResByProductId.builder()
                .colors(colors)
                .sizes(sizes)
                .build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OptionDto {
        private long id;
        private String label;
        private boolean soldOut;
    }
}
