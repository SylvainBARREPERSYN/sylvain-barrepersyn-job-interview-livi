import {NgModule} from '@angular/core';

import {MonitoringRoutingModule} from './monitoring-routing.module';
import {SharedModule} from "../shared/shared.module";
import { MonitoringListPage } from './pages/monitoring-list/monitoring-list.page';
import { MonitoringCardComponent } from './components/monitoring-card/monitoring-card.component';
import { MonitoringCreationPage } from './pages/monitoring-creation/monitoring-creation.page';
import { MonitoringEditionPage } from './pages/monitoring-edition/monitoring-edition.page';
import { MonitoringFormComponent } from './components/monitoring-form/monitoring-form.component';

@NgModule({
  declarations: [
    MonitoringListPage,
    MonitoringCardComponent,
    MonitoringCreationPage,
    MonitoringEditionPage,
    MonitoringFormComponent
  ],
  imports: [
    SharedModule,
    MonitoringRoutingModule
  ]
})
export class MonitoringModule {
}
