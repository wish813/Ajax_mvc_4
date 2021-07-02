package com.ict.model;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLCommand {
	
	public String exec(HttpServletRequest request, HttpServletResponse response) {
	String output = "";
	 try {
		 String url = "https://www.kma.go.kr/XML/weather/sfc_web_map.xml";
		 DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
		 DocumentBuilder db = dbFact.newDocumentBuilder();
		Document document = db.parse(url);
		
		document.getDocumentElement().normalize();
		System.out.println("root : "+ document.getDocumentElement().getNodeName());
		
		NodeList local= document.getElementsByTagName("local");
		
		// xml -> string
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer transformer = tff.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter sw = new StringWriter();
		transformer.transform(new DOMSource(document),new StreamResult(sw));
		 output ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>";
		
		output +=sw.getBuffer().toString();
		
		System.out.println("output : "+output);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return output;
	}
}
