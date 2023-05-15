import { Component, OnInit  } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { SignupService } from 'src/app/services/signup.service';
import { NGXLogger } from 'ngx-logger';
import { ClientLoggerService } from 'src/app/services/client-logger.service';

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

  constructor(private router: Router, private signupService: SignupService, private logger: NGXLogger, private clientLoggerService: ClientLoggerService) {
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
      this.logger.error("Passwords doesnot match in both the fields");
      this.clientLoggerService.log("Passwords doesnot match in both the fields");
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
          this.logger.info("Signup successful");
          this.clientLoggerService.log("Signup successful");
          console.log(data.message);
        },
        error: (e) => {
          this.logger.error("Signup Failed");
          this.clientLoggerService.log("Signup Failed");
          console.error(e);}
      });
      alert("success");
      this.router.navigate(['login']);
    }

  }

}
