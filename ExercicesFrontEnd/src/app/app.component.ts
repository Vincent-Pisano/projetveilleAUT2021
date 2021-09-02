import { Component, NgZone } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { environment as env } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ExercicesFrontEnd';

  endSearch: boolean = false;
  showProgressBar:boolean = false;
  results: string[] = [];
  resultsObserver: Observable<String[]> = new Observable<String[]>();

  constructor(private ngZone: NgZone) {

  }

  search(event: any) {
    event.preventDefault();
    this.showProgressBar = true;
    this.endSearch = false;
    this.results = [];
    this.resultsObserver = this.createEventSourceObserver();
  }

  createEventSourceObserver(): Observable<string[]> {
    return new Observable<string[]>((observer: Observer<string[]>) => {
      const source = new EventSource(env.apiUrl)

      source.onmessage = (event) => {
        console.log(event.data);
        this.results.push(event.data);
        this.results.sort();
        //On utilise NgZone pour notifier 'results' des updates à l'opération async
        //En gros = on render de nouveau l'HTML
        //le ... | async permet de se subscribe à l'observer
        this.ngZone.run(() => observer.next(this.results))
      };

      //quand ça se finit
      source.onopen = (event) => {
        if (this.endSearch) {
          source.close();
          this.ngZone.run(() => {
            observer.complete();
            this.showProgressBar = false;
          });
        }
        this.endSearch = true
      };
    })
  }
}
