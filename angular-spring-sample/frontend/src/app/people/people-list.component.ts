import { Component, OnInit, Injectable } from '@angular/core';
import { PeopleService } from './PeopleService';
// import { PeopleRowComponent } from './people-row.component';

const PEOPLE = [
  {
    'id': 1,
    'firstName': 'Craig',
    'lastName': 'Walls'
  }
];


@Component({
  // tslint:disable-next-line:component-selector
  selector: 'people-list',
  templateUrl: 'people-list.component.html'
})
@Injectable()
export class PeopleListComponent implements OnInit {

  people = PEOPLE;

  constructor(private peopleService: PeopleService) {}

  ngOnInit() {
    this.peopleService.getPeople()
      .subscribe(p => this.people = p.json());
  }
}
