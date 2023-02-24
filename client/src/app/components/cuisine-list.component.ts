import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})


export class CuisineListComponent implements OnInit {


  coordinatesArr: number[] = [];

  cuisineList: Restaurant[] = []

  constructor( private activatedRoute: ActivatedRoute, private svc: RestaurantService, private router: Router) { }


  ngOnInit(): void {
   
    this.svc.getCuisineList()
    .then(result =>  {
      this.cuisineList = result;
    })
    .catch(error =>
      {
        console.error('',error)
      })
  
  }

  // TODO Task 2
  // For View 1

  getCuisineList(){

    
   
  }
}



