import { Component, OnInit } from '@angular/core';
import { DashboardService} from './dashboard.service'

@Component({
  selector: 'app-login',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[DashboardService]
})
export class DashboardComponent implements OnInit {

  constructor(private dashboardService : DashboardService) {}

  ngOnInit() {

  }
  authenticate(){
    if(this.dashboardService.authenticate("jyoti","password")){
     console.log("login successful");
    }else{
     console.log("Login unsuccessful");
    }
  }


}
