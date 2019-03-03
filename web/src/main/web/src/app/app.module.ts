import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';

import {CompanyEditComponent} from './company-edit/company-edit.component';
import {CompanyListComponent} from './company-list/company-list.component';
import {CompanyDetailComponent} from './company-detail/company-detail.component';


const appRoutes: Routes = [
  {
    path: 'company-edit/:id',
    component: CompanyEditComponent,
    data: {title: 'Company Edit'}
  },
  {
    path: 'company-detail/:id',
    component: CompanyDetailComponent,
    data: {title: 'Company Detail'}
  },
  {
    path: 'companies',
    component: CompanyListComponent,
    data: {title: 'Companies List'}
  },
  {
    path: '',
    redirectTo: '/companies',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    CompanyEditComponent,
    CompanyListComponent,
    CompanyDetailComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    FormsModule,
    ReactiveFormsModule,

    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
