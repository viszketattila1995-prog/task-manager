import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TaskListItem} from '../model/task-list-item';
import {UpdateTaskCommand} from '../model/update-task-command';
import {CreateTaskCommand} from '../model/create-task-command';

@Injectable({
  providedIn: 'root',
})
export class TaskService {

  private httpClient = inject(HttpClient)

  private BASE_URL = 'http://localhost:8080/tasks'

  createTask (data: CreateTaskCommand) {
    return this.httpClient.post(this.BASE_URL, data)
  }

  getAllTask() {
    return this.httpClient.get<TaskListItem[]>(this.BASE_URL)
  }

  getTaskById(id: number) {
    return this.httpClient.get<TaskListItem>(`${this.BASE_URL}/${id}`)
  }

  updateTask(id: number, data: UpdateTaskCommand) {
    return this.httpClient.patch<UpdateTaskCommand>(`${this.BASE_URL}/${id}`, data)
  }

  deleteTask(id: number) {
    return this.httpClient.delete(`${this.BASE_URL}/${id}`)
  }

}
