import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import AlertModule from "ngx-bootstrap";
import { SportsListComponent } from './components/sports-list/sports-list.component';
import { FixturesComponent } from './components/fixtures/fixtures.component';
import { CreateOrJointTeamComponent } from './components/create-or-joint-team/create-or-joint-team.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomepageComponent } from './components/homepage/homepage.component'

@NgModule({
  declarations: [
    AppComponent,
    SportsListComponent,
    FixturesComponent,
    CreateOrJointTeamComponent,
    LoginComponent,
    SignupComponent,
    HomepageComponent
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
