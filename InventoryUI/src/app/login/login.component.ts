import { Component, OnInit } from '@angular/core';
import {LoginService} from './login.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers : [LoginService]
})
export class LoginComponent implements OnInit {
  loggedIn: boolean;
  username : string;
  password : string;
  constructor(private loginService : LoginService) { }

  ngOnInit() {
  }

  authenticate(){
    this.loginService.isAuthenticate(this.username,this.password)
                           .subscribe(
                               loggedIn => this.loggedIn = loggedIn, //Bind to view
                                err => {
                                    // Log errors if any
                                    console.log(err);
                                });
  }

}
