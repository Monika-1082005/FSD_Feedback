import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-feedback-form',
  standalone: false,
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.css'],  // Corrected to styleUrls
})
export class FeedbackFormComponent {
  feedbackForm: FormGroup;
  categories = ['Health & Safety', 'Food & Beverages', 'Event Organization', 'Facilities'];

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.feedbackForm = this.fb.group({
      category: ['', Validators.required],
      message: ['', [Validators.required, Validators.minLength(10)]],
      rating: [0, [Validators.required, Validators.min(1)]],
    });
  }

  get rating(): number {
    return this.feedbackForm.get('rating')?.value || 0;
  }

  setRating(star: number) {
    this.feedbackForm.patchValue({ rating: star });
  }

  submitFeedback() {
    // Log the form data before submitting
    console.log(this.feedbackForm.value); // This will log the form values
  
    const formData = {
      category: this.feedbackForm.value.category,
      feedbackDescription: this.feedbackForm.value.message,  // Ensure this key matches what the backend expects
      rating: this.rating,
    };
  
    this.http.post('http://localhost:8080/feedback/api/submit', formData).subscribe({
      next: (response) => {
        console.log("Feedback submitted successfully", response);
        this.feedbackForm.reset(); // Reset after success
      },
      error: (error) => {
        console.error("Error submitting feedback:", error);
      },
    });
  }
  
}
