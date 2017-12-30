package org.pinakee.transform;

import java.io.IOException;
import java.io.StringReader;

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
	
	public String transform(String xml,String xquery) throws SaxonApiException, IOException {
		
		// the Saxon processor object
		Processor saxon = new Processor(false);
		log.debug("the Saxon processor object created");
		// compile the query
		XQueryCompiler compiler = saxon.newXQueryCompiler();
		XQueryExecutable exec = compiler.compile(xquery);
		log.debug("Xquery Compiled");

		// parse the string as a document node
		DocumentBuilder builder = saxon.newDocumentBuilder();
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
