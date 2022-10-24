import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from 'src/app/service/employee-service.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  employeeID!: string;
  employee = new Employee;

  routeSub: Subscription = new Subscription;

  rolesOptions = new FormControl('');
  selectedRole?: String;



  managerOptions: string[] = ['Organizing', 'Leading', 'Representative', 'Mentor'];
  developerOptions: string[] = ['Back-endDeveloper', 'Front-endDeveloper', 'Full-stackDeveloper'];
  accountingOptions: string[] = ['Budgeting', 'InternalAudit', 'Track', 'Report'];
  hrOptions: string[] = ['ProcessPayroll', 'UpdatePolicies', 'MaintainEmployeeRecords'];
  marketingOptions: string[] = ['CustomerExperience', 'MarketResearch', 'DistributeNewsletters'];

  map = new Map().set("Manager", this.managerOptions)
    .set("Developer", this.developerOptions)
    .set("Accounting", this.accountingOptions)
    .set("Human Resources", this.hrOptions)
    .set("Marketing", this.marketingOptions);



  constructor(private router: Router, private employeeService: EmployeeService, private activatedRouter: ActivatedRoute, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.routeSub = this.activatedRouter.params.subscribe((params: Params) => {
      this.employeeID = params['id'];
      this.getEmployeesDetails(this.employeeID);
    })
  }

  getEmployeesDetails(id: string): void {
    this.employeeService.getEmployeeDetail(id).subscribe((res: Employee) => {
      this.employee = res;
      console.log(this.employee);
    })
  }

  saveDetails() {
    //console.log(this.rolesOptions);
    const arrayDecompus = [...this.rolesOptions.value];
    this.employee.function.roles.push(...arrayDecompus);
    console.log('Roles added.')

    console.log(this.employee.function.roles);

    this.employeeService.updateEmployee(this.employee).subscribe((response: Employee) => {
      //this.employee = response
      console.log('Employee updated.')
    })
  }

  onSelect(role: String): void {
    this.selectedRole = role;
    const dialogReff = this.dialog.open(DialogWindow, { width: '250px' });
    dialogReff.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteRole(selectedRole) {
    const index: number = this.employee.function.roles.indexOf(selectedRole);
    this.employee.function.roles.splice(index, 1)
    console.log(index)
    console.log('Role deleted.')
    console.log(this.employee.function.roles);
    this.employeeService.updateEmployee(this.employee).subscribe((response: Employee) => {
      //this.employee = response
      console.log('Employee updated.')
    })
  }
}

@Component({
  selector: 'app-dialogWindow',
  templateUrl: './dialogWindow.html',
})
export class DialogWindow {
  constructor(
    public dialogRef: MatDialogRef<DialogWindow>
  ) { }

  onNoClick() {
    this.dialogRef.close();
  }
}
