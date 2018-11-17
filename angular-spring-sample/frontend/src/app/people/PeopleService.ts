import { Injectable } from '@angular/core';
import { ApiService } from './ApiService';

@Injectable()
export class PeopleService {

  constructor(private apiService: ApiService) {
  }

  getPeople() {
    return this.apiService.get('/people');
  }

}
