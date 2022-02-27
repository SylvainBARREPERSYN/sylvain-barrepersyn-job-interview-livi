import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {MonitoringResponse} from "../../monitoring.models";
import {MonitoringService} from "../../monitoring.service";

@Component({
  selector: 'app-monitoring-card',
  templateUrl: './monitoring-card.component.html',
  styleUrls: ['./monitoring-card.component.scss']
})
export class MonitoringCardComponent implements OnInit {

  @Input() monitoring!: MonitoringResponse;

  constructor() {
  }

  ngOnInit(): void {
  }

  @HostBinding('class.error')
  public get error(): boolean {
    return this.monitoring?.status === 'FAIL';
  }
}
