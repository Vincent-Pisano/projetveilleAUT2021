import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { Customer } from 'src/models/customer';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validMessage:string = "";
  customer:Customer = new Customer();

  SignInForm = new FormGroup({
    username: new FormControl("", [Validators.required, Validators.minLength(4)]),
    password: new FormControl("", [Validators.required, Validators.minLength(4)])
  });

  constructor(private service: UserService, private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.SignInForm.valid)
    {
      this.signIn();
    }
    else{
      this.validMessage = "Please fill the form before Signing In  !"
    }
  }

  signIn(): any {
    this.service.signIn(this.SignInForm.get("username")!.value, this.SignInForm.get("password")!.value).subscribe(
      (data) => {
        this.customer = data;
        if (this.customer != undefined) {
          this.SignInForm.reset();
          sessionStorage.setItem('username', this.customer.username!)
          this.router.navigateByUrl('/home', {state: this.customer})
        }
        else{
          this.validMessage = "You donkey, check your credentials  !"
        }
      }
    )
  }

}
