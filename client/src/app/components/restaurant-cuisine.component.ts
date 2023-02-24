import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit {
	
	// TODO Task 3
	// For View 2

  cuisine = ''

  restaurantList: Restaurant[] = [];
 

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private svc: RestaurantService) {}


  ngOnInit(): void {
      this.showCuisine(this.activatedRoute.snapshot.params['cuisine'])
    }

    
    showCuisine(cuisine: string) {
      this.svc.getRestaurantsByCuisine(cuisine).subscribe((data: any) => {
        console.info(data);
  
        this.restaurantList = data as any;
        console.info('All Orders from Mongo:', this.restaurantList);
        return this.restaurantList;
      });
    }
  
    backtoView1() {
      this.router.navigate(['/cuisines']);
    }
  }




