
package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.input.BOMInputStream;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Tries to read a file starting with BOM-UTF-8 marker without
 * {@link BOMInputStream}, you'll see that it fails to read the name column
 * values for {@link Project} beans
 *
 */
@Slf4j
public class ByteOrderMarkerMainWithoutBomInputStream {
    public static void main(String[] args) throws IOException {
        log.info("Started");

        Path inputPath = Paths.get("test_with_BOM-UTF-8.csv");

        // @formatter:off
        try (
            InputStream withoutBomInputStream = Files.newInputStream(inputPath);
                
            Reader reader = new InputStreamReader(withoutBomInputStream);
        ) {
        // @formatter:on
            HeaderColumnNameMappingStrategy<Project> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
            mappingStrategy.setType(Project.class);

            new CsvToBeanBuilder<Project>(reader).withType(Project.class)
                    .withMappingStrategy(mappingStrategy)
                    .build()
                    .iterator()
                    .forEachRemaining(i -> log.info(i.toString()));
        }
    }
}