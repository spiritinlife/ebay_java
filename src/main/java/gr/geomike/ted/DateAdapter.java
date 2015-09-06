package gr.geomike.ted;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Timestamp> {

    @Override
    public String marshal(Timestamp v) throws Exception {
        return new Date(v.getTime()).toString();
    }

    @Override
    public Timestamp unmarshal(String v) throws Exception {
        Date date = new SimpleDateFormat("MMM-dd-yy HH:mm:ss", Locale.ENGLISH).parse(v);

        return new Timestamp(date.getTime());
    }

}