import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile-edit',
  templateUrl: './user-profile-edit.component.html',
  styleUrls: ['./user-profile-edit.component.css']
})
export class UserProfileEditComponent implements OnInit {

  user: User;

  constructor(private service: UserService,
    private router: Router) { }

  ngOnInit() {
    this.service.getMe().subscribe((data) => {
      this.user = data;
    });
  }

  edit() {
    this.service.update(this.user.email, this.user)
    .subscribe((data) => {
      this.user = data;
      this.router.navigateByUrl('/home/profile');
    });
  }
}
