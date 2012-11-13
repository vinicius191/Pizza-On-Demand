/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.diversos;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VINICIUS
 */
@Component
public class CustomJSONSerialization extends XStreamJSONSerialization {

  public CustomJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer) {
    super(response, extractor, initializer);
  }

  @Override
  protected XStream getXStream() {
    XStream xstream = super.getXStream();

    xstream.registerConverter(new CollectionConverter(xstream.getMapper()) {
      @Override
      @SuppressWarnings("rawtypes")
      public boolean canConvert(Class type) {
        return Collection.class.isAssignableFrom(type);
      }
    });

    return xstream;
  }

}
