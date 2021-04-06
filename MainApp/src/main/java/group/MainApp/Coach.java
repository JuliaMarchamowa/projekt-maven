package group.MainApp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class Coach {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}
