package vttp2022.csf.assessment.server.controllers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class CuisineRestController {

    @Autowired
    private RestaurantService restSvc;

    @GetMapping(path = "/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCuisine() {
        List<Restaurant> results = restSvc.getCuisines();
        JsonObject result = null;
        // Build the result
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
        Restaurant rs = new Restaurant();
        rs.setRestaurantId(results.toString());
        rs.setName(results.toString());
        rs.setCoordinates(results.toString());
        rs.setCuisine(results.toString());
        rs.setAddress(results.toString());
        rs.setMapURL(results.toString());

        objBuilder.add("restaurants", rs.toJson());
        result = objBuilder.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping(path = "/{cuisine}/restaurants")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public ResponseEntity<String> getAllRestaurantsByCuisine(@PathVariable String cuisine) {
        
        String jsonStringRestaurants = restSvc.getRestaurantsByCuisine(cuisine);
        /* 
        List<Restaurant> lRestaurants = restSvc.getRestaurantsByCuisine(cuisine);
        if (lRestaurants.isEmpty()) {
            JsonObject resp = Json.createObjectBuilder().add("message", "No such cuisines: " + cuisine).build();
            return ResponseEntity.ok(resp.toString());
        }
        List<JsonObject> lRestaurantJson = new ArrayList<>();
        lRestaurants.forEach(summary -> lRestaurantJson.add(summary.toJson()));
        return ResponseEntity.ok(lRestaurantJson.toString());
        */
        return ResponseEntity.ok(jsonStringRestaurants);
    }


    @PostMapping(value="/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postComment(@RequestBody String payload){
        
        JsonReader jsonReader = Json.createReader(new StringReader(payload));
        JsonObject jsonObject = jsonReader.readObject();

        Comment comment = new Comment();

        comment.setName(jsonObject.getString("name"));
        comment.setRating(jsonObject.getInt("rating"));
        comment.setText(jsonObject.getString("text"));
        
        restSvc.addComment(comment);

        return ResponseEntity.ok("" + comment);


    }
    }
    

