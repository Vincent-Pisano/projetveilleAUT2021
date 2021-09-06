import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Error404Component } from './components/error404/error404.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { SubscribeComponent } from './components/subscribe/subscribe.component';
import { AuthguardGuard as AuthGuard } from './service/authguard.guard';

const routes: Routes = [
  {path:'home', component:HomeComponent, canActivate:[AuthGuard]},
  {path:'logout', component:LogoutComponent, canActivate:[AuthGuard]},

  {path:'login', component: LoginComponent},
  {path:'subscribe', component: SubscribeComponent},

  {path:'', redirectTo:"/login", pathMatch:'full'},
  {path: '**', component: Error404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
