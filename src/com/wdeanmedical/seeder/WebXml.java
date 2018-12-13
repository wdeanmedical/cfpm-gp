package com.wdeanmedical.seeder;

import static org.joox.JOOX.$;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.function.Consumer;

import org.joox.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WebXml {
  private Document document;
  private static WebXml _instance;
  private HashMap<String, String> contextParams = new HashMap<String, String>();

  public static WebXml instance() throws Exception {
    if(_instance==null) {
      FileInputStream xmlFile = new FileInputStream("web/WEB-INF/web.xml");
      Document document = $(xmlFile).document();
      _instance = new WebXml(document);
    }
    return _instance;
  }

  private WebXml(Document document) {
    this.document=document;
    this.loadContextParams();
  }

  public void loadContextParams(){
    Match params=$(document).find("context-param");
    params.forEach(new Consumer<Element>() {
      @Override
      public void accept(Element match) {
        String name=match.getElementsByTagName("param-name").item(0).getTextContent();
        String value=match.getElementsByTagName("param-value").item(0).getTextContent();
        contextParams.put(name, value);
      }
    });
  }
  public String getParam(String name) {
    return contextParams.get(name);
  }

}
