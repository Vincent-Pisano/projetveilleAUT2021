import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { ItemService } from 'src/app/service/item.service';
import { Customer } from 'src/models/customer';
import { Comment } from 'src/models/comment';
import { Item } from 'src/models/item';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  customer:Customer = new Customer();
  item:Item = new Item;
  comment:Comment = new Comment;
  
  itemlistObserver: Observable<Array<Item>> = new Observable<Array<Item>>();
  itemlist: Array<Item> = Array<Item>();
  endSearchItem: boolean = false;

  commentlistObserver: Observable<Array<Comment>> = new Observable<Array<Comment>>();
  commentlist: Array<Comment> = Array<Comment>();
  endSearchComment: boolean = false;

  constructor(private ngZone: NgZone, private router:Router) { }

  ngOnInit(): void {
    this.endSearchItem = false;
    this.endSearchComment = false;
    this.customer = history.state;
    console.log(this.customer);
    this.itemlistObserver = this.getItems();
    this.commentlistObserver = this.getComment(95);
  }

  getItems(): Observable<Array<Item>> {
    return new Observable<Array<Item>>((observer: Observer<Array<Item>>) => {
      const source = new EventSource('http://localhost:8080/getItems')

      source.onmessage = (event) => {
        this.item = JSON.parse(event.data)
        this.itemlist.push(this.item);
        this.itemlist.sort();
        
        //On utilise NgZone pour notifier 'results' des updates à l'opération async
        //En gros = on render de nouveau l'HTML
        //le ... | async permet de se subscribe à l'observer
        this.ngZone.run(() => observer.next(this.itemlist))
      };

      //quand ça se finit
      source.onopen = (event) => {
        if (this.endSearchItem) {
          source.close();
          this.ngZone.run(() => {
            observer.complete();
          });
        }
        this.endSearchItem = true
      };
    })
  }

  getComment(idItem:number | undefined): Observable<Array<Comment>> {
    if (idItem == undefined)
      return new Observable<Array<Comment>>();
    return new Observable<Array<Comment>>((observer: Observer<Array<Comment>>) => {
      const source = new EventSource('http://localhost:8080/getComments?idItem=' + idItem)

      source.onmessage = (event) => {
        this.comment = JSON.parse(event.data)
        console.log(this.comment)
        this.commentlist.push(this.comment);
        this.commentlist.sort();

        this.ngZone.run(() => observer.next(this.commentlist))
      };

      //quand ça se finit
      source.onopen = (event) => {
        if (this.endSearchComment) {
          source.close();
          this.ngZone.run(() => {
            observer.complete();
          });
        }
        this.endSearchComment = true
      };
    })
  }

}
