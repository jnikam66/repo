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
  private editModel = {
    firstname:'',
    lastname:'',
    company:'',
    country:'',
    location:'',
    email:'',
    contactno:'',
    username : ''
  };

  constructor(private adminService : AdminService, private loginService: LoginService) { }

  ngOnInit() {
    this.getUserDetails();
  }

  getUserDetails(){
    this.adminService.getUserDetails(this.loginService.username).subscribe(data =>  this.users = data);
  }

    onSubmit(){
    this.adminService.updateUser(this.editModel,this.loginService.username).then((data: Response) => {
        this.getUserDetails();
    }).catch((err) => {
            console.log("loadData Error", err);
    });
    console.log("Saving");

  }

  updateRecord(row){
    this.editModel.firstname = row.firstname;
    this.editModel.lastname = row.lastname;
    this.editModel.company = row.company;
    this.editModel.country = row.country;
    this.editModel.location = row.location;
    this.editModel.email = row.email;
    this.editModel.contactno = row.contactno;
    this.editModel.username = row.username;
  }
}
