import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public user = {
    newPassword: "newPassword",
    repeatedNewPassword: "newPassword"
  };

  userId: number;
  constructor(private service: UserService,
    private router: Router) { }

  ngOnInit() {
    this.service.getMe().subscribe((data) => {
      this.userId = data.id;
    });
  }

  changePassword() {
    this.service.changePassword(this.userId, this.user).subscribe((data) => {
      this.service.logout();
      this.service.removeToken();
      this.router.navigateByUrl('/login');
    });
  }
  
}
