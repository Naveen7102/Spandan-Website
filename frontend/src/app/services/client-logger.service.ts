import { Injectable } from '@angular/core';
import { saveAs } from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class ClientLoggerService {

  constructor() { }
  private logs: string[] = [];

  log(message: string) {
    const logEntry = `${new Date().toISOString()} INFO [frontend] ${message}`;
    this.logs.push(logEntry);
    console.log(logEntry);
  }

  saveLogsToFile() {
    const logContent = this.logs.join('\n');
    console.log(this.logs);
    const blob = new Blob([logContent], { type: 'text/plain;charset=utf-8' });
    saveAs(blob, 'app.log');
  }
}
