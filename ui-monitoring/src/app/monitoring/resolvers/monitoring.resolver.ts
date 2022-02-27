import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {MonitoringService} from "../monitoring.service";
import {MonitoringResponse} from "../monitoring.models";

@Injectable({
  providedIn: 'root'
})
export class MonitoringResolver implements Resolve<MonitoringResponse> {

  constructor(private monitoringService: MonitoringService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MonitoringResponse> {
    return this.monitoringService.findById(route.params['id']);
  }
}
