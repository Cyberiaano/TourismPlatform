import {contentChild, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GetServiceService {

  constructor(private http: HttpClient) { }

  getAnonnces() : Observable<any>{
    const anonncesPath = 'localhost:8080/public/home';
    return this.http.get<any>(anonncesPath);
  }
   getPublications() : Observable<any>{
    const publicationsPath = 'localhost:8080/public/explore';
    return this.http.get<any>(publicationsPath);
   }



}
