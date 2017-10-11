import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { AppComponents, AppRoutes} from "../app.routing";
import { HeaderComponent } from '../header/header.component'
import { InventoryComponent } from './inventory/inventory.component';

@NgModule({
imports: [
  CommonModule,
  RouterModule,
  RouterModule.forRoot(AppRoutes)
],
declarations: [
  AppComponents,
  HeaderComponent,
  InventoryComponent
]
})
export class FeaturesModule { }
