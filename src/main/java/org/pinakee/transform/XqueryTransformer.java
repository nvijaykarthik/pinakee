package org.pinakee.transform;

import java.io.IOException;
import java.io.StringReader;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;

@Component("xqueryTransformer")
public class XqueryTransformer {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	Processor saxon;
	XQueryCompiler compiler;
	XQueryExecutable exec;
	DocumentBuilder builder;
	
	@PostConstruct
	private void init() {
		saxon = new Processor(false);
		compiler = saxon.newXQueryCompiler();
		builder = saxon.newDocumentBuilder();
		log.debug("The Saxon processor object created");
	}
	
	public String transform(String xml,String xquery) throws SaxonApiException, IOException {
		XQueryExecutable exec = compiler.compile(xquery);
		log.debug("Xquery Compiled");
		Source src = new StreamSource(new StringReader(xml));
		XdmNode doc = builder.build(src);	
		log.debug("XdmNode is created using the input XML ");
		// instantiate the query, bind the input and evaluate
		XQueryEvaluator query = exec.load();
		query.setContextItem(doc);
		XdmValue result = query.evaluate();
		log.debug("Xquery is evaluated and result is generated ");
		return result.toString();
	}
}
