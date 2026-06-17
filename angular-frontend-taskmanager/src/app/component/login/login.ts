import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, NgClass],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private formBuilder = inject(FormBuilder)

  private authService = inject(AuthService)

  private router = inject(Router)

  loginForm: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  login() {
    this.authService.loginWithJwtToken(this.loginForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        console.log("logged in");
        this.loginForm.reset()
        this.router.navigate(['/task-list'])
      }
    })
  }

}
