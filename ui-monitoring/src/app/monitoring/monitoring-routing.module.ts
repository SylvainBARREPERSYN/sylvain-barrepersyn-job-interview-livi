import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MonitoringListPage} from "./pages/monitoring-list/monitoring-list.page";
import {MonitoringCreationPage} from "./pages/monitoring-creation/monitoring-creation.page";
import {MonitoringEditionPage} from "./pages/monitoring-edition/monitoring-edition.page";
import {MonitoringsResolver} from "./resolvers/monitorings.resolver";
import {MonitoringResolver} from "./resolvers/monitoring.resolver";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: MonitoringListPage,
    resolve: {
      monitorings: MonitoringsResolver
    }
  },
  {
    path: 'new',
    component: MonitoringCreationPage
  },
  {
    path: ':id',
    component: MonitoringEditionPage,
    resolve: {
      monitoring: MonitoringResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MonitoringRoutingModule {
}
