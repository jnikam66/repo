import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import { DataTableModule,SharedModule } from 'primeng/primeng';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import {ButtonModule} from 'primeng/primeng';
import {DataGridModule,ToolbarModule} from 'primeng/primeng';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {PanelModule,DialogModule} from 'primeng/primeng';
import { FeaturesComponent } from './features.component';
import { HeaderComponent } from './header/header.component';
import { AppRoutes } from "../app.routing";
import { InventoryComponent } from './inventory/inventory.component';
import { ReportsComponent } from './reports/reports.component';
import { FooterComponent } from './footer/footer.component';
import { AdminComponent } from './admin/admin.component';
import { HomepageComponent } from './homepage/homepage.component'; 
import { MeasureComponent } from './measure/measure.component';
import { LocationComponent } from './location/location.component';
import { StatusComponent } from './status/status.component';


@NgModule({
imports: [
  CommonModule,
  DataTableModule,
  SharedModule,
  RouterModule,
  BrowserModule,
  ButtonModule,
  FormsModule,
  DataGridModule,
  ToolbarModule,
  PanelModule,
  DialogModule,
  BrowserAnimationsModule,
  RouterModule.forChild(AppRoutes)
],
declarations: [
  FeaturesComponent,
  HeaderComponent,
  InventoryComponent,
  ReportsComponent,
  FooterComponent,
  AdminComponent,
  HomepageComponent,
  MeasureComponent,
  LocationComponent,
  StatusComponent
]
})
export class FeaturesModule { }
