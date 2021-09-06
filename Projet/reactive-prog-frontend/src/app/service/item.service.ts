import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { Item } from 'src/models/item';
import { GenericService } from './genericService';

@Injectable({
  providedIn: 'root'
})
export class ItemService extends GenericService<Item, Number> {
  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080');
  }

  getAll(): Observable<Array<Item>> {
    return new Observable<Array<Item>>((observer: Observer<Array<Item>>) => {
      const source = new EventSource(this.url + "/getItems")

      source.onmessage = (event) => {
        console.log(event.data);
      };
    })
  }
}
