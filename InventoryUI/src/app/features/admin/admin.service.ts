import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class AdminService {

  private url = '../inventory/userServices';


  constructor(private http: Http) { }

  getUserDetails(username:string){

    return this.http.get(this.url+"/getAllUsers"+"/"+username )
                   // ...and calling .json() on the response to return data
                    .map((response:Response) => response.json())

                    .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
  updateUser(data,username:string){
    let that = this;
    let body = JSON.stringify(data);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return new Promise(function(resolve, reject) {
        that.http.put(that.url+'/update/'+ username, body, options)
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
