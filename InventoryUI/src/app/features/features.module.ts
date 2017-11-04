import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import {DataTableModule,SharedModule} from 'primeng/primeng';
import { FeaturesComponent } from './features.component';
import { HeaderComponent } from './header/header.component';
import { AppRoutes } from "../app.routing";
import { InventoryComponent } from './inventory/inventory.component';
import { ReportsComponent } from './reports/reports.component';
import { FooterComponent } from './footer/footer.component';
import { AdminComponent } from './admin/admin.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import {ButtonModule} from 'primeng/primeng';
import { HomepageComponent } from './homepage/homepage.component';

@NgModule({
imports: [
  CommonModule,
  DataTableModule,
  SharedModule,
  RouterModule,
  BrowserModule,
  ButtonModule,
  FormsModule,
  RouterModule.forChild(AppRoutes)
],
declarations: [
  FeaturesComponent,
  HeaderComponent,
  InventoryComponent,
  ReportsComponent,
  FooterComponent,
  AdminComponent,
  HomepageComponent
]
})
export class FeaturesModule { }
