import {Component, OnInit} from '@angular/core';
import {MonitoringRequest} from "../../monitoring.models";
import {MonitoringService} from "../../monitoring.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-monitoring-creation',
  templateUrl: './monitoring-creation.page.html',
  styleUrls: ['./monitoring-creation.page.scss']
})
export class MonitoringCreationPage implements OnInit {

  constructor(private monitoringService: MonitoringService,
              private router: Router,
              private snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  create(toCreate: MonitoringRequest) {

    this.monitoringService.create(toCreate)
      .subscribe({
        next: _ => this.router.navigate(['/monitorings']),
        error: e => {
          this.snackbar.open(e.error.message)
        }
      });
  }
}
