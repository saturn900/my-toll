package jdev.tracker;

import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KmlHandler extends DefaultHandler {

    private static final DecimalFormat decimalFormat = new DecimalFormat( "#.00000" );
    private static final Logger log = LoggerFactory.getLogger(KmlHandler.class);

    public ArrayList<PointDTO> getTrack () { return lpoint; }

    private final ArrayList<PointDTO> lpoint = new ArrayList<>();

    boolean bcoord = false;
    private long currentTime;

    @Override
    public void startDocument() {
        log.info("\n\nXML parsing started...");
        currentTime = System.currentTimeMillis();
        currentTime = currentTime - currentTime % 1000L;
        lpoint.clear();
    }

    @Override
    public void endDocument() {
        log.info("XML parsing ended: " + lpoint.size() + " track points processed.\n");
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attrs) {
        if (qName.equalsIgnoreCase("coordinates"   )) bcoord = true;
    }

    @Override
    public void endElement(String namespace, String localName, String qName) {
    }

    @Override
    public void characters(char[] chars, int start, int len) {
        String coordinates = String.copyValueOf(chars, start, len);

        if (bcoord) {
            String[] points = coordinates.split(" ");
            for (String w : points) {
                String[] fields = w.split(",");

                PointDTO p = new PointDTO(
                        replaceCommaByDot(formatDouble(fields[0])),
                        replaceCommaByDot(formatDouble(fields[1])),
                        "WDB2020181A712669",
                        currentTime);

                currentTime = currentTime + 1000L;

                lpoint.add(p);
            }
            bcoord = false;
        }
    }

    private String formatDouble(String arg) {
        return decimalFormat.format(Double.parseDouble(arg));
    }

    private String replaceCommaByDot (String arg) { return arg.replace(',', '.'); }
}