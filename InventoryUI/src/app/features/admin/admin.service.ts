import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class AdminService {

  private url = 'http://localhost:8080/org.inventory/userServices/getAllUsers';


  constructor(private http: Http) { }

  getUserDetails(){

    return this.http.get(this.url)
                   // ...and calling .json() on the response to return data
                    .map((response:Response) => response.json())

                    .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
}
