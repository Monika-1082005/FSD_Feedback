import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeedbackService } from '../auth/services/feedback.service';



@NgModule({
  imports: [CommonModule],
  providers: [FeedbackService],
})
export class CoreModule { }
