import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {catchError, finalize} from 'rxjs/operators';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Company} from "../company";
import {RestService} from "../rest.service";

export class CompanyListDataSource extends DataSource<Company> {

  private companiesSubject = new BehaviorSubject<Company[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  constructor(private restService: RestService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Company[]> {
    return this.companiesSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.companiesSubject.complete();
    this.loadingSubject.complete();
  }

  loadCompanies(filter = '', sortDirection = 'asc', pageIndex = 0, pageSize = 25) {

    console.log('loadCompanies begin');

    this.loadingSubject.next(true);

    this.restService.getAll(filter, sortDirection,
      pageIndex, pageSize).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(companies => this.companiesSubject.next(companies));

    console.log('loadCompanies end');

    this.companiesSubject.asObservable().subscribe(result => {console.log('length: ' + result.length)})
  }
}
