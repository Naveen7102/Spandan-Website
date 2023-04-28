import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SportsListComponent } from './components/sports-list/sports-list.component';
import { FixturesComponent } from './components/fixtures/fixtures.component';
import { CreateOrJointTeamComponent } from './components/create-or-joint-team/create-or-joint-team.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { AuthGuardGuard } from './auth-guard.guard';

const routes: Routes = [
  { path: "", component:HomepageComponent},
  { path: "sports", component: SportsListComponent, canActivate: [AuthGuardGuard]},
  { path: "fixtures", component: FixturesComponent, canActivate: [AuthGuardGuard]},
  { path: "create-join-team", component: CreateOrJointTeamComponent, canActivate: [AuthGuardGuard]},
  { path: "login", component: LoginComponent},
  { path: "signup", component: SignupComponent},
  { path: "home", component: HomepageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
