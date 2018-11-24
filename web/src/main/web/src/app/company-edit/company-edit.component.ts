import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import {Company} from "../company";

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.css']
})
export class CompanyEditComponent implements OnInit {

  company: Company;

  constructor(public rest:RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.rest.get(this.route.snapshot.params['id']).subscribe((data: Company) => {
      console.log(data);
      this.company = data;
    });
  }

  onSubmit() {
    console.log("to be saved: ");
    console.log(this.company);

    if(confirm("Are you sure to edit " + this.company.name)) {
      console.log('said yes');
      this.rest.save(this.company);
      this.router.navigate(['/companies'], {});
    } else {
      console.log("said no");
    }
  }
}
