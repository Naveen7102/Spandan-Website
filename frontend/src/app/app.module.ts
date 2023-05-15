import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import AlertModule from "ngx-bootstrap";
import { SportsListComponent } from './components/sports-list/sports-list.component';
import { FixturesComponent } from './components/fixtures/fixtures.component';
import { CreateOrJointTeamComponent } from './components/create-or-joint-team/create-or-joint-team.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomepageComponent } from './components/homepage/homepage.component'

// import { environment } from '../environments/environment';

// const environmentConfig: any = environment.logger;s

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
    AppRoutingModule,
    LoggerModule.forRoot({
      level: NgxLoggerLevel.DEBUG, // Set the desired log level
      serverLogLevel: NgxLoggerLevel.OFF, // Disable server-side logging
      enableSourceMaps: true, // Enable source map support for error stack traces
      disableConsoleLogging: false, // Enable console logging,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
