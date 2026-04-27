package com.AirBnb.projects.airbnb_app.stratery;

import com.AirBnb.projects.airbnb_app.entity.Inventory;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PricingService {

    public BigDecimal calculateDynamicPricing(Inventory inventory){
        PricingStratery pricingStratery = new BasePricingStratery();

        //Apply the addition  strategy
        pricingStratery = new SurgePricingStratery(pricingStratery);
        pricingStratery = new OccupancyPricingStratery(pricingStratery);
        pricingStratery = new UrgencyPricingStratery(pricingStratery);
        pricingStratery = new HolidayPricingStratery(pricingStratery);

        return  pricingStratery.calculatePrice(inventory);
    }

//    Return the sum of price of this inventory list
    public BigDecimal calculateTotalPrice(List<Inventory> inventoryList) {
        return inventoryList.stream()
                .map(this::calculateDynamicPricing)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

