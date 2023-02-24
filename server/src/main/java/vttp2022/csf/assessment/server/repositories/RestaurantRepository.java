package vttp2022.csf.assessment.server.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;

@Repository
public class RestaurantRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	//
	public List<Restaurant> getCuisines() {
		// Implmementation in here
		
		 Query query = new Query();
		  List<Restaurant> listCuisines = mongoTemplate.find(query, Document.class,
		  "restaurants")
		  .stream()
		 .map(d -> Restaurant.create(d))
		  .toList();
		  return listCuisines;
		 
	}

	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	//
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here
		Query query = new Query(Criteria.where("cuisine").is(cuisine));
		List<Restaurant> lRestaurants = mongoTemplate.find(query, Restaurant.class, "restaurants");
		return lRestaurants;
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any)
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//
	public Optional<Restaurant> getRestaurant() {
		// Implmementation in here

		Query query = new Query();
		Document result = mongoTemplate.findOne(query, Document.class, "restaurants");
		if (null == result)
			return Optional.empty();

		return Optional.of(Restaurant.create(result));
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//
	public Comment addComment(Comment comment) {
		// Implmementation in here
		return mongoTemplate.insert(comment, "comments");
	}

	// You may add other methods to this class

}
