import {Component, OnInit} from '@angular/core';
import {MonitoringResponse} from "../../monitoring.models";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-monitoring-list',
  templateUrl: './monitoring-list.page.html',
  styleUrls: ['./monitoring-list.page.scss']
})
export class MonitoringListPage implements OnInit {

  monitorings: MonitoringResponse[];

  constructor(private route: ActivatedRoute) {
    this.monitorings = route.snapshot.data['monitorings'];
  }

  ngOnInit(): void {
  }

}
