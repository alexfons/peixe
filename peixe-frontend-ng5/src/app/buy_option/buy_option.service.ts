import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BuyOption } from '../models/buy_option.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BuyOptionService {

  constructor(private http: HttpClient) { }
  naive_bayes
  private apiUrl = 'http://localhost:8080/api/v1/buyOption';

  public getBuyOptions(): Observable<Array<BuyOption>> {
    return this.http.get<Array<BuyOption>>(this.apiUrl)
      .pipe(map((result: any) => { return result._embedded.buyOption; }));
  }

  public deleteBuyOption(buyOption: BuyOption) {
    return this.http.delete(this.apiUrl + '/' + buyOption.id);
  }

  public createBuyOption(buyOption: BuyOption) {
    return this.http.post<BuyOption>(this.apiUrl, buyOption);
  }
}
