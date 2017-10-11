import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from "@angular/router";
import { AppComponents, AppRoutes} from "./app.routing";
import { DashboardComponent } from './dashboard/dashboard.component'
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FeaturesModule } from './features/features.module';

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
        RouterModule.forRoot(AppRoutes),
        FeaturesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
