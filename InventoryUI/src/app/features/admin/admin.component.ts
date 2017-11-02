import { Component, OnInit } from '@angular/core';
import { AdminService } from './admin.service';
import { FormGroup, FormControl} from '@angular/forms'

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers : [AdminService]
})
export class AdminComponent implements OnInit {

  users= [{
    username:'',
    accesslevel:'',
    company:''
  }]

  constructor(private adminService : AdminService) { }

  ngOnInit() {
    this.adminService.getUserDetails().subscribe(data =>  this.users = data);
  }

  onSubmit(){
    console.log('hello');
  }
}
