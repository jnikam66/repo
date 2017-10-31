import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Ng2TableModule  } from 'ng2-table/ng2-table';
import { RouterModule } from "@angular/router";
import { FeaturesComponent } from './features.component';
import { HeaderComponent } from './header/header.component';
import {  AppRoutes} from "../app.routing";
import { InventoryComponent } from './inventory/inventory.component';
import { ReportsComponent } from './reports/reports.component';
import { FooterComponent } from './footer/footer.component';


@NgModule({
imports: [
  CommonModule,
  Ng2TableModule,
  RouterModule,
  RouterModule.forChild(AppRoutes)
],
declarations: [
  FeaturesComponent,
  HeaderComponent,
  InventoryComponent,
  ReportsComponent,
  FooterComponent
]
})
export class FeaturesModule { }
