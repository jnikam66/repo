import { Injectable }     from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class RegisterService {

  private url = 'http://localhost:8080/Inventory/userServices/register';
   constructor (private http: Http) {}

     registerUser(registerData){
       let that = this;
       let body = JSON.stringify(registerData);
       return new Promise(function(resolve, reject) {
           that.http.post(this.url, body)
               .subscribe(
               (data: Response) => {
                   resolve(data);
               },
               error => {
                   reject(error);
               }
               );
       });
   }
}
