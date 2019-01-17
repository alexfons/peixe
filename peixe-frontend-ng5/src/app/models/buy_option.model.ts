export class BuyOption {
  constructor(
    public id: string = '',
    public title: string = '',
    public normalPrice: string = '',
    public salePrice: string = '',
    public percentagelDiscount: string = '',
    public quantityCupom: string = '',
    public startDate: string = '',
    public endDate: string = '',
    public _links: Array<String> = new Array<String>() 
  ) { }
}