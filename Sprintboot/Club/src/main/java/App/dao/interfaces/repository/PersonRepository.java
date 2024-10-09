package App.dao.interfaces.repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import App.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface PersonRepository extends JpaRepository<Person,Long>{
    
    public boolean existsByDocument(long Document);
    
    public Person findByDocument(long Document);
    
}
