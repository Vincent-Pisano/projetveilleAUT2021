import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validMessage:string = "";
  user:User = new User();

  loginForm = new FormGroup({
    username: new FormControl("", [Validators.required, Validators.minLength(4)]),
    password: new FormControl("", [Validators.required, Validators.minLength(4)])
  });

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.loginForm.valid)
    {
      
    }
    else{
      this.validMessage = "Please fill the form before Signing In  !"
    }
  }

}
