import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { DashboardComponent } from '../dashboard/dashboard.component';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  providers: [DashboardComponent],
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private breakpointObserver: BreakpointObserver,
    private dashboardComponent: DashboardComponent,
    private service: UserService,
    private router: Router
  ) { }
  
  public logout() {
    this.service.logout();
    this.service.removeAll();
    this.router.navigateByUrl('/login');
  }

}
