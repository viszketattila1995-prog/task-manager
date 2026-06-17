import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CreateUserCommand} from '../model/create-user-command';
import {JwtResponse} from '../model/jwt-response';
import {LoginCommand} from '../model/login-command';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private httpClient = inject(HttpClient);

  private BASE_URL = 'http://localhost:8080'

  registerUser(data: CreateUserCommand) {
    return this.httpClient.post(this.BASE_URL + '/user', data)
  }

  loginWithJwtToken(data: LoginCommand) {
    return this.httpClient.post<JwtResponse>(this.BASE_URL + '/auth/login', data)
  }

  logout() {
    localStorage.removeItem('token')
  }

}
