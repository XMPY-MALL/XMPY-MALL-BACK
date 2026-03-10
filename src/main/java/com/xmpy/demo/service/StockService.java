package com.xmpy.demo.service;

import com.xmpy.demo.dto.res.stock.StockResByProductId;
import com.xmpy.demo.entity.Stock;
import com.xmpy.demo.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;

    public StockResByProductId getStocks(long productId) {
        // dto가 entity들을 종합해서 변환되게 캡슐화했어요
        return StockResByProductId.from(stockMapper.findByProductId(productId));
    }

    public List<Stock> getStocksByProduct(long productId) {
        return stockMapper.findStocksByProductId(productId);
    }

    public void addStock(long productId, Long sizeId, Long colorId, int count) {
        if (sizeId == null || colorId == null) {
            throw new RuntimeException("사이즈/색상은 필수입니다.");
        }
        if (count < 0) {
            throw new RuntimeException("재고는 0 이상이어야 합니다.");
        }

        int exists = stockMapper.countByOption(productId, sizeId, colorId);
        if (exists > 0) {
            throw new RuntimeException("이미 존재하는 옵션입니다.");
        }

        int result = stockMapper.insertStock(productId, sizeId, colorId, count);
        if (result <= 0) {
            throw new RuntimeException("옵션 추가 실패");
        }
    }

    public void updateStock(long stockId, int count) {
        if (count < 0) {
            throw new RuntimeException("재고는 0 이상이어야 합니다.");
        }

        int result = stockMapper.updateStockCount(stockId, count);
        if (result <= 0) {
            throw new RuntimeException("수정할 옵션이 없습니다.");
        }
    }

    public void deleteStock(long stockId) {
        int result = stockMapper.deleteStock(stockId);
        if (result <= 0) {
            throw new RuntimeException("삭제할 옵션이 없습니다.");
        }
    }

    public List<Map<String, Object>> getSizes() {
        return stockMapper.findAllSizes();
    }

    public List<Map<String, Object>> getColors() {
        return stockMapper.findAllColors();
    }

    // 주문 확정 시 재고 차감
    @Transactional
    public void decreaseStock(long productId, long sizeId, long colorId, int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("수량은 1개 이상이어야 합니다.");
        }

        int updated = stockMapper.decreaseStock(productId, sizeId, colorId, quantity);
        if (updated <= 0) {
            throw new RuntimeException("재고가 부족하거나 해당 옵션이 없습니다.");
        }
    }

    // 주문 취소/실패 시 재고 복구
    @Transactional
    public void restoreStock(long productId, long sizeId, long colorId, int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("수량은 1개 이상이어야 합니다.");
        }

        int updated = stockMapper.restoreStock(productId, sizeId, colorId, quantity);
        if (updated <= 0) {
            throw new RuntimeException("복구할 옵션 재고가 없습니다.");
        }
    }
}