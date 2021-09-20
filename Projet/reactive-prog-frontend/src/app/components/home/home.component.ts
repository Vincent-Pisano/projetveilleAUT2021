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
  currentClickedItem:number = 0;

  constructor(private ngZone: NgZone, private router:Router) { }

  ngOnInit(): void {
    this.endSearchItem = false;
    this.endSearchComment = false;
    this.customer = history.state;
    console.log(this.customer);
    this.itemlistObserver = this.getItems();
    
  }

  getItems(): Observable<Array<Item>> {
    return new Observable<Array<Item>>((observer: Observer<Array<Item>>) => {
      const source = new EventSource('http://localhost:8080/getItems')

      //quand ça se finit
      source.onopen = () => {
        if (this.endSearchItem) {
          source.close();
          this.ngZone.run(() => {
            observer.complete();
          });
        }
        this.endSearchItem = true
      };

      source.onmessage = (event) => {
        this.item = JSON.parse(event.data)
        this.itemlist.push(this.item);
        this.itemlist.sort();
        //On utilise NgZone pour notifier 'results' des updates à l'opération async
        //En gros = on render de nouveau l'HTML
        //le ... | async permet de se subscribe à l'observer
        this.ngZone.run(() => observer.next(this.itemlist))
      };
    })
  }

  /*getComment(idItem:number | undefined) {
    this.service.getComment(idItem).subscribe(() => {
      (data: any) => {
        console.log(data)
      }
    }, (error) => {
      console.log(error)
    });
  }*/

  getComment(idItem:any) {  
    this.currentClickedItem = idItem
    this.commentlistObserver = this.getCommentObservable(idItem)
  }

  getCommentObservable(idItem:number | undefined): Observable<Array<Comment>> {
    this.endSearchComment = false
    return new Observable<Array<Comment>>((observer: Observer<Array<Comment>>) => {

      const source = new EventSource('http://localhost:8080/getComments?idItem=' + idItem)

      //quand ça se finit
      source.onopen = () => {
        if (this.endSearchComment) {
          source.close();
          this.ngZone.run(() => {
            observer.complete();
          });
        }
        this.endSearchComment = true
      };

      source.onmessage = (event) => {
        this.comment = JSON.parse(event.data)
        if (this.commentlist.find(c => c.idComment == this.comment.idComment) == undefined) {
          this.commentlist.push(this.comment);
          this.commentlist.sort();
        }
        this.ngZone.run(() => observer.next(this.commentlist))
      };
    })
  }

}
