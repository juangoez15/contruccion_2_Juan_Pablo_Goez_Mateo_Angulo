/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package App.dao.interfaces.repository;

import App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ASUS
 */
public interface UserRepository  extends JpaRepository<User,Long>{

    public User findByUsername(String Username);

    public boolean existsByUsername(String Username);
    
}
