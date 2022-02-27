import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MonitoringRequest, MonitoringResponse} from "../../monitoring.models";

@Component({
  selector: 'app-monitoring-form',
  templateUrl: './monitoring-form.component.html',
  styleUrls: ['./monitoring-form.component.scss']
})
export class MonitoringFormComponent implements OnInit {

  form: FormGroup;

  @Input() current: MonitoringResponse | undefined;

  @Output() formSubmit: EventEmitter<MonitoringRequest> = new EventEmitter<MonitoringRequest>();

  constructor(private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(50)]],
      url: ['', [Validators.required, Validators.maxLength(255)]]
    })
  }

  ngOnInit(): void {
    if (this.current) {
      this.form.patchValue(this.current);
    }
  }

  onSubmit(): void {
    this.formSubmit.emit(this.form.value);
  }

}
