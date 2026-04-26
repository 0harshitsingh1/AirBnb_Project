package com.AirBnb.projects.airbnb_app.stratery;

import com.AirBnb.projects.airbnb_app.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OccupancyPricingStratery implements PricingStratery{

    private final PricingStratery wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        double occupanyRate = (double) inventory.getBookedCount()/inventory.getTotalCount();
        if(occupanyRate > 0.8) {
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;
    }
}
