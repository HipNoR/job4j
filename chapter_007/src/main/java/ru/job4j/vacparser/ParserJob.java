package ru.job4j.vacparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.*;

import java.util.List;
import java.util.Properties;

/**
 * The class implements the job interface.
 * This job starts the vacancy parser from sql.ru forum.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 18.10.2018
 */
public class ParserJob implements Job {
    /**
     * Logger for info output.
     */
    private final Logger log = LogManager.getLogger(DBWorker.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Parser starting.");
        JobDetail det = jobExecutionContext.getJobDetail();
        JobDataMap jdm = det.getJobDataMap();
        Properties prop = (Properties) jdm.get("prop");
        try (DBWorker worker = new DBWorker(prop)) {
            HTMLReader reader = new HTMLReader(prop, worker.lastStartDate());
            List<Vacancy> parsed = reader.getVac();
            worker.addMultiVac(parsed);
        }
        log.info("Stop parsing.");
    }
}
