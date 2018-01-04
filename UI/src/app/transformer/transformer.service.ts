import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import { Transformer } from './TranformerEntity';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class TransformerService {

  constructor( private http: HttpClient) { }
  private url = 'http://localhost:8080/rest/transform';
  private saveURl='http://localhost:8080/rest/transform/newTransformer';

  getTransformers():Observable<Transformer[]>{
    return this.http.get<Transformer[]>(this.url);
  }

  saveTransformer(transformer:Transformer):Observable<Transformer>{
    return this.http.post<Transformer>(this.saveURl,transformer,httpOptions);
  }

  deleteTransformer(id:string):Observable<string>{
    return this.http.delete<string>(this.url.concat("?id=").concat(id),httpOptions);
  }
}
