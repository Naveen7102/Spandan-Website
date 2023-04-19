import { Component, OnInit  } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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

  constructor() {
    this.email = '';
    this.password = '';
    this.password_c = '';
    this.number = '';
  }

  ngOnInit(): void {
    
  }

  emailForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email])
  });

  onSubmit(): void{
    console.log(this.email);
    console.log(this.password);
    console.log(this.password_c);
    console.log(this.number);

    if((this.password === this.password_c) == false){
      alert("Passwords donot match. Please Enter the same password in both fields");
    }
    else{
      alert("success");
    }

  }

}
