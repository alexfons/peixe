import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Deal } from '../models/deal.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class DealService {

  constructor(private http: HttpClient) { }
  naive_bayes
  private apiUrl = 'http://localhost:8080/api/v1/deal';

  public getDeals(): Observable<Array<Deal>> {
    return this.http.get<Array<Deal>>(this.apiUrl)
      .pipe(map((result: any) => { return result._embedded.deal; }));
  }

  public deleteDeal(deal: Deal) {
    return this.http.delete(this.apiUrl + '/' + deal.id);
  }

  public createDeal(deal: Deal) {
    return this.http.post<Deal>(this.apiUrl, deal);
  }

  public updateDeal(deal: Deal) {
    return this.http.post<Deal>(this.apiUrl+'/associar', deal);
  }

  public processaVenda(dealId: number, buyOptinoId: number) {
    return this.http.get(this.apiUrl + "/processa/" + dealId + "/" + buyOptinoId);
  }
}
