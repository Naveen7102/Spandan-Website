import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import AlertModule from "ngx-bootstrap";
import { SportsListComponent } from './components/sports-list/sports-list.component'

@NgModule({
  declarations: [
    AppComponent,
    SportsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
