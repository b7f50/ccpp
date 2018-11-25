import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Company} from "./company";

const endpoint = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})

export class RestService {

  constructor(private http: HttpClient) { }

  getAll(name = ''):  Observable<Company[]> {
    return this.http.get(endpoint + '/companies', {
      params: new HttpParams()
        .set('name', name)
    }).pipe(map(data =>  <Company[]>data));
  }

  get(id: string) {
    return this.http.get(endpoint + '/companies/' + id);
  }

  save(company: Company) {
    console.log("save got company: " + company.id)

    this.http.post(endpoint + '/companies/', company).subscribe(status=> console.log(JSON.stringify(status)));
  }
}
