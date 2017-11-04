import { Injectable }     from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class RegisterService {

  private url = '../inventory/userServices/register';
   constructor (private http: Http) {}

     registerUser(registerData){
       let that = this;
       let body = JSON.stringify(registerData);
       let headers = new Headers({ 'Content-Type': 'application/json' });
       let options = new RequestOptions({ headers: headers });

       return new Promise(function(resolve, reject) {
           that.http.post(that.url, body, options)
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
