package com.merga.linkSave.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction  sortDirection = Sort.Direction.ASC;
    private  String sortBy = "SAVED_DATE";
}
