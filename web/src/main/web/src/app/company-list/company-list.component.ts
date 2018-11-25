import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {CompanyListDataSource} from './company-list-datasource';
import {RestService} from "../rest.service";
import {fromEvent} from "rxjs";
import {debounceTime, distinctUntilChanged, filter, tap} from "rxjs/operators";

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css'],
})
export class CompanyListComponent implements OnInit, AfterViewInit {

  dataSource: CompanyListDataSource;

  displayedColumns = ['id', 'name'];

  @ViewChild('input') input: ElementRef;

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.dataSource = new CompanyListDataSource(this.restService);
    this.dataSource.loadCompanies("");
  }

  ngAfterViewInit() {

    // server-side search
    fromEvent(this.input.nativeElement,'keyup')
      .pipe(
        debounceTime(500),
        distinctUntilChanged(),
        tap(() => {
          this.dataSource.loadCompanies(this.input.nativeElement.value);
        })
      )
      .subscribe();
  }
}
