import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SportsListComponent } from './components/sports-list/sports-list.component';

const routes: Routes = [
  { path: "sports", component: SportsListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
