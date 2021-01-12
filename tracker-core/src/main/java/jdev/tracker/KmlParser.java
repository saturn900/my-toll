package jdev.tracker;

import jdev.dto.PointDTO;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KmlParser {

    private final ArrayList<PointDTO> track;
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

    public PointDTO getNext () {
        counter = ++counter;
        if (counter < track.size())
            return track.get(counter);
        else
            return null;
    }

}