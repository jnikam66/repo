import { Component, OnInit } from '@angular/core';
import { AdminService } from './admin.service';
import { FormGroup, FormControl} from '@angular/forms'
import {LoginService} from  './../../login/login.service';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers : [AdminService]
})
export class AdminComponent implements OnInit {

  users= [{
    username:'',
    accesslevel:'',
    company:''
  }]

  constructor(private adminService : AdminService, private loginService: LoginService) { }

  ngOnInit() {
    this.adminService.getUserDetails(this.loginService.username).subscribe(data =>  this.users = data);
  }

  onSubmit(){
    console.log('hello');
  }
}
