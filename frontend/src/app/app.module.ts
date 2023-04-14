import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import AlertModule from "ngx-bootstrap";
import { SportsListComponent } from './components/sports-list/sports-list.component';
import { FixturesComponent } from './components/fixtures/fixtures.component';
import { CreateOrJointTeamComponent } from './components/create-or-joint-team/create-or-joint-team.component'

@NgModule({
  declarations: [
    AppComponent,
    SportsListComponent,
    FixturesComponent,
    CreateOrJointTeamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
