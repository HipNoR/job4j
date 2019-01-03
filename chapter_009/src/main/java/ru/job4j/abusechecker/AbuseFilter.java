package ru.job4j.abusechecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Collectors;

/**
 * Class stream filter character.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 29.12.2018
 */
public class AbuseFilter {
    private static final Logger LOG = LogManager.getLogger(AbuseFilter.class.getName());


    /**
     * The method filters the input stream according to the words specified in the list of invalid words.
     * Words not deleted are sent to the output stream.
     * Important! A space is added to the end of each sent word.
     * @param in input stream.
     * @param out output stream.
     * @param abuse array of filterable words.
     */

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        new BufferedReader(new InputStreamReader(in)).lines()
                .map(line -> {
                    for (String word : abuse) {
                        line = line.replaceAll(word, "");
                    }
                    return line.replaceAll(" {2,}", " ").strip() + "\n";
                }).forEach(line -> {
            try {
                out.write(line.getBytes());
            } catch (IOException e) {
                LOG.error("IOException", e);
            }
        });
    }

    public void dropAbusesSecond(InputStream in, OutputStream out, String[] abuse) throws IOException {
        out.write(
                new BufferedReader(new InputStreamReader(in))
                        .lines()
                        .map(line -> {
                            for (String word : abuse) {
                                line = line.replaceAll(word, "");
                            }
                            return line.replaceAll(" {2,}", " ").strip();
                        }).collect(Collectors.joining("\n")).getBytes()
        );
    }

   public void dropAbuseUniversal(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(in));
             var writer = new BufferedWriter(new OutputStreamWriter(out))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (var word : abuse) {
                    line = line.replaceAll(word, "");
                }
                writer.write(line.replaceAll(" {2,}", " ").strip() + "\n");
            }
        }
    }
}
