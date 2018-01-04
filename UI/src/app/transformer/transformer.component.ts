import { Component, OnInit } from '@angular/core';

import { Transformer } from './TranformerEntity';
import { TransformerService } from './transformer.service';


@Component({
  selector: 'app-transformer',
  templateUrl: './transformer.component.html',
  styleUrls: ['./transformer.component.css']
})
export class TransformerComponent implements OnInit {

  title_logo:String;
  transformers:Transformer[];
  selectedTransformer: Transformer;

  constructor(private transformerService: TransformerService) {
   }

  ngOnInit() {
    this.title_logo='./assets/LOGO.png';
    this.getTransformers();
  }

  addnew(){
    this.selectedTransformer= new Transformer();
  }
  onSelect=(tfn: Transformer)=>{
    this.selectedTransformer=tfn;
  }

  getTransformers():void{
    this.transformerService.getTransformers()
      .subscribe(transformers=>this.transformers=transformers);
  }

  save():void{
    
    if(!this.selectedTransformer.xqueryName){
      alert("Please enter the xquery name");
      return;
    }
    this.transformerService.saveTransformer(this.selectedTransformer)
    .subscribe(
      selTransformer=>{
        this.selectedTransformer=selTransformer;
        alert("Message Saved Successfully");
        this.getTransformers();
  });
    
  }

  delete(id:string):void{
    this.transformerService.deleteTransformer(id)
    .subscribe(()=>{
      alert("Successfully Deleted");
      this.getTransformers();
    });
  }
}
