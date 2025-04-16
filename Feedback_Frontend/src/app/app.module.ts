import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { SharedModule } from './shared/shared.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { interceptors } from './auth/interceptors';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,RouterModule,
    AppRoutingModule,SharedModule,HttpClientModule ,
  ],
  providers: [provideHttpClient(withInterceptors(interceptors))],
  bootstrap: [AppComponent]
})
export class AppModule { }
