import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { Customer } from 'src/models/customer';

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {

  validMessage:string = "";
  customer:Customer = new Customer();

  signUpForm = new FormGroup({
    username: new FormControl("", [Validators.required, Validators.minLength(4)]),
    password: new FormControl("", [Validators.required, Validators.minLength(4)])
  });

  constructor(private service: UserService ,private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.signUpForm.valid)
    {
      console.log("test 1");
      this.signUp();
    }
    else{
      this.validMessage = "Please fill the form before Signing In  !"
    }
  }

  signUp(): any {
    console.log("test 2");
    this.customer.username = this.signUpForm.get("username")!.value;
    this.customer.password = this.signUpForm.get("password")!.value;
    this.service.signUp(this.customer).subscribe(
      (data) => {
        this.customer = data;
        if (this.customer != undefined)
        {
          this.signUpForm.reset();
          this.router.navigateByUrl('/home', {state: this.customer})
        }
        else{
          this.validMessage = "Oops, an error occured, we couldn't create your account !"
        }
      }
    )
  }

}
