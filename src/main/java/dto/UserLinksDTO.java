package dto;
import lombok.Data;
import java.util.Map;

@Data
public class UserLinksDTO {
    private String username;
    private Map<String,String > siteLinks;
}
