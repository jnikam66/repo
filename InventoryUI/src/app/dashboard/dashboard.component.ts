import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
	loggedIn: boolean;
  constructor() {}

  ngOnInit() {

  }

}
