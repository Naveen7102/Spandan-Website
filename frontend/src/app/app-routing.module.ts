import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SportsListComponent } from './components/sports-list/sports-list.component';
import { FixturesComponent } from './components/fixtures/fixtures.component';
import { CreateOrJointTeamComponent } from './components/create-or-joint-team/create-or-joint-team.component';

const routes: Routes = [
  { path: "sports", component: SportsListComponent},
  { path: "fixtures", component: FixturesComponent},
  { path: "create-join-team", component: CreateOrJointTeamComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
