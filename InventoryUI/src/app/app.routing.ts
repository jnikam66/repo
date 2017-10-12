import { NgModule }      from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component'
import { LoginComponent }  from './login/login.component';
import { RegisterComponent } from './register/register.component'
import { InventoryComponent } from './features/inventory/inventory.component'
import { FeaturesComponent } from './features/features.component'
import { ReportsComponent } from './features/reports/reports.component'
export const AppRoutes: any = [
    {path: '' , component : DashboardComponent },
    { path: 'login', component: LoginComponent },
    { path : 'register', component : RegisterComponent },
    { path : 'inventory', component : InventoryComponent },
    { path : 'features', component : FeaturesComponent},
    { path : 'reports', component :ReportsComponent}
];

export const AppComponents: any = [
    DashboardComponent,
    LoginComponent,
    RegisterComponent
];
