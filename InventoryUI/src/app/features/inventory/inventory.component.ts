import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { FormsModule }   from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {InventoryService} from './inventory.service';
import {LoginService } from './../../login/login.service';
@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
  providers:[InventoryService]
})


export class InventoryComponent implements OnInit {
  unitsOfMeasurement = ['Kilogram(kgs)', 'Pounds(lbs)', 'Units', 'Millilitres(mls)'];

  items = [
    'The first choice!',
    'And another choice for you.',
    'but wait! A third!'
  ];

  private editModel = {
    itemid:'',
    quantity: '',
    unitOfMeasure: '',
    itemName: '',
    description: '',
    location: '',
    status:'',
    costPerUnit : ''
  };

    quantity: string;
    unitOfMeasurement: string;
    itemName: string;
    description: string;
    location: string;
    status:string;
    costPerUnit : string;

  private data: Array<any> = [{
    quantity: '',
    unitOfMeasure: '',
    itemName: '',
    description: '',
    location: '',
    status:''
  }];
  private inventoryId;
  public constructor(private route: ActivatedRoute, private inventoryService:InventoryService,private loginService : LoginService) {
    this.inventoryId = this.route.snapshot.paramMap.get('id');
  }


  public ngOnInit(): void {
    this.inventoryService.getInventoryItems(this.loginService.username,this.inventoryId).subscribe(
          data =>{
            this.data = data; //loggedIn;
        },
       err => {
               // Log errors if any
          console.log(err);
         });
  }

  public saveNewInventory(){
    let model = {

      quantity: this.quantity,
      unitOfMeasurement: this.unitOfMeasurement,
      itemName: this.itemName,
      description: this.description,
      location: this.location,
      status:this.status,
      costPerUnit : this.costPerUnit,
      }
    this.inventoryService.saveNewInventory(this.loginService.username,this.inventoryId,model).then((data: Response) => {
        data;
    }).catch((err) => {
            console.log("loadData Error", err);
    });
    console.log("Saving");
  }

  onSubmit(){

      this.inventoryService.updateInventoryItem(this.loginService.username,this.inventoryId,this.editModel).then((data: Response) => {
          data;
      }).catch((err) => {
              console.log("loadData Error", err);
      });
      console.log("Saving");
  }

  updateRecord(row){
    this.editModel.itemid = row.itemid;
    this.editModel.quantity = row.quantity;
    this.editModel.unitOfMeasure = row.unitOfMeasure;
    this.editModel.itemName = row.itemName;
    this.editModel.description = row.description;
    this.editModel.location = row.location;
    this.editModel.status = row.status;
    this.editModel.costPerUnit = row.costPerUnit;



  }

}
