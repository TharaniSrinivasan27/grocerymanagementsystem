package com.GroceryManagementSales.repository;
//import the required packages 
import com.GroceryManagementProduct.entity.Product;
import com.GroceryManagementSales.entity.Sales;
import com.GroceryManagementSales.entity.SalesDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing SalesDetails entities.
 */
@Repository
public interface SalesDetailsRepository extends JpaRepository<SalesDetails, Integer> {

    /**
     * Custom query method to find SalesDetails by Sales and Product.
     *
     * @param sales the Sales entity
     * @param product the Product entity
     * @return the SalesDetails entity matching the given Sales and Product
     */
    @Query("SELECT sd FROM SalesDetails sd WHERE sd.sales = :sales AND sd.product = :product")
    SalesDetails findBySalesAndProduct(@Param("sales") Sales sales, @Param("product") Product product);

    /**
     * Custom query method to find a list of SalesDetails by Sales ID.
     *
     * @param salesId the ID of the Sales
     * @return a list of SalesDetails entities matching the given Sales ID
     */
    @Query("SELECT sd FROM SalesDetails sd WHERE sd.sales.salesId = :salesId")
    List<SalesDetails> findBySalesId(@Param("salesId") int salesId);
}
