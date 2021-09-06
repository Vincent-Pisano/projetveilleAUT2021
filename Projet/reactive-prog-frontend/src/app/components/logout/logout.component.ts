import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private serice: UserService, private route: Router){
    
  }

  ngOnInit(): void {
    this.serice.logout();
    this.route.navigate(['/login'])
  }

}
