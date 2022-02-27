import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MonitoringService} from "../../monitoring.service";
import {MonitoringRequest, MonitoringResponse} from "../../monitoring.models";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-monitoring-edition',
  templateUrl: './monitoring-edition.page.html',
  styleUrls: ['./monitoring-edition.page.scss']
})
export class MonitoringEditionPage implements OnInit {

  monitoring: MonitoringResponse;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private monitoringService: MonitoringService,
              private snackbar: MatSnackBar) {
    this.monitoring = route.snapshot.data['monitoring'];
  }

  ngOnInit(): void {
  }

  delete(): void {
    this.monitoringService.delete(this.monitoring.id)
      .subscribe({
        next: _ => this.router.navigate(['/monitorings']),
        error: e => this.snackbar.open(e.message)
      });
  }

  update(toUpdate: MonitoringRequest) {
    this.monitoringService.update(this.monitoring.id, toUpdate)
      .subscribe({
        next: _ => this.router.navigate(['/monitorings']),
        error: e => this.snackbar.open(e.message)
      });
  }
}
