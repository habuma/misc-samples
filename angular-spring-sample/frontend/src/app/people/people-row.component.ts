import { Component, OnInit, Input } from '@angular/core';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'tr.people-row',
  templateUrl: 'people-row.component.html'
})

export class PeopleRowComponent implements OnInit {

  @Input() person;

  constructor() { }

  ngOnInit() { }
}
