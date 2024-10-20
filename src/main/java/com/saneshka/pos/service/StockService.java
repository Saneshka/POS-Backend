package com.saneshka.pos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.Product;
import com.saneshka.pos.entity.Stock;

@Service
public interface StockService {

    List<Stock> getAllStocks();

    Stock createStock(Stock stock);

    Stock geStockById(Long id);

    Stock getStockByProduct(Product product);

    Stock updateStock(Long id, Stock stock);

    void deleteStock(Long id);
}
