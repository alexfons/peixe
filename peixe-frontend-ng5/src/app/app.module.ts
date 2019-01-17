import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule } from "@angular/common/http";
import { DealService } from './deal/deal.service';
import { BuyOptionService } from './buy_option/buy_option.service';

import { NgxMaskModule } from 'ngx-mask';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    NgxMaskModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [
    DealService,
    BuyOptionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
