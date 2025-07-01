package com.bappi.pdfgeneratorapplication.dto;

public record CompanyInfoRequestDto(
        String name,
        String address,
        String companyShortName
) {}
