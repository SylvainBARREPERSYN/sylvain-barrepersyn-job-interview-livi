import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MonitoringRequest, MonitoringResponse} from "./monitoring.models";

@Injectable({
  providedIn: 'root'
})
export class MonitoringService {

  static readonly BASE_URL = 'http://localhost:8080/service-monitoring/v1/monitorings';

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<MonitoringResponse[]> {
    return this.http.get<MonitoringResponse[]>(MonitoringService.BASE_URL);
  }

  public findById(id: number): Observable<MonitoringResponse> {
    return this.http.get<MonitoringResponse>(`${MonitoringService.BASE_URL}/${id}`);
  }

  public create(toCreate: MonitoringRequest): Observable<MonitoringResponse> {
    return this.http.post<MonitoringResponse>(MonitoringService.BASE_URL, toCreate);
  }

  public update(id: number, toUpdate: MonitoringRequest): Observable<MonitoringResponse> {
    return this.http.put<MonitoringResponse>(`${MonitoringService.BASE_URL}/${id}`, toUpdate);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${MonitoringService.BASE_URL}/${id}`);
  }
}
