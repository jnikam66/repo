import { Component, OnInit } from '@angular/core';
import {RegisterService} from './register.service'
import {Response} from "@angular/http";
import {Router} from "@angular/router"

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers : [RegisterService]
})
export class RegisterComponent implements OnInit {

  constructor(private registerService: RegisterService,private router :Router) { }

  redirectToLogin:boolean;
  firstname: string;
  lastname : string;
  username: string;
  password:string;
  email:string;
  company:string;
  country:string;
  contactNumber:string;

  ngOnInit() {
  }

  registerUser(){
    let user = {
        firstname: this.firstname,
        lastname: this.lastname,
        username: this.username,
        password: this.password,
        email: this.email,
        company: this.company,
        country: this.country,
        contactno: this.contactNumber
      }
    this.registerService.registerUser(user).then((data: Response) => {
      this.router.navigate(['login'])
    }).catch((err) => {
      console.log("loadData Error", err);
    });
  }

}
