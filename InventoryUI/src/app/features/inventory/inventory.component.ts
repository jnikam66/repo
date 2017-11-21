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

  private editModel = {
    itemid:'',
    initial_quantity:'',
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
    sold : string;
  private data: Array<any> = [{
    quantity: '',
    initial_quantity:'',
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
    this.getInventoryItems();
  }

  getInventoryItems(){
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
    var status1;
        if(parseInt(this.quantity)>=5){
          status1 = "In stock";
        }else if(parseInt(this.quantity)<5){
          status1 = "Needs restocking";
        }else if(parseInt(this.quantity)==0){
          status1= "Sold out";
        }
    let model = {
      initial_quantity:this.quantity,
      quantity: this.quantity,
      unitOfMeasurement: this.unitOfMeasurement,
      itemName: this.itemName,
      description: this.description,
      location: this.location,
      status:status1,
      costPerUnit : this.costPerUnit,
      }
    this.inventoryService.saveNewInventory(this.loginService.username,this.inventoryId,model).then((data: Response) => {
        this.getInventoryItems();
    }).catch((err) => {
            console.log("loadData Error", err);
    });
    console.log("Saving");
  }

  onSubmit(){
    this.editModel.quantity = (parseInt(this.editModel.quantity)-parseInt(this.sold)).toString();

        if(parseInt(this.editModel.quantity)>=5){
          this.editModel.status = "In stock";
        }else if(parseInt(this.editModel.quantity)<5){
          this.editModel.status = "Needs restocking";
        }else if(parseInt(this.editModel.quantity)==0){
          this.editModel.status= "Sold out";
        }
      this.inventoryService.updateInventoryItem(this.loginService.username,this.inventoryId,this.editModel).then((data: Response) => {
          this.getInventoryItems();
      }).catch((err) => {
              console.log("loadData Error", err);
      });
      console.log("Saving");
  }

  updateRecord(row){
var status;
    if(row.quantity>=5){
      status = "In stock";
    }else if(row.quantity<5){
      status = "Needs restocking";
    }else if(row.quantity==0){
      status= "Sold out";
    }
    this.editModel.itemid = row.itemid;
    this.editModel.quantity = row.quantity;
    this.editModel.unitOfMeasure = row.unitOfMeasure;
    this.editModel.itemName = row.itemName;
    this.editModel.description = row.description;
    this.editModel.location = row.location;
    this.editModel.status = status;
    this.editModel.costPerUnit = row.costPerUnit;




  }

}
