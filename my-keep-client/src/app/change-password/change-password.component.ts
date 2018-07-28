import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public user: User;
  public request = {
    newPassword: "newPassword",
    repeatedNewPassword: "newPassword"
  };

  constructor(private service: UserService,
    private router: Router) { }

  ngOnInit() {
    this.service.getMe().subscribe((data) => {
      this.user = data;
    });
  }

  changePassword() {
    this.service.changePassword(this.user.email, this.request).subscribe((data) => {
      this.service.logout();
      this.service.removeToken();
      this.router.navigateByUrl('/login');
    });
  }
  
}
