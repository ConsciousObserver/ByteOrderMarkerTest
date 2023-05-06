package com.example;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvBindByName(column = "TECHNOLOGY")
    private String technology;
}