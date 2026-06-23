import {Component, inject, OnInit, signal} from '@angular/core';
import {TaskService} from '../../service/task.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {TaskListItem} from '../../model/task-list-item';

@Component({
  selector: 'app-task-details',
  imports: [
    RouterLink
  ],
  templateUrl: './task-details.html',
  styleUrl: './task-details.css',
})
export class TaskDetails implements OnInit{

  private taskService = inject(TaskService)

  private activatedRoute = inject(ActivatedRoute)

  private router = inject(Router)

  taskDetails = signal<undefined | TaskListItem>(undefined)

  ngOnInit(): void {
      const taskId = +this.activatedRoute.snapshot.paramMap.get('id')!
      this.taskService.getTaskById(taskId).subscribe({
        next: (data) => {
          this.taskDetails.set(data)
        },
        error: () => {
          this.router.navigate(['/not-found'])
        }
      })
  }

  deleteTask() {
    const taskId = +this.activatedRoute.snapshot.paramMap.get('id')!
    this.taskService.deleteTask(taskId).subscribe({
      next: () => {
        this.router.navigate(['/task-list'])
      }
    })
  }

  completeTask() {
    const taskId = +this.activatedRoute.snapshot.paramMap.get('id')!
    this.taskService.updateTask(taskId,{completed: true}).subscribe({
      next: () => {
        this.router.navigate(['/task-list'])
      }
    })
  }
}
