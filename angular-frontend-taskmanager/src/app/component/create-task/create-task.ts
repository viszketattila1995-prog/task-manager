import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {TaskService} from '../../service/task.service';
import {Router} from '@angular/router';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-create-task',
  imports: [ReactiveFormsModule, NgClass],
  templateUrl: './create-task.html',
  styleUrl: './create-task.css',
})
export class CreateTask {

  private taskService = inject(TaskService)

  private formBuilder = inject(FormBuilder)

  private router = inject(Router)

  taskForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    description: ['', Validators.required]
  })

  createNewTask() {
    this.taskService.createTask(this.taskForm.value).subscribe({
      next: () => {
        this.taskForm.reset()
        this.router.navigate(['/task-list'])
    }
    })
  }
}
