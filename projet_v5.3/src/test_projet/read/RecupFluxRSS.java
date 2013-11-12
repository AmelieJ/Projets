
package test_projet.read;


import test_projet.model.MessageFlux;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;  // importe la classe URL existante

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import test_projet.model.ListeFlux;;  

public class RecupFluxRSS {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";

	final URL url;

	public RecupFluxRSS(String ListeFluxUrl) {
	    try {
	      this.url = new URL(ListeFluxUrl); 
	    } catch (MalformedURLException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  public ListeFlux readListeFlux() {
	    ListeFlux ListeFlux = null;
	    try {
	      boolean isListeFluxHeader = true;
	      String description = "";
	      String title = "";
	      String link = "";
	      String language = "";
	      String copyright = "";
	      String author = "";
	      String pubdate = "";
	      String guid = "";

	      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

	      InputStream in = read();
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

	      while (eventReader.hasNext()) {
	        XMLEvent event = eventReader.nextEvent();
	        if (event.isStartElement()) {
	          String localPart = event.asStartElement().getName()
	              .getLocalPart();
	          switch (localPart) {
	          case ITEM:
	            if (isListeFluxHeader) {
	              isListeFluxHeader = false;
	              ListeFlux = new ListeFlux(title, link, description, language,
	                  copyright, pubdate);
	            }
	            event = eventReader.nextEvent();
	            break;
	          case TITLE:
	            title = getCharacterData(event, eventReader);
	            break;
	            
	          case DESCRIPTION:
	            description = getCharacterData(event, eventReader);
	            break;
	          case LINK:
	            link = getCharacterData(event, eventReader);
	            break;
	          case GUID:
	            guid = getCharacterData(event, eventReader);
	            break;
	          case LANGUAGE:
	            language = getCharacterData(event, eventReader);
	            break;
	          case AUTHOR:
	            author = getCharacterData(event, eventReader);
	            break;
	          case PUB_DATE:
	            pubdate = getCharacterData(event, eventReader);
	            break;
	          case COPYRIGHT:
	            copyright = getCharacterData(event, eventReader);
	            break;
	          }
	        } else if (event.isEndElement()) {
	          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
	            MessageFlux message = new MessageFlux();
	            message.setAuthor(author);
	            message.setDescription(description);
	            message.setGuid(guid);
	            message.setLink(link);
	            message.setTitle(title);
	            message.setpubDate(pubdate);
	            message.setCopyright(copyright);
	            ListeFlux.getMessages().add(message);
	            event = eventReader.nextEvent();
	            continue;
	          }
	        }
	      }
	    } catch (XMLStreamException e) {
	      throw new RuntimeException(e);
	    }
	    return ListeFlux;
	  }

	  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
	      throws XMLStreamException {
	    String result = "";
	    event = eventReader.nextEvent();
	    if (event instanceof Characters) {
	      result = event.asCharacters().getData();
	    }
	    return result;
	  }

	  private InputStream read() {
	    try {
	      return url.openStream();
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }
}
