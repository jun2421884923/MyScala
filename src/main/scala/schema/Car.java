package schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


/**
 * @Author: jun.chang
 * @Date: 2024-11-28
 * @Description:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {
    @JsonProperty("id")
    private Integer model = 0  ;
    @JsonProperty("name1")
    private String name = ""  ;

}
