import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: User; 

  constructor(private service: UserService) {
   }

  ngOnInit() {
    this.service.getMe().subscribe((data) => {
      this.user = data;
    });
  }
}
