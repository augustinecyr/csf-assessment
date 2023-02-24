import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant, Comment } from '../models';
import { RestaurantService } from '../restaurant-service';



@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {


  // TODO Task 4 and Task 5
  // For View 3


  constructor(private fbuild: FormBuilder, private svc: RestaurantService, private router: Router) { }

  commentForm!: FormGroup;

  ngOnInit(): void {

    this.commentForm = this.createForm();
  }
  createForm(): FormGroup<any> {
    let commentForm = this.fbuild.group({
      name: this.fbuild.control<string>('', [Validators.required]),
      rating:this.fbuild.control<number>(0, [Validators.required]),
      text:this.fbuild.control<string>('', Validators.required),
    });
    return commentForm;
  }

  sendForm(){

    let comment:Comment= {
      "name": this.commentForm.get("name")?.value,
      "rating": this.commentForm.get("rating")?.value,
      "text": this.commentForm.get("text")?.value,
      restaurantId: ''
    }
    console.info("Comments:", comment);

    this.svc.postComment(comment);
    
  }

  home() {
    this.router.navigate(['/']);
  }

}
