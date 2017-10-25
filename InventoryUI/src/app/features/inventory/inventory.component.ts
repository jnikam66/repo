import { Component, OnInit } from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
// Header
import {SearchTableComponent} from "ng2-search-table/ng2-search-table";
import {SimpleHeaderComponent} from "ng2-search-table/ng2-search-table";
import {SortableHeaderComponent} from "ng2-search-table/ng2-search-table";

// Filter
import {TextFilterComponent} from "ng2-search-table/ng2-search-table";
import {SelectFilterComponent} from "ng2-search-table/ng2-search-table";
import {FromToTextFilterComponent} from "ng2-search-table/ng2-search-table"

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})

export class InventoryComponent implements OnInit {

  items = [
      'The first choice!',
      'And another choice for you.',
      'but wait! A third!'
    ];
   data =[{
    quantity :'2' ,
    uOM:'asd',
    name:'aadada',
    description:'dadadad',
    location : 'adadada'
  },{
   quantity :'3' ,
   uOM:'eee',
   name:'dddddd',
   description:'adadadad',
   location : 'weeqeqeeqe'
 }]
  constructor() { }

  ngOnInit() {
  }

}
