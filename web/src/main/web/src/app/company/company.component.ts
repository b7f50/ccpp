import { Component, OnInit } from '@angular/core';
import { Company } from "../company";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  company: Company = {
    id: 47,
    name: 'Company 47',
    expirationDate: new Date('2018-09-23'),
    usersLimit: 10,
    quota: 100
  };

  constructor() { }

  ngOnInit() {
  }
}
