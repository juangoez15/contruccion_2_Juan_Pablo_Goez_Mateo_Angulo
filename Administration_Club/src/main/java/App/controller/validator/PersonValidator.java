/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller.validator;

public class PersonValidator extends CommonsValidator {

    public PersonValidator() {
        super();
    }

    public void validName(String name) throws Exception {
        super.isValidString("person's name", name);
    }

    public long validDocument(String document) throws Exception {
        return super.isValidLong("person's ID card", document);
    }
}
