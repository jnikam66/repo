import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

@Injectable()
export class DashboardService {
  let url = 'http://localhost:8080/Inventory/userServices/register/';
  constructor(private http : Http){}

   authenticate(username: string, password:string ){
   return new Promise(function(resolve, reject){
    this.http.get(this.url+ username+'/'+password).subscribe(
       (data: Response) => {
           resolve(data);
       },
       error => {
           reject(error);
       }
     );
     });
  }
  private getHeaders(){
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    return headers;
  }

}
