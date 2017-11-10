import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-measure',
  templateUrl: './measure.component.html',
  styleUrls: ['./measure.component.css']
})
export class MeasureComponent implements OnInit {

  constructor() { }
  data : Array<any>=[

   {'unit':'Each'},


   {'unit':'Box'},


   {'unit':'Case'},


   {'unit':'Dozen'},


   {'unit':'Inch'},


   {'unit':'Foot'},


   {'unit':'Yard'},


   {'unit':'Meter'},


   {'unit':'Ounce'},


   {'unit':'Pound'},


   {'unit':'Kilogram'},


   {'unit':'Quart'},


   {'unit':'Gallon'},


   {'unit':'Cubic Yard'},


   {'unit':'Liter'},


   {'unit':'Square Foot'},


   {'unit':'Acre'},


   {'unit':'Square Meter'},


   {'unit':'Minute'},


   {'unit':'Hour'},


   {'unit':'Day'},


   {'unit':'Year'}

  ]

  ngOnInit() {
  }

}
