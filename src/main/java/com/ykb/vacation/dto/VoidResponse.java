package com.ykb.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoidResponse {

    private boolean error = false;
    private String description;
    private String key;

    public VoidResponse(boolean error) {
        this.error = error;
    }

    public VoidResponse(boolean error, String key) {
        this.error = error;
        this.key = key;
    }
}
