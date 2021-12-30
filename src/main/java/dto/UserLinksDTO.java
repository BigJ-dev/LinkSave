package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLinksDTO {
    private Long id;
    private String siteName;
    private String siteUrl;
    private Date savedDate;
}
