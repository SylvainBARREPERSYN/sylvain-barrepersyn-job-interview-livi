export type MonitoringStatus = 'FAIL' | 'OK';

export interface MonitoringRequest {
  name: string,
  url: string
}

export interface MonitoringResponse {
  id: number,
  name: string,
  url: string,
  status: MonitoringStatus,
  createdAt: Date,
  updatedAt: Date
}
