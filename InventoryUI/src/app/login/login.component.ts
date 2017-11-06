import { Component, OnInit } from '@angular/core';
import {LoginService} from './login.service';
import { Router }      from '@angular/router';
import { AuthService } from './../auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loggedIn: boolean;
  username : string;
  password : string;
  constructor(private loginService : LoginService, private router : Router, private authService : AuthService) { }

  ngOnInit() {
  }

  authenticate(){
   this.loginService.isAuthenticate(this.username,this.password)
                         .subscribe(
                               loggedIn =>{
                                 this.loggedIn =loggedIn; //loggedIn;
                                 this.login(this.loggedIn);
                             },
                            err => {
                                    // Log errors if any
                               console.log(err);
                              });
  }
  login(isLoggedIn){
    this.authService.login().subscribe(() => {
      if (isLoggedIn) {
        let redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/features/inventoryList';
        this.router.navigate([redirect]);
      }
    });
  }

}
