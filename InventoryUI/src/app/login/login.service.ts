import { Injectable }     from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class LoginService {


  public username;

   setUsername(username){
    this.username=username;
   }

  private url = '../inventory/userServices/authenticate';
   constructor (private http: Http) {}

     isAuthenticate(username:string,password:string) : Observable<boolean> {
         // ...using get request
         this.setUsername(username);
         return this.http.get(this.url+'/'+username+'/'+password)
                        // ...and calling .json() on the response to return data
                         .map((res:Response) => res.json())
                         //...errors if any
                         .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

     }

}
