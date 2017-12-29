package org.pinakee.mongo.repository;

import org.pinakee.mongo.entity.TransformerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransformerRepository extends MongoRepository<TransformerEntity, String> {

	public TransformerEntity findByXqueryName(String xqueryName);
}
