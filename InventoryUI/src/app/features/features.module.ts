import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { FeaturesComponent } from './features.component'
//import { AppComponents, AppRoutes} from "../app.routing";
import { HeaderComponent } from './header/header.component'
import { InventoryComponent } from './inventory/inventory.component';
import { ReportsComponent } from './reports/reports.component';


@NgModule({
imports: [
  CommonModule,
  RouterModule
  //RouterModule.forRoot(AppRoutes)
],
declarations: [
//  AppComponents,
  FeaturesComponent,
  HeaderComponent,
  InventoryComponent,
  ReportsComponent
]
})
export class FeaturesModule { }
