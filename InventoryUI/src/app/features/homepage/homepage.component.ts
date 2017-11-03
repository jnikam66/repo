import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  private inventoryList: Array<any> = [{
    inventoryId :'1',
    inventoryName:'ABC',
    location :'sdf',
    country:'df',
    createdBy:'dfsf',
    company:'dsfsf'
  }];
  constructor() { }

  ngOnInit() {
  }

}
