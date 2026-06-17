import { Routes } from '@angular/router';
import {CreateTask} from './component/create-task/create-task';
import {Login} from './component/login/login';
import {TaskDetails} from './component/task-details/task-details';
import {TaskList} from './component/task-list/task-list';
import {NotFound} from './component/not-found/not-found';
import {Register} from './component/register/register';
import {loginGuard} from './guard/login-guard';
import {authGuard} from './guard/auth-guard';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'create-task', component: CreateTask, canActivate: [authGuard]},
  {path: 'login', component: Login, canActivate: [loginGuard]},
  {path: 'task-details/:id', component: TaskDetails, canActivate: [authGuard]},
  {path: 'task-list', component: TaskList, canActivate: [authGuard]},
  {path: 'register', component: Register, canActivate: [loginGuard]},
  {path: '**', component: NotFound}
];
