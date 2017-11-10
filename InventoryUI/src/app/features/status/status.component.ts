import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  data:Array<any>=[
{'status':'Status'},
{'status':'In Stock'},
{'status':'On Backorder'},
{'status':'Held at Shipping Point'},
{'status':'Shipped (In-Transit)'},
{'status':'Trucked (In-Transit)'},
{'status':'Held at Customs'},
{'status':'Held at Destination'},
{'status':'Accepted at Destination'}]
  constructor() { }

  ngOnInit() {
  }

}
