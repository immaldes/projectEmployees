import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../model/employee';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeesUrl: string;

  constructor(private http: HttpClient, private router: Router) {
    this.employeesUrl = 'http://localhost:8080/employees';
  }

  public getEmployeeList(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.employeesUrl);
  }

  public getEmployeeDetail(id: string): Observable<Employee> {
    return this.http.get<Employee>(`${this.employeesUrl}/${id}`)
  }

  updateEmployee(employee: Employee): Observable<any> {
    return this.http.patch(`${this.employeesUrl}/${employee.id}`, employee);
  }

  public save(employee: Employee) {
    return this.http.post<Employee>(this.employeesUrl, employee);
  }
}