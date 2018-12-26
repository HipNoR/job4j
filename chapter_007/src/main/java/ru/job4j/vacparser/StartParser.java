package ru.job4j.vacparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Main class of the parser application.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 24.10.2018
 */
public class StartParser {
    /**
     * Logger for info output.
     */
    private final static Logger LOGGER = LogManager.getLogger(DBWorker.class);


    public void task(String prop) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = newJob(ParserJob.class).withIdentity("job1").build();
        JobDataMap dm = jobDetail.getJobDataMap();
        Properties properties = Configurator.getProperties(prop);
        dm.put("prop", properties);
        String cron = properties.getProperty("cron.time");
        Trigger trigger = newTrigger().withIdentity("trigger1").withSchedule(cronSchedule(cron))
                .forJob("job1").build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }


    public static void main(String[] args) {
        StartParser parser = new StartParser();
        try {
            parser.task("app.properties");
        } catch (SchedulerException e) {
            LOGGER.error("ERROR", e);
        }
    }
}
