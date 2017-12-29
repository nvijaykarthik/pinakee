package org.pinakee.service;

import java.io.IOException;
import java.util.List;

import org.pinakee.domain.Transformed;
import org.pinakee.mongo.entity.TransformerEntity;
import org.pinakee.mongo.repository.TransformerRepository;
import org.pinakee.transform.XqueryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.saxon.s9api.SaxonApiException;

@Service
public class TransformerService {

	@Autowired
	TransformerRepository repository;
	
	@Autowired
	XqueryTransformer xqueryTransformer;
	
	public Transformed retrieveXqueryFromDB(String xqueryName,String xml) throws SaxonApiException, IOException {
		TransformerEntity entity=repository.findByXqueryName(xqueryName);
		String xquery =entity.getXqueryContent();
		String result=xqueryTransformer.transform(xml,xquery);
		
		Transformed transformedData= new Transformed();
			transformedData.setStatus(0);
			transformedData.setContent(result);
		
		return transformedData;
	}

	public TransformerEntity addTransformerData(TransformerEntity transformerEntity) {
		return repository.save(transformerEntity);
	}

	public List<TransformerEntity> getAllTransfomers() {
		return repository.findAll();
	}
}
