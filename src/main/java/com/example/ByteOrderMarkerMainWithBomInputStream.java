
package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample code showing usage of {@link BOMInputStream} in removing byte order
 * marker for UTF-8. Name column of the {@link Project} bean would be null
 * without it after CSV is read
 *
 */
@Slf4j
public class ByteOrderMarkerMainWithBomInputStream {
    public static void main(String[] args) throws IOException {
        log.info("Started");

        Path inputPath = Paths.get("test_with_BOM-UTF-8.csv");

        // @formatter:off
        try (
            BOMInputStream bomInputStream = new BOMInputStream(Files.newInputStream(inputPath), false, ByteOrderMark.UTF_8);
                
            Reader reader = new InputStreamReader(bomInputStream);
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