import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from 'src/app/service/employee-service.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees!: Employee[];

  constructor(private employeeService: EmployeeService, private router: Router) { 
   
  }

  ngOnInit() {
    this.employeeService.getEmployeeList().subscribe(data => {
      this.employees = data;
    });
    
  }

  openEmployeeDetails(id: string): void{
    this.router.navigate(['employees',id]);
  }

}
