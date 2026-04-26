package com.AirBnb.projects.airbnb_app.stratery;

import com.AirBnb.projects.airbnb_app.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStratery {

    BigDecimal calculatePrice(Inventory inventory);
}
