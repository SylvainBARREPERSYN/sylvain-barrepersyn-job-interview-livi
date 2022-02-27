import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {AngularMaterialModule} from "./modules/angular-material/angular-material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {TopBarComponent} from './components/top-bar/top-bar.component';
import {RouterModule} from "@angular/router";

const SHARED_MODULES = [
  AngularMaterialModule,
  CommonModule,
  HttpClientModule,
  ReactiveFormsModule,
  RouterModule
];

const SHARED_COMPONENTS = [
  TopBarComponent
];

@NgModule({
  declarations: SHARED_COMPONENTS,
  imports: SHARED_MODULES,
  exports: [
    ...SHARED_MODULES,
    ...SHARED_COMPONENTS
  ]
})
export class SharedModule {
}
