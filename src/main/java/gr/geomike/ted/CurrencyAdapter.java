package gr.geomike.ted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CurrencyAdapter extends XmlAdapter<String, Float> {

    @Override
    public String marshal(Float f) throws Exception {
        return "$" + f.toString();
    }

    @Override
    public Float unmarshal(String v) throws Exception {
        return Float.parseFloat(v.replaceAll("[^\\d.]+", ""));
    }

}