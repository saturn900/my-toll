package jdev.tracker;

import jdev.dto.Point;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class KmlParser {

    private List<Point> track;
    private int counter;

//    @PostConstruct
//    public void setTrack (/* From *.kml file */) {
    public KmlParser () {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        KmlHandler handler = new KmlHandler();

        try {
            SAXParser saxParser = parserFactory.newSAXParser();
            File file = new File("tracker-core/src/main/resources/20150731_Peschanaya.kml");
            saxParser.parse(file, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        track = handler.getTrack();
        counter = -1;
    }

    public Point getNext () {
        counter = ++counter;
        return track.get(counter);
    }

}