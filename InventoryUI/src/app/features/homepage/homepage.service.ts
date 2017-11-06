import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Rx';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
@Injectable()
export class HomepageService {

  constructor(private http: Http) { }

  public url = '../inventory/InventoryServices';

  getInventoryDetails(username):Observable<any>{

    return this.http.get(this.url+'/'+'getInventoryDetails/'+ username)
                   // ...and calling .json() on the response to return data
                    .map((res:Response) => res.json())
                    //...errors if any
                    .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }
  saveNewInventory(username:string,inventoryData){
    let that = this;
    let body = JSON.stringify(inventoryData);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return new Promise(function(resolve, reject) {
        that.http.post(that.url+ '/' +'addInventory'+'/'+ username, body, options)
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
