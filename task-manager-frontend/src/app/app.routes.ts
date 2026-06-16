import { Routes } from '@angular/router';
import {CreateTask} from './component/create-task/create-task';
import {Login} from './component/login/login';
import {TaskDetails} from './component/task-details/task-details';
import {TaskList} from './component/task-list/task-list';
import {NotFound} from './component/not-found/not-found';
import {Register} from './component/register/register';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'create-task', component: CreateTask},
  {path: 'login', component: Login},
  {path: 'task-details/:id', component: TaskDetails},
  {path: 'task-list', component: TaskList},
  {path: 'register', component: Register},
  {path: '**', component: NotFound}
];
