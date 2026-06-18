import {Component, inject, OnInit, signal} from '@angular/core';
import {TaskService} from '../../service/task.service';
import {ActivatedRoute} from '@angular/router';
import {TaskListItem} from '../../model/task-list-item';

@Component({
  selector: 'app-task-details',
  imports: [],
  templateUrl: './task-details.html',
  styleUrl: './task-details.css',
})
export class TaskDetails implements OnInit{

  private taskService = inject(TaskService)

  private activatedRoute = inject(ActivatedRoute)

  taskDetails = signal<undefined | TaskListItem>(undefined)

  ngOnInit(): void {
      const taskId = +this.activatedRoute.snapshot.paramMap.get('id')!
      this.taskService.getTaskById(taskId).subscribe({
        next: (data) => {
          this.taskDetails.set(data)
        }
      })
  }
}
