import {Component, OnInit} from '@angular/core';
import {CompanyListDataSource} from './company-list-datasource';
import {RestService} from "../rest.service";

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css'],
})
export class CompanyListComponent implements OnInit {

  dataSource: CompanyListDataSource;

  displayedColumns = ['id', 'name'];

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.dataSource = new CompanyListDataSource(this.restService);
    this.dataSource.loadCompanies();
  }

  onRowClicked(row) {
    console.log('Row clicked: ', row);
  }
}
