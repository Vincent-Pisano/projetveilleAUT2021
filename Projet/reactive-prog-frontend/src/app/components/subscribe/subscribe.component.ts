import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/models/user';

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {

  validMessage:string = "";
  user:User = new User();

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
    this.user.username = this.signUpForm.get("username")!.value;
    this.user.password = this.signUpForm.get("password")!.value;
    this.service.signUp(this.user).subscribe(
      (data) => {
        console.log("test 3");
        console.log(this.user.username + " " + this.user.password)
        console.log(data.idUser)
        console.log(typeof(data))
        console.log(typeof(this.user))
        this.user = data;
        console.log(typeof(this.user))
        console.log(this.user);
        this.signUpForm.reset();

        //this.router.navigateByUrl('/home', {state: this.user})
      }
    )
  }

}
