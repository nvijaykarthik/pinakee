package org.pinakee.service;

import java.io.IOException;
import java.util.List;

import org.pinakee.domain.Transformed;
import org.pinakee.exception.XqueryNotFoundException;
import org.pinakee.mongo.entity.TransformerEntity;
import org.pinakee.mongo.repository.TransformerRepository;
import org.pinakee.transform.XqueryTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.saxon.s9api.SaxonApiException;

@Service
public class TransformerService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TransformerRepository repository;
	
	@Autowired
	XqueryTransformer xqueryTransformer;
	
	public Transformed retrieveXqueryFromDB(String xqueryName,String xml) throws SaxonApiException, IOException, XqueryNotFoundException {
		log.trace("Input {},{}",xqueryName,xml);
		log.debug("fetching xquery for the {}",xqueryName);
		TransformerEntity entity=repository.findByXqueryName(xqueryName);
		if(null==entity) {
			throw new XqueryNotFoundException("Given Xquery : "+xqueryName+" is not found");
		}
		
		log.trace("Data fetched for xquery {} is {}",xqueryName,entity.getXqueryContent());
		String xquery =entity.getXqueryContent();
		long startTime = System.currentTimeMillis();
		String result=xqueryTransformer.transform(xml,xquery);
		long endTime = System.currentTimeMillis();
		log.debug("Transformed data is : {}",result);
		String exeTime=(endTime-startTime)+"ms";
		log.debug("Execution time : {} seconds",exeTime);
		
		Transformed transformedData= new Transformed();
			transformedData.setStatus(0);
			transformedData.setContent(result);
			transformedData.setExecutionTime(exeTime);
		
		return transformedData;
	}

	public TransformerEntity addTransformerData(TransformerEntity transformerEntity) {
		log.debug("Saving the data {}",transformerEntity);
		log.trace("Saving the data xquery content {}",transformerEntity.getXqueryContent());
		return repository.save(transformerEntity);
	}

	public List<TransformerEntity> getAllTransfomers() {
		List<TransformerEntity> transformesList=repository.findAll();
		log.debug("List of transformers : {}",transformesList);
		return transformesList;
	}

	public Transformed deleteTransformer(String id) {
		log.debug("Deleting the transformer : {}",id);
		repository.delete(id);
		log.debug("Deleted Successfully :{}",id);
		Transformed success = new Transformed();
		success.setStatus(0);
		success.setContent("Successfully deleted");
		return success;
	}
}
