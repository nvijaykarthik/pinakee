import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { TransformerComponent } from './transformer/transformer.component';
import { FilterPipe} from './filter.pipe';
import { TransformerService } from './transformer/transformer.service';
import { HttpClientModule }    from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    TransformerComponent,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [TransformerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
