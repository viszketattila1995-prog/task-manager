import {Component, inject, OnInit} from '@angular/core';
import {TaskService} from '../../service/task.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-edit-task',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './edit-task.html',
  styleUrl: './edit-task.css',
})
export class EditTask implements OnInit{

  private taskService = inject(TaskService)

  private router = inject(Router)

  private route = inject(ActivatedRoute)

  private formBuilder = inject(FormBuilder)

  editForm: FormGroup = this.formBuilder.group({
    name: [''],
    description: ['']
  })

  ngOnInit() {
      const taskId = +this.route.snapshot.paramMap.get('id')!
      this.taskService.getTaskById(taskId).subscribe({
        next: (data) => {
          this.editForm.patchValue(data)
        }
      })
  }

  updateTask() {
    const taskId = +this.route.snapshot.paramMap.get('id')!
    this.taskService.updateTask(taskId, this.editForm.value).subscribe({
      next: () => {
        this.editForm.reset()
        this.router.navigate(['/task-details', taskId])
      }
    })
  }

  goBack() {
    const taskId = +this.route.snapshot.paramMap.get('id')!
    this.router.navigate(['task-details', taskId])
  }

}
