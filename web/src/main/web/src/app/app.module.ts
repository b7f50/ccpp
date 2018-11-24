import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule, MatInputModule, MatCardModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule, MatSelectModule, MatRadioModule, MatTableModule, MatPaginatorModule, MatSortModule, MatProgressSpinnerModule } from "@angular/material";

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CompanyDetailComponent } from './company-detail/company-detail.component';

import { HttpClientModule } from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';
import { CompanyListComponent } from './company-list/company-list.component';


const appRoutes: Routes = [
  {
    path: 'companies/:id',
    component: CompanyDetailComponent,
    data: { title: 'Company Detail' }
  },
  {
    path: 'companies',
    component: CompanyListComponent,
    data: { title: 'Companies List' }
  },
  { path: '',
    redirectTo: '/companies',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    CompanyDetailComponent,
    CompanyListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    FormsModule,
    ReactiveFormsModule,

    MatInputModule,
    MatCardModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatProgressSpinnerModule,

    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
