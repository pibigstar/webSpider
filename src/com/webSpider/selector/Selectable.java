package com.webSpider.selector;

import java.util.List;


public interface Selectable {

    public Selectable xpath(String xpath);

    public Selectable $(String selector);

  
    public Selectable $(String selector, String attrName);

  
    public Selectable css(String selector);

    public Selectable css(String selector, String attrName);

    public Selectable smartContent();

  
    public Selectable links();

   
    public Selectable regex(String regex);

   
    public Selectable regex(String regex, int group);

   
    public Selectable replace(String regex, String replacement);

  
    public String toString();

    public String text();
   
    public String get();

   
    public boolean match();

   
    public List<String> all();

   
    public Selectable jsonPath(String jsonPath);

    
    public Selectable select(Selector selector);

  
    public Selectable selectList(Selector selector);

    public List<Selectable> nodes();
}
