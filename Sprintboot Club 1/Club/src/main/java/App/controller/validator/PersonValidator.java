/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component

public class PersonValidator extends CommonsValidator {

    public void validName(String name) throws Exception {
        super.isValidString("el nombre de la persona ", name);
    }

    public long validDocument(String document) throws Exception {
        return super.isValidLong("la cedula de la persona ", document);
    }

    public long validCelPhone(String celPhone) throws Exception {
        return super.isValidLong("el numero de celular ", celPhone);
    }

}
