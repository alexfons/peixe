import { BuyOption } from '../models/buy_option.model';

export class Deal {
  constructor(
    public id: string = '',
    public title: string = '',
    public text: string = '',
    public createDate: string = '',
    public publishDate: string = '',
    public endDate: string = '',
    public url: string = '',
    public totalSold: string = '',
    public type: string = '',
    public buyOptions: Array<BuyOption> = new Array<BuyOption>(),
    public buyOptionsLinks: Array<String> = new Array<String>(),
    public _links: Array<String> = new Array<String>()
  ) { }
}