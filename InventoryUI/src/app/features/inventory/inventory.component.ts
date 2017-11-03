import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';


@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})

export class InventoryComponent implements OnInit {
  unitsOfMeasurement = ['Kilogram(kgs)', 'Pounds(lbs)', 'Units', 'Millilitres(mls)'];

  items = [
    'The first choice!',
    'And another choice for you.',
    'but wait! A third!'
  ];
  private data: Array<any> = [{
    quantity: '2',
    unitOfMeasurement: 'asd',
    itemName: 'aadada',
    description: 'dadadad',
    location: 'adadada',
    status:'Instock'
  }, {
    quantity: '3',
    unitOfMeasurement: 'eee',
    itemName: 'dddddd',
    description: 'adadadad',
    location: 'weeqeqeeqe',
    status:'Damaged'
  }];
  public constructor() {

  }

  public ngOnInit(): void {
  }

}
