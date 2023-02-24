import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { BehaviorSubject, firstValueFrom, lastValueFrom, map, take } from 'rxjs';
import { Restaurant, Comment } from './models'

@Injectable()
export class RestaurantService {
	onCuisineSearch: BehaviorSubject<Restaurant | null> = new BehaviorSubject<Restaurant | null>(null);

	// Make the BehaviorSubject observable to other components
	onCuisineSearch$ = this.onCuisineSearch.asObservable();

	constructor(private http: HttpClient) { }

	// TODO Task 2 
	// Use the following method to get a list of cuisines
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getCuisineList() : Promise<Restaurant[]> {
		// Implememntation in here
		const headers = new HttpHeaders()
			.set('Content-Type', 'application/json')
			.set('Accept', 'application/json')
		return firstValueFrom(
			this.http.get<Restaurant[]>('http://localhost:8080/api/cuisines', { headers })
		)
	}

	// TODO Task 3 
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public getRestaurantsByCuisine(cuisine: string) {
		// Implememntation in here
		/*
		return firstValueFrom<Restaurant>(
			this.http.get<any>('http://:localhost:8080/api/${cuisine}/restaurants')
				.pipe(
					take(1),
					map(r => {
						return {
							restaurantId: r.restaurantId,
							name: r.name,
							cuisine: r.cuisine,
							address: r.address,
							coordinates: r.coordinates
						} as Restaurant
					})
				)
		)
			.then((restaurantObj) => {
				console.log(restaurantObj);
				this.onCuisineSearch.next(restaurantObj) 

				return restaurantObj;
			})
			*/

		const headers = new HttpHeaders()
			.set('Content-Type', 'application/json')
			.set('Accept', 'application/json')
		return (
			this.http.get<String>('http://localhost:8080/api' + "/" + cuisine + "/restaurants", { headers })
		)

	}



	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	/*
	public getRestaurant(): Promise<Restaurant> {
		// Implememntation in here
		
	}
	*/

	// TODO Task 5
	// Use this method to submit a comment
	// DO NOT CHANGE THE METHOD'S NAME OR SIGNATURE
	
	public postComment(comment: Comment): Promise<any> {
		// Implememntation in here
		const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')

    return lastValueFrom(
      this.http.post<String>('http://localhost:8080/api/comments', comment, { headers })
    )
	}
	
}
