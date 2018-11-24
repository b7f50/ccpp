import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import {Company} from "./company";

const endpoint = 'http://localhost:8080/api/';


@Injectable({
  providedIn: 'root'
})

export class RestService {

  constructor(private http: HttpClient) { }

  getAll(filter = '', sortOrder = 'asc',
         pageNumber = 0, pageSize = 25):  Observable<Company[]> {
    return this.http.get(endpoint + '/companies', {
      params: new HttpParams()
        .set('filter', filter)
        .set('sortOrder', sortOrder)
        .set('pageNumber', pageNumber.toString())
        .set('pageSize', pageSize.toString())
    }).pipe(map(data =>  data));
  }

  get(id: string) {
    return this.http.get(endpoint + '/companies/' + id);
  }
}
