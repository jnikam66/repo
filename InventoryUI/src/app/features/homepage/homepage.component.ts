import { Component, OnInit } from '@angular/core';
import { HomepageService } from './homepage.service';
import {LoginService } from './../../login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  providers:[HomepageService]
})
export class HomepageComponent implements OnInit {

  private inventoryList: Array<any> = [{
    company:'',
    country:'',
    createdby:'',
    inventoryid:'',
    inventoryname:'',
    lastupdatedby:'',
    location:'',
    numberofitems:''
  }];
  location: string;
  country: string;
  inventoryName: string;
  company: string;
username: string;
  constructor(private homepageService : HomepageService,private loginService : LoginService, private router : Router) {
    }


  ngOnInit() {
    this.homepageService.getInventoryDetails(this.loginService.username).subscribe(
          data =>{
            this.inventoryList = data; //loggedIn;
        },
       err => {
               // Log errors if any
          console.log(err);
         });
  }
  public saveNewInventory(){
    let model = {
      location: this.location,
      country: this.country,
      inventoryname: this.inventoryName,
      company: this.company,
      createdby: this.loginService.username
      }
    this.homepageService.saveNewInventory(this.loginService.username,model).then((data: Response) => {
        data;
    }).catch((err) => {
            console.log("loadData Error", err);
    });
    console.log("Saving");
  }
  onClick(inventoryId){
    this.router.navigate(['features/inventory',inventoryId]);
  }

}
