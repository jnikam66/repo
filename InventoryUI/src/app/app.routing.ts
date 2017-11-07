import { NgModule }      from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard }from './auth-guard.service';
import { DashboardComponent } from './dashboard/dashboard.component'
import { LoginComponent }  from './login/login.component';
import { RegisterComponent } from './register/register.component'
import { InventoryComponent } from './features/inventory/inventory.component'
import { FeaturesComponent } from './features/features.component'
import { ReportsComponent } from './features/reports/reports.component'
import { AdminComponent } from './features/admin/admin.component'
import {HomepageComponent} from './features/homepage/homepage.component'
import {LocationComponent} from './features/location/location.component'
import {MeasureComponent} from './features/measure/measure.component'
import {StatusComponent} from './features/status/status.component'

export const AppRoutes: any = [
    {path: '' , component : DashboardComponent },
    { path: 'login', component: LoginComponent },
    { path : 'register', component : RegisterComponent },
    //{ path : 'features', component : FeaturesComponent},


	{ path: 'features', component: FeaturesComponent, canActivate: [AuthGuard],
		children: [
		  { path: '', redirectTo: 'inventoryList', pathMatch: 'full' },
      { path : 'reports', component :ReportsComponent},
      { path: 'inventory/:id', component: InventoryComponent },
      { path: 'admin', component: AdminComponent },
      { path: 'inventoryList', component: HomepageComponent},
      { path: 'location', component: LocationComponent },
      { path: 'measure', component: MeasureComponent },
      { path: 'status', component: StatusComponent }
    ]
  }
];
export const appRoutingProviders: any[] = [];


export const AppComponents: any = [
    DashboardComponent,
    LoginComponent,
    RegisterComponent
];
