package com.localmart.dto;

import lombok.Data;

@Data
public class AISearchRequest {
    private String query;

    // Explicit getter and setter to ensure VS Code compiles it perfectly immediately
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}