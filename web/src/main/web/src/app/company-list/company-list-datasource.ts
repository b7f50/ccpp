import {catchError, finalize} from 'rxjs/operators';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Company} from "../company";
import {RestService} from "../rest.service";

export class CompanyListDataSource {

  private companiesSubject = new BehaviorSubject<Company[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  constructor(private restService: RestService) {
  }

  connect(): Observable<Company[]> {
    return this.companiesSubject.asObservable();
  }

  loadCompanies(name = 'Company 1') {

    console.log('loadCompanies begin');

    this.loadingSubject.next(true);

    this.restService.getAll(name).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(companies => this.companiesSubject.next(companies));

    console.log('loadCompanies end');

    this.companiesSubject.asObservable().subscribe(result => {console.log('length: ' + result.length)})
  }
}
