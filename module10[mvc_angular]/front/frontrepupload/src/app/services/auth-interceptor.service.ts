import { Injectable } from '@angular/core';
import { AuthManagerService } from './auth-manager.service';
import { Router } from '@angular/router';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private authManager: AuthManagerService,
    private router: Router) { }

  intercept(req: HttpRequest<any>, 
            next: HttpHandler ): Observable<HttpEvent<any>> {
    // avant que la requette parte au backend
    if (this.authManager.isLoggedIn()) {
      req = req.clone({ setHeaders : 
        { Authorization: `Basic ${this.authManager.getCredentials()}` }
      });
    }
    return next.handle(req)
               .pipe(catchError((error, caught) => {
                  console.log(error);
                  // est ce que c'est une erreur lié a une réponse du backend
                  if (error instanceof HttpErrorResponse) {
                    let resp : HttpErrorResponse = error;
                    if (resp.status == 401 || resp.status == 403) {
                      // on doit s'authentifier car non logué ou pas les droits
                      this.router.navigateByUrl("/login");
                    }
                  }
                  return throwError(error);
               }));
  }

}
