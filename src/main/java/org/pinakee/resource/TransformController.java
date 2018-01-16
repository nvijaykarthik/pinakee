package org.pinakee.resource;

import java.io.IOException;
import java.util.List;

import org.pinakee.domain.TranformerInput;
import org.pinakee.domain.Transformed;
import org.pinakee.exception.XqueryNotFoundException;
import org.pinakee.mongo.entity.TransformerEntity;
import org.pinakee.service.TransformerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.sf.saxon.s9api.SaxonApiException;

@RestController
@RequestMapping(path="/rest/transform")
public class TransformController {

	@Autowired
	TransformerService service;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Transformed transform(@RequestBody TranformerInput transformer) throws SaxonApiException, IOException, XqueryNotFoundException {
		log.debug("Input :{}",transformer);
		log.trace("input xml \n {}",transformer.getXml());
		return service.retrieveXqueryFromDB(transformer.getXqueryName(), transformer.getXml(),transformer.getParameters());
	}
	
	@RequestMapping(path="/newTransformer",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public TransformerEntity transformer(@RequestBody TransformerEntity transformer) throws SaxonApiException, IOException {
		log.debug("Input :{}",transformer);
		log.trace("input xml \n {}",transformer.getXqueryContent());
		return service.addTransformerData(transformer);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TransformerEntity> transformer() {
		return service.getAllTransfomers();
	}
	
	@RequestMapping(method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Transformed transformer(@RequestParam String id) {
		log.debug("Input :{}",id);
		return service.deleteTransformer(id);
	}
}
