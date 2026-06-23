import {Component, inject, OnInit, signal} from '@angular/core';
import {TaskService} from '../../service/task.service';
import {RouterLink} from '@angular/router';
import {TaskListItem} from '../../model/task-list-item';

@Component({
  selector: 'app-task-list',
  imports: [RouterLink],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css',
})
export class TaskList implements OnInit {

  taskList = signal<TaskListItem[]>([])

  private taskService = inject(TaskService)

  ngOnInit(): void {
    this.taskService.getAllTask().subscribe({
      next: (data) => {
        this.taskList.set(data.filter(task => !task.completed));
      }
    })
  }

}
