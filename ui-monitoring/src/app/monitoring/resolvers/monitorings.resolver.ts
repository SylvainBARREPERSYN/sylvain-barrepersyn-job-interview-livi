import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot, ActivatedRoute
} from '@angular/router';
import { Observable, of } from 'rxjs';
import {MonitoringService} from "../monitoring.service";
import {MonitoringResponse} from "../monitoring.models";

@Injectable({
  providedIn: 'root'
})
export class MonitoringsResolver implements Resolve<MonitoringResponse[]> {

  constructor(private monitoringService: MonitoringService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MonitoringResponse[]> {
    return this.monitoringService.findAll();
  }
}
