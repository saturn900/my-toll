package jdev.tracker;

import jdev.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class KmlHandler extends DefaultHandler {

    private static final DecimalFormat decimalFormat = new DecimalFormat( "#.00000" );
    private static final Logger log = LoggerFactory.getLogger(KmlHandler.class);
    private StringBuilder coordinates = new StringBuilder(); // билдер для приема кусков строки с координатами

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
        //к вопрос у 3. На данном этапе считывается 734 точки (после костыля), и чтобы проверить что они все разные выводим содержимое в лог и смотрим, там разные точки
        //к вопросу 1 что парсер считывает 2 трек-поинта - подтвердилось, заменил пробелы на переводы строк и тогда стало считывать по 2 поинта.
        //Смотрите мои замечания про то как надо пользоваться Sax-парсером в файле KmlHandler.
        Set<PointDTO> set = new HashSet<>(lpoint);
        log.info("set size {}", set.size());
        // размер набора и размер списка одинаковый, а так как свойством набора является то что в нем только уникальные обекты то значит все объекты в списке - уникальны
        // так что утверждение в вопросе #3 что весь список в итоге состоит из одинаковых - не подтверждается
        assert set.size() == lpoint.size();
        log.info(set.toString());
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attrs) {
        if (qName.equalsIgnoreCase("coordinates"   )) bcoord = true;
    }

    @Override
    public void endElement(String namespace, String localName, String qName) {
        if (qName.equalsIgnoreCase("coordinates"   )) {
            //блок преобразования в объекты точек перенесен сюда
            String[] points = coordinates.toString().split("\\n");
            for (String w : points) {
                if (w.trim().isEmpty()) { // может влезть пустая строка
                    continue;
                }
                String[] fields = w.split(",");
                currentTime = currentTime + 1000L;
                /*делаю инлайн чтобы создавать точку сразу при добавлении в список*/
                lpoint.add(new PointDTO(
                        replaceCommaByDot(formatDouble(fields[0])),
                        replaceCommaByDot(formatDouble(fields[1])),
                        "WDB2020181A712669",
                        currentTime));
            }
            //помечаем, что больше склеивать строку не надо
            bcoord = false;
        }
    }

    @Override
    public void characters(char[] chars, int start, int len) {
        //причина считывания только 2 поинтов в том что этот метод вызывается несколько раз и каждый раз возвращает какуюто часть строки
        //т.е. вся строка разбита на чанки - куски.
        //чтобы проверить - запустите приложение в режиме дебаг и поставьте точку останова и вы увидите что этот метод вызывается несколько раз
        //значит надо склеивать эти чанки в строку, а конечную обработку - преобразование в список трек-поинтов перенести в метод эндЭлемент

        if(bcoord) { // признак что элемент с координатами начался
            //склеиваем чанки в виде стрингбилдера для высокой эффективности
            coordinates.append(String.copyValueOf(chars, start, len)).toString();
        }

        /*if (bcoord) {
            String[] points = coordinates.split("\\n");
            for (String w : points) {
                String[] fields = w.split(",");

                currentTime = currentTime + 1000L;
                lpoint.add(new PointDTO(
                        replaceCommaByDot(formatDouble(fields[0])),
                        replaceCommaByDot(formatDouble(fields[1])),
                        "WDB2020181A712669",
                        currentTime));
            }
            bcoord = false;
        }*/
    }

    private String formatDouble(String arg) {
        return decimalFormat.format(Double.parseDouble(arg));
    }

    private String replaceCommaByDot (String arg) { return arg.replace(',', '.'); }
}