package com.GroceryManagementSales.DAO;
//import the required packages

import com.GroceryManagementSales.entity.SalesDetails;
import com.GroceryManagementSales.repository.SalesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * DAO class for managing SalesDetails entities.
 */
@Component
public class SalesDetailsDAO {
    
    // Repository for SalesDetails
    @Autowired
    private final SalesDetailsRepository salesDetailsRepository;

    /**
     * Constructor for SalesDetailsDAO.
     *
     * @param salesDetailsRepository the repository for sales details
     */
    public SalesDetailsDAO(SalesDetailsRepository salesDetailsRepository) {
        this.salesDetailsRepository = salesDetailsRepository;
    }

    /**
     * Retrieve all sales details.
     *
     * @return a list of all sales details
     */
    public List<SalesDetails> getAllSalesDetails() {
        return salesDetailsRepository.findAll();
    }

    /**
     * Retrieve sales details by its ID.
     *
     * @param salesDetailsId the ID of the sales details
     * @return the sales details with the given ID
     */
    public SalesDetails getSalesDetailsById(int salesDetailsId) {
        return salesDetailsRepository.findById(salesDetailsId).orElse(null);
    }

    /**
     * Create new sales details.
     *
     * @param salesDetails the sales details to be created
     * @return the created sales details
     */
    public SalesDetails createSalesDetails(SalesDetails salesDetails) {
        return salesDetailsRepository.save(salesDetails);
    }

    /**
     * Update existing sales details.
     *
     * @param salesDetailsId the ID of the sales details to be updated
     * @param salesDetails the updated sales details
     * @return the updated sales details
     */
    public SalesDetails updateSalesDetails(int salesDetailsId, SalesDetails salesDetails) {
        return salesDetailsRepository.save(salesDetails);
    }
    
    /**
     * Delete sales details by its ID.
     *
     * @param salesDetailsId the ID of the sales details to be deleted
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteSalesDetails(int salesDetailsId) {
        if (salesDetailsRepository.existsById(salesDetailsId)) {
            salesDetailsRepository.deleteById(salesDetailsId);
            return true;
        }
        return false;
    }
    
    /**
     * Retrieve sales details by sales ID.
     *
     * @param salesId the ID of the sales
     * @return a list of sales details for the given sales ID
     */
    public List<SalesDetails> getSalesDetailsBySalesId(int salesId) {
        return salesDetailsRepository.findBySalesId(salesId);
    }
}
