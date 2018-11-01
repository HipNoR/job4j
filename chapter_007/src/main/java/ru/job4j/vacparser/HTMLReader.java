package ru.job4j.vacparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The class scans the forum skl.ru, finds jobs java developer.
 * When you first start it finds all jobs from the beginning of the year.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 19.10.2018
 */
public class HTMLReader {
    /**
     * Logger for info output.
     */
    private final Logger log = LogManager.getLogger(HTMLReader.class);

    /**
     * Basic url for parsing.
     */
    private String url;

    /**
     * List of all new vacancies.
     */
    private List<Vacancy> vac = new ArrayList<>();

    /**
     * Pattern for date formatting from sql.ru.
     */
    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yy");
    /**
     * Start date of the search.
     * Last start date or beginning of year.
     */
    private Date startDate;

    public HTMLReader(Properties prop, Date date) {
        url = prop.getProperty("jsob.url");
        setStartDate(date);
    }

    /**
     * Method searches vacancies from url.
     * At start checks number of pages.
     * After that parses every page until date of last message in the topic going after start date.
     * Also, if the topic matches the date, it analyzes the first message by the publication date and the Java programmer's filter.
     * If all conditions are met, add to the job list.
     */
    public void vacSearch() {
        boolean validate = true;
        String pageUrl;
        int pages = 1;
        try {
            pages = getPages(url);
        } catch (IOException e) {
            log.error("ERROR", e);
        }
        log.info(String.format("Total pages to parse: %s", pages));
        for (int page = 1; page <= pages; page++) {
            pageUrl = String.format("%s/%s", url, page);
            log.info(String.format("Parsing page %s of %s pages.", page, pages));
            Document doc = getDoc(pageUrl);
            Elements topicsTable = doc.getElementsByAttributeValue("class", "forumTable").get(0).getElementsByTag("tr");
            for (Element topic : topicsTable) {
                String title = topic.text();
                if (!title.contains("Тема Автор") && !title.contains("Важно:")) {
                    Date lastDate = getDate(topic.child(5).text());
                    if (checkDate(lastDate)) {
                        Elements tobicBlock = topic.getElementsByAttributeValue("class", "postslisttopic");
                        String topicUrl = tobicBlock.get(0).child(0).attr("href");
                        Document vacTopic = getDoc(topicUrl);
                        Elements messages = vacTopic.getElementsByAttributeValue("class", "msgTable");
                        Elements msgBlocks = messages.first().getElementsByTag("tr");
                        Date postDate = getDate(msgBlocks.last().text());
                        if (checkDate(postDate)) {
                            String description = msgBlocks.get(1).getElementsByAttributeValue("class", "msgBody").last().text();
                            if (javaFilter(description)) {
                                Vacancy vacancy = new Vacancy(postDate, symbolsReplace(title), topicUrl, symbolsReplace(description));
                                vac.add(vacancy);
                            }
                        }
                    } else {
                        validate = false;
                    }
                }
            }
            if (!validate) {
                log.info("Parsing is over.");
                break;
            }
        }
        log.info(String.format("Total vacancies found: %s.", vac.size()));
    }

    public List<Vacancy> getVac() {
        vacSearch();
        return vac;
    }

    /**
     * Sets the start date of the search.
     * @param date for starting.
     */
    public void setStartDate(Date date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            startDate = calendar.getTime();
        } else {
            startDate = date;
        }
    }

    /**
     * Creates a document with a URL.
     * @param url of html document.
     * @return document is based on the html page.
     */
    private Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("ERROR", e);
        }
        return doc;
    }

    /**
     * Method return date from string.
     * Date must be like <date, time or another info>
     *     for example "сегодня, 10:50" or "10 ноя 18, 11:30".
     * @param postDate input string.
     * @return instance of Date.
     */
    private Date getDate(String postDate) {
        Date date = null;
        String splitted = postDate.split(",")[0];
        Calendar cal = Calendar.getInstance();
        if (splitted.equals("сегодня")) {
            date = new Date();
        } else if (splitted.equals("вчера")) {
            cal.add(Calendar.DATE, -1);
            date = cal.getTime();
        } else {
            try {
                date = format.parse(splitted);
            } catch (ParseException e) {
                log.error("ERROR", e);
            }
        }
        return date;
    }

    /**
     * Method checks that the date is after the last start date or the beginning of the year.
     * @param postDate date for checking.
     * @return true if after.
     */
    private boolean checkDate(Date postDate) {
        return postDate.after(startDate);
    }

    /**
     * Method searches for the number of all pages in the section.
     * Only for sql.ru
     * @param url of page.
     * @return number of pages.
     * @throws IOException if can't get document.
     */
    private int getPages(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements sortOptions = doc.getElementsContainingOwnText("Страницы:");
        String pages = sortOptions.text().trim();
        String[] p = pages.split("\\s");
        return Integer.parseInt(p[p.length - 1]);
    }

    /**
     * The method replaces all ' and ; chars by spaces to protect SQL queries against errors.
     * @param text to convert.
     * @return fixed string without ' and ;.
     */
    private String symbolsReplace(String text) {
        return text.replaceAll("[';]", " ");
    }

    /**
     * Method filters only Java-developers jobs.
     * @param text of vacancy to check.
     * @return true if appropriate else false.
     */
    private boolean javaFilter(String text) {
        return Pattern.compile("\\b[Jj][Aa][Vv][Aa]\\b[^Jj]").matcher(text).find();
    }
}
