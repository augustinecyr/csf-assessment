package vttp2022.csf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.repositories.MapCache;
import vttp2022.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Autowired
	private MapCache mapCache;

	// TODO Task 2
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type
	// DO NOT CHNAGE THE METHOD'S NAME

	public List<Restaurant> getCuisines() {
		// Implmementation in here
		// Find the post
		return (List<Restaurant>) restaurantRepo.getCuisines();
	}

	// TODO Task 3
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type
	// DO NOT CHNAGE THE METHOD'S NAME
	public String getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here

		List<Restaurant> lRestaurants = restaurantRepo.getRestaurantsByCuisine(cuisine);

		JsonArrayBuilder arrBuild = Json.createArrayBuilder();
		lRestaurants.stream()
				.forEach(i -> {
					arrBuild.add(i.toJson());
				});
		JsonArray jsonArrayRestaurants = arrBuild.build();

		String jsonStringRestaurants = jsonArrayRestaurants.toString();

		return jsonStringRestaurants;

	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any)
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public Optional<Restaurant> getRestaurant() {
		// Implmementation in here
		Optional<Restaurant> opt = restaurantRepo.getRestaurant();
		if (opt.isEmpty())
		return Optional.empty();

		Restaurant restaurant = opt.get();
		restaurant.setName(restaurant.getName());
		restaurant.setAddress(restaurant.getAddress());
		restaurant.setCoordinates(restaurant.getCoordinates());
		restaurant.setCuisine(restaurant.getCuisine());
		restaurant.setMapURL(restaurant.getMapURL());
		restaurant.setRestaurantId(restaurant.getRestaurantId());

		return Optional.of(restaurant);

	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public String addComment(Comment comment) {
		// Implmementation in here
		Comment commentOutcome = restaurantRepo.addComment(comment);

		JsonObject jo = commentOutcome.toJson();

		return jo.toString();

	}
	//
	// You may add other methods to this class
}
