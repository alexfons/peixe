import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Deal } from '../models/deal.model';
import { DealService } from '../deal/deal.service';

import { BuyOption } from '../models/buy_option.model';
import { BuyOptionService } from '../buy_option/buy_option.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  deals: Deal[];
  dealModel: Deal = new Deal();
  showNewDeal: Boolean = false;

  buyOptions: BuyOption[];
  buyOptionModel: BuyOption = new BuyOption();
  showNewBuyOption: Boolean = false;

  types: Array<string> = ['local', 'product', 'travel'];

  term: string;

  constructor(
    private http: HttpClient,
    private dealService: DealService,
    private buyOptionService: BuyOptionService
  ) { }

  ngOnInit() {
    this.dealService.getDeals()
      .subscribe(data => {
        this.deals = data;
        this.populaAssociacao(null);
      });
    this.buyOptionService.getBuyOptions()
      .subscribe(data => {
        this.buyOptions = data;
      });
  }

  // This method associate to New Button.
  onNewDeal() {
    // Initiate new deal.
    this.dealModel = new Deal();
    // display deal entry section.
    this.showNewDeal = true;
  }

  // This method associate to Save Button.
  onSaveDeal() {
    // Push deal model object into deal list.
    this.dealModel = this.removeEmptyFields(this.dealModel);

    this.dealService.createDeal(this.dealModel).subscribe(data => {
      this.populaAssociacao(data);
      this.deals.unshift(
        data
      );
    });
    // Hide deal entry section.
    this.showNewDeal = false;
  }

  // This method associate toCancel Button.
  onCancelDeal() {
    // Hide deal entry section.
    this.showNewDeal = false;
  }


  // This method associate to New Button.
  onNewBuyOption() {
    // Initiate new buyOption.
    this.buyOptionModel = new BuyOption();
    // display buyOption entry section.
    this.showNewBuyOption = true;
  }

  // This method associate to Save Button.
  onSaveBuyOption() {
    // Push buyOption model object into buyOption list.
    this.buyOptionModel = this.removeEmptyFields(this.buyOptionModel);
    this.buyOptionService.createBuyOption(this.buyOptionModel).subscribe(data => {
      this.buyOptions.unshift(
        data
      );
    });
    // Hide buyOption entry section.
    this.showNewBuyOption = false;
  }

  private removeEmptyFields(obj) {
    for (var key in obj) {
      if (obj[key] === null || obj[key] === "") {
        delete obj[key];
      }
    }
    return obj;
  }

  // This method associate toCancel Button.
  onCancelBuyOption() {
    // Hide buyOption entry section.
    this.showNewBuyOption = false;
  }

  // This method associate Deal to BuyOption.
  onAssociate() {
    this.dealModel = this.removeEmptyFields(this.dealModel);
    this.buyOptionModel = this.removeEmptyFields(this.buyOptionModel);

    if (!this.dealModel.buyOptions) {
      this.dealModel.buyOptions = new Array<BuyOption>();
    }
    if (!this.dealModel.buyOptionsLinks) {
      this.dealModel.buyOptionsLinks = new Array<String>();
    }

    const index: BuyOption[] = this.dealModel.buyOptions.filter((buyOption) => buyOption.id === this.buyOptionModel.id);
    if (index.length === 0) {
      this.dealModel.buyOptions.push(this.buyOptionModel);
      this.dealModel.buyOptionsLinks.push(this.buyOptionModel._links['self']['href']);
      this.dealService.updateDeal(this.dealModel).subscribe(data =>{
        this.ngOnInit();
      });
      
    }
  }

  populaAssociacao(deal: Deal) {
    if (deal) {
      this.getBuyOptions(deal)
        .subscribe(data => {
          deal.buyOptions = data ? data : new Array<BuyOption>();
        });
    } else {
      this.deals.forEach(deal => {
        this.populaAssociacao(deal);
      });
    }
  }

  getBuyOptions(data: Deal): Observable<Array<BuyOption>> {
    if (data._links['buyOptions'])
      return this.http.get<Array<BuyOption>>(data._links['buyOptions']['href'])
        .pipe(map((result: any) => { return result._embedded.buyOption; }));
  }

  onBuyBuyOption(dealId: number, buyOptinoId: number) {
    this.dealService.processaVenda(dealId, buyOptinoId).subscribe(data =>{
      this.ngOnInit();
    });
  }
}
