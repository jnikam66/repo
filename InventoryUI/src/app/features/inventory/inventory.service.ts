import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class InventoryService {


  private url = '../inventory/InventoryItemServices/';
  constructor(private http: Http) { }

  getInventoryItems(username: string, inventoryid: string): Observable<any> {
    return this.http.get(this.url + '/' +'getInventoryItemDetails'+'/'+ username+ '/' +inventoryid)
      // ...and calling .json() on the response to return data
      .map((res: Response) => res.json())
      //...errors if any
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }

  saveNewInventory(username:string,inventoryid:string,inventoryData){
    let that = this;
    let body = JSON.stringify(inventoryData);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return new Promise(function(resolve, reject) {
        that.http.post(that.url+ '/' +'addItem'+'/'+ username+ '/' +inventoryid, body, options)
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
  updateInventoryItem(username:string,inventoryid:string,data){
    let that = this;
    let body = JSON.stringify(data);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return new Promise(function(resolve, reject) {
        that.http.put(that.url+'updateItem/'+ username+ '/' +inventoryid, body, options)
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
