import { Component, OnInit  } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { SignupService } from 'src/app/services/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  email: string;
  password: string;
  password_c: string;
  number: string;
  name: string;

  constructor(private router: Router, private signupService: SignupService) {
    this.email = '';
    this.password = '';
    this.password_c = '';
    this.number = '';
    this.name = '';
  }

  ngOnInit(): void {
    
  }

  emailForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email])
  });

  onSubmit(): void{

    if((this.password === this.password_c) == false){
      alert("Passwords donot match. Please Enter the same password in both fields");
    }
    else{
      const data = {
        user_type: "Participant",
        password: this.password,
        username: this.name,
        email: this.email,
        phone_no: this.number
      };
      console.log(data);
      this.signupService.signup(data)
      .subscribe({
        next: (data:any) => {
          console.log(data.message);
        },
        error: (e) => console.error(e)
      });
      alert("success");
      this.router.navigate(['login']);
    }

  }

}
