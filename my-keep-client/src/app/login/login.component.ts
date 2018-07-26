import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user = {
    email: "ivana@gmail.com",
    password: "ivana"
  };

  constructor(private service: UserService,
    private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.service.login(this.user).subscribe((data) => {
      localStorage.setItem('token', JSON.stringify(data.token));
      localStorage.setItem('notes', JSON.stringify(data.user.notesDashboard.widgets));
      localStorage.setItem('notesID', JSON.stringify(data.user.notesDashboard.id));
      localStorage.setItem('archive', JSON.stringify(data.user.archiveDashboard.widgets));
      localStorage.setItem('archiveID', JSON.stringify(data.user.archiveDashboard.id));
      localStorage.setItem('trash', JSON.stringify(data.user.trashBashboard.widgets));
      localStorage.setItem('trashID', JSON.stringify(data.user.trashBashboard.id));
  
      this.router.navigateByUrl('/home/dashboard');
    });
  }
}
