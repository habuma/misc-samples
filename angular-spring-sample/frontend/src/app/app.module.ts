import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { PeopleListComponent } from './people/people-list.component';
import { PeopleRowComponent } from './people/people-row.component';
import { ApiService } from './people/ApiService';
import { PeopleService } from './people/PeopleService';
import { UppercasePipe } from './people/UppercasePipe';


@NgModule({
  declarations: [
    AppComponent,   // declares the AppComponent component, as defined in app.component.ts
    PeopleListComponent,
    PeopleRowComponent,
    UppercasePipe,
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [
    ApiService, PeopleService
  ],
  bootstrap: [AppComponent]   // set the "main" component
})
export class AppModule { }
