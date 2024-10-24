package com.saneshka.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.Product;
import com.saneshka.pos.entity.Stock;
import com.saneshka.pos.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock geStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Stock existingStock = stockRepository.findById(id).orElse(null);

        if (existingStock == null) {
            return null;
        } else {
            existingStock.setQty(stock.getQty());
            existingStock.setProduct(stock.getProduct());

            return stockRepository.save(existingStock);
        }
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public Stock getStockByProduct(Product product) {
        return stockRepository.findByProduct(product).orElse(null);
    }

}
