import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { ItemService } from 'src/app/service/item.service';
import { Customer } from 'src/models/customer';
import { Item } from 'src/models/item';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  customer:Customer = new Customer();
  item:Item = new Item;
  itemlistObserver: Observable<Array<Item>> = new Observable<Array<Item>>();
  itemlist: Array<Item> = Array<Item>();
  endSearch: boolean = false;

  constructor(private service: ItemService, private ngZone: NgZone, private router:Router) { }

  ngOnInit(): void {
    this.endSearch = false;
    this.customer = history.state;
    console.log(this.customer);
    this.itemlistObserver = this.createEventSourceObserver();
  }

  createEventSourceObserver(): Observable<Array<Item>> {
    return new Observable<Array<Item>>((observer: Observer<Array<Item>>) => {
      const source = new EventSource('http://localhost:8080/getItems')

      source.onmessage = (event) => {
        this.itemlist.push(event.data);
        this.itemlist.sort();
        this.item = event.data;
        console.log(this.item.name)
        //On utilise NgZone pour notifier 'results' des updates à l'opération async
        //En gros = on render de nouveau l'HTML
        //le ... | async permet de se subscribe à l'observer
        this.ngZone.run(() => observer.next(this.itemlist))
      };

      //quand ça se finit
      source.onopen = (event) => {
        if (this.endSearch) {
          source.close();
          this.ngZone.run(() => {
            observer.complete();
          });
        }
        this.endSearch = true
      };
    })
  }
}
