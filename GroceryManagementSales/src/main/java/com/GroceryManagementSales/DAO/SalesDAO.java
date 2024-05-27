package com.GroceryManagementSales.DAO;

import com.GroceryManagementSales.entity.Sales;
import com.GroceryManagementSales.repository.SalesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesDAO {

    @Autowired
    private final SalesRepository salesRepository;

    public SalesDAO(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSalesById(int salesId) {
        return salesRepository.findById(salesId).orElse(null);
    }

    public Sales createSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public Sales updateSales(int salesId, Sales sales) {
        if (salesRepository.existsById(salesId)) {
            return salesRepository.save(sales);
        } else {
            throw new RuntimeException("Sales with ID " + salesId + " not found.");
        }
    }

    public boolean deleteSales(int salesId) {
        if (salesRepository.existsById(salesId)) {
            salesRepository.deleteById(salesId);
            return true;
        } else {
            return false;
        }
    }
}
