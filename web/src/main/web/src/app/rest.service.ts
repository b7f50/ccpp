import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Company} from "./company";
import {environment} from "../environments/environment";




@Injectable({
  providedIn: 'root'
})

export class RestService {

  endpoint = environment.baseUrl;

    constructor(private http: HttpClient) { }

  getAll(name = ''):  Observable<Company[]> {
    return this.http.get(this.endpoint + '/companies', {
      params: new HttpParams()
        .set('name', name)
    }).pipe(map(data =>  <Company[]>data));
  }

  get(id: string) {
    return this.http.get(this.endpoint + '/companies/' + id);
  }

  save(company: Company) {
    console.log("save got company: " + company.id)

    this.http.post(this.endpoint + '/companies/', company).subscribe(status=> console.log(JSON.stringify(status)));
  }
}
