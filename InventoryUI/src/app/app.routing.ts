import { NgModule }      from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component'
import { LoginComponent }  from './login/login.component';
import { RegisterComponent } from './register/register.component'

export const AppRoutes: any = [
    {path: '' , component : DashboardComponent },
    { path: 'login', component: LoginComponent },
    { path : 'register', component : RegisterComponent}
];

export const AppComponents: any = [
    DashboardComponent,
    LoginComponent,
    RegisterComponent
];
