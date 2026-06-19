import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router, RouterLink} from '@angular/router';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, NgClass, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  private authService = inject(AuthService)

  private formBuilder = inject(FormBuilder)

  private router = inject(Router)

  errorMessage = signal<string | undefined>(undefined);

  registerForm: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  register() {
    this.authService.registerUser(this.registerForm.value).subscribe({
      next: () => {
        this.registerForm.reset()
        this.router.navigate(['/login'])
      },
      error: (err) => {
        if (err.error.fieldErrors) {
          for (const validationError of err.error.fieldErrors) {
            const formControl = this.registerForm.get(validationError.field)
            if (formControl) {
              formControl.setErrors({serverError: validationError.message})
            }
          }
        } else {
          this.errorMessage.set(err.error.error)
        }
      }
    })
  }
}
