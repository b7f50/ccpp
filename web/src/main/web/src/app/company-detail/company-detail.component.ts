import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import {Company} from "../company";

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {

  company: Company;

  constructor(public rest:RestService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.rest.get(this.route.snapshot.params['id']).subscribe((data: Company) => {
      console.log(data);
      this.company = data;
    });
  }

  onSubmit() {
    alert('Thanks! ' +  this.company.expirationDate);
  }
}
