package com.app.foodcatalogue.dto;

import com.app.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {

    private List<FoodItem> foodItemsList;


    //This restaurant will be fetched from RestaurantListing Microservice
    private Restaurant restaurant;

}
