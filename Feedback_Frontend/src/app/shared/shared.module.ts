import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FeedbackFormComponent } from '../feedbacks/components/feedback-form/feedback-form.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FeedbackFormComponent
  ],
  imports: [
    CommonModule, ReactiveFormsModule
  ],
  exports: [
    FeedbackFormComponent
  ]
})
export class SharedModule { }
