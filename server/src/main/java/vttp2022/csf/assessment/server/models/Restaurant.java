package vttp2022.csf.assessment.server.models;


import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// Do not modify this class
public class Restaurant {
	
	private String restaurantId;
	private String name;
	private String cuisine;
	private String address;
	private LatLng coordinates;
	private String mapUrl;

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantId() {
		return this.restaurantId;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCuisine() {
		return this.cuisine;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}

	public void setCoordinates(LatLng coordinates) {
		this.coordinates = coordinates;
	}
	public LatLng getCoordinates() {
		return this.coordinates;
	}

	public void setMapURL(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getMapURL() {
		return this.mapUrl;
	}

	public JsonObject toJson(){
		return Json.createObjectBuilder()
		.add("restaurant_id", restaurantId)
		.add("name", name)
		.add("cuisine", cuisine)
		.add("address", address)
		.add("coordinates", coordinates.toString())
		.add("mapUrl", mapUrl)
		.build();
	}
	

	public static Restaurant create (Document d) {
		Restaurant r = new Restaurant();

		r.setRestaurantId(d.getString("restaurant_id"));
		r.setName(d.getString("name"));
		r.setCuisine(d.getString("cuisine"));
		r.setAddress(d.getString("address"));
		r.setCoordinates(d.getString("coordinates"));
		r.setMapURL(d.getString("map"));
		return r;
	}
	public void setCoordinates(String coordinates) {

	
		return;
	}
	

}
