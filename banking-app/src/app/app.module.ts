//imports angular dependencies needed and components 
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientTransactionsComponent } from './client-transactions/client-transactions.component';

import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user.service';

//uses the @ngmodule annotation to define a module by passing an object 
@NgModule({
  declarations: [  // the object has declarations for listing any component or directive used in the application
    AppComponent,
    ClientTransactionsComponent
  ],
  imports: [ // other imports used in the application
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent] // the first component to use to boostrap the application
})
export class AppModule { } //exports an empty class
