import { Component, OnInit } from '@angular/core';
import { DashboardService} from './dashboard.service'

@Component({
  selector: 'app-login',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[DashboardService]
})

export class DashboardComponent implements OnInit {
	loggedIn: boolean;
  constructor(private dashboardService : DashboardService) {}

  ngOnInit() {

  }
  authenticate(){
		this.dashboardService.isAuthenticate()
                           .subscribe(
                               loggedIn => this.loggedIn = loggedIn, //Bind to view
                                err => {
                                    // Log errors if any
                                    console.log(err);
                                });
  }


}
