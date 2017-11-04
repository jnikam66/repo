import { Component, OnInit } from '@angular/core';
import {RegisterService} from './register.service'
import {Response} from "@angular/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private registerService: RegisterService) { }
private user:{
  firstName: null,
  lastName : null,
  username: null,
  password:null ,
  email:null,
  company:null,
  country:null,
  contactNo:null
}
  ngOnInit() {
  }

  registerUser(){
    this.registerService.registerUser(this.user).then((data: Response) => {
        data;
    }).catch((err) => {
            console.log("loadData Error", err);
        });
  }

}
