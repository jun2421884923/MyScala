package schema
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.{Data, Getter, Setter};

/**
 * @Author: jun.chang
 * @Date: 2024-11-27 
 * @Description:
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Person {

    var age=0

    var name =""



}
