import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, 
  MatListModule, MatGridListModule, MatCardModule, MatMenuModule, MatDialogModule} from '@angular/material';
import { RouterModule, Routes } from '@angular/router';
import { GridsterModule } from 'angular2gridster';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardService } from './service/dashboard.service';
import { WidgetUpdateComponent } from './widget-update/widget-update.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { HeaderInterceptor } from './header-interceptor';
import { DashboardArchiveComponent } from './dashboard-archive/dashboard-archive.component';
import { DashboardTrashComponent } from './dashboard-trash/dashboard-trash.component';

const routes: Routes = [
  {
    path: "",
    component: RegisterComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "profile",
    component: RegisterComponent
  },
  {
    path: "change-password",
    component: RegisterComponent
  },
  {
    path: "home",
    component: HomeComponent,
    children: [
      {
        path: "dashboard",
        component: DashboardComponent
      },
      {
        path: "archive",
        component: DashboardArchiveComponent
      },
      {
        path: "trash",
        component: DashboardTrashComponent
      }
    ]
  }

]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DashboardComponent,
    WidgetUpdateComponent,
    DashboardArchiveComponent,
    DashboardTrashComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    GridsterModule.forRoot(),
    RouterModule.forRoot(
      routes
    ),
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatDialogModule,
  ],
  entryComponents: [DashboardComponent, WidgetUpdateComponent],
  providers: [
    DashboardService,
    { provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptor, multi: true },
  ],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
