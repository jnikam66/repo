
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from "@angular/router";
import { AppComponents, AppRoutes, appRoutingProviders} from "./app.routing";
import { DashboardComponent } from './dashboard/dashboard.component'
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FeaturesModule } from './features/features.module';
import {AuthGuard} from './auth-guard.service';
import { AuthService } from './auth.service';
import { LoginService} from './login/login.service'

@NgModule({
  declarations: [
    AppComponent,
    AppComponents,
    LoginComponent,
    RegisterComponent,
    DashboardComponent
  ],
  imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RouterModule,
		    FeaturesModule,
        RouterModule.forRoot(AppRoutes),
      ],
  providers: [appRoutingProviders,AuthGuard,AuthService,LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
