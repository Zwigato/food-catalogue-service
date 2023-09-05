package com.app.foodcatalogue.service;

import com.app.foodcatalogue.dto.FoodCataloguePage;
import com.app.foodcatalogue.dto.FoodItemDTO;
import com.app.foodcatalogue.dto.Restaurant;
import com.app.foodcatalogue.entity.FoodItem;
import com.app.foodcatalogue.mapper.FoodItemMapper;
import com.app.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem savedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        //food item list
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        //restaurantDetails from microservice call
          Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);

        return new FoodCataloguePage(foodItemList, restaurant);
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        System.out.println("Hello");
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/getRestaurant/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return  foodItemRepo.findByRestaurantId(restaurantId);
    }
}
