import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { urls } from '../Urls/urlList';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  //token is available at localstorage
  //token is of string type
  //if we have token then it will get it through getItem() function otherwise it will pass empty string ''
  let token: string = localStorage.getItem('token') || '';
  //you can also type above line as follow
  // let token: string = localStorage.getItem('token')!;
  //or
  //let token = localStorage.getItem('token');

  const router = inject(Router);
  //if token is available, then we will add it and move to next
  //if not take the user go back to landing page

  /*urls.forEach((e) => {
    if (e.includes(req.url)) return next(req);
  });
*/
  //above code is not running so will create following code
  for (const e of urls) {
    if (req.url.includes(e)) {
      return next(req);
    }
  }

  if (token) {
    // if yes then add token in header
    //add one header X-Auth-Token : token
    //request object is immutable so we can not modify it. So, we will create clone of it and make manipulation by passing it to the json object
    let modifiedReq = req.clone({
      //whatever the changes we want those things we will pass it to the json object
      headers: req.headers.set('X-Auth-Token', token),
    });
    return next(modifiedReq);
  } else {
    //redirect to landing
    //but if we dont have token, then there is no use of calling the API and eventually it will receive the errror. So commetting the following line
    //return next(req);
    //and replace with
    router.navigate(['/']); // it will redirect to landing page
    throw new Error('Unauthorized Access');
  }
};

//req is httprequest
//next is next interceptor or backendApp