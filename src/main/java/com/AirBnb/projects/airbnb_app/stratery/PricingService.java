package com.AirBnb.projects.airbnb_app.stratery;

import com.AirBnb.projects.airbnb_app.entity.Inventory;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
