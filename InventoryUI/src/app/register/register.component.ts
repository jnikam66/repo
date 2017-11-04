import { Component, OnInit } from '@angular/core';
import {RegisterService} from './register.service'
import {Response} from "@angular/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers : [RegisterService]
})
export class RegisterComponent implements OnInit {

  constructor(private registerService: RegisterService) { }

  firstName: string;
  lastName : string;
  username: string;
  password:string;
  email:string;
  company:string;
  country:string;
  contactNo:string;

  ngOnInit() {
  }

  registerUser(){
    let user = {
        //firstName: this.firstName,
        //lastName: this.lastName,
        username: this.username,
        password: this.password,
        email: this.email,
        company: this.company,
        country: this.country,
        contactno: this.contactNo
      }
    this.registerService.registerUser(user).then((data: Response) => {
        data;
    }).catch((err) => {
            console.log("loadData Error", err);
        });
  }

}
