import { Injectable }     from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class LoginService {



  private url = 'http://localhost:8080/Inventory/userServices/authenticate';
   constructor (private http: Http) {}

     isAuthenticate(username:string,password:string) : Observable<boolean> {
     const header = new Headers();
     header.append('Access-Control-Allow-Headers', 'Content-Type');
     header.append('Access-Control-Allow-Methods', 'GET');
     header.append('Access-Control-Allow-Origin', '*');
         // ...using get request
         return this.http.get(this.url+'/'+username+'/'+password,{headers: header})
                        // ...and calling .json() on the response to return data
                         .map((res:Response) => res.json())
                         //...errors if any
                         .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

     }
}
