import { Component } from '@angular/core';
import {
  AuthenticationRequest,
  AuthenticationResponse,
} from '../../services/models';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  authRequest: AuthenticationRequest = { email: '', password: '' };
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  login(): void {
    this.errorMsg = [];
    this.authService
      .authenticate({
        body: this.authRequest,
      })
      .subscribe({
        next: (res: AuthenticationResponse): void => {
          //todo save token
          this.router.navigate(['games']);
        },
        error: (err): void => {
          console.log(err);
        },
      });
  }

  register(): void {
    this.router.navigate(['register']);
  }
}
