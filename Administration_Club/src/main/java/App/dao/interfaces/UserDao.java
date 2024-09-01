/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package App.dao.interfaces;

import App.dto.UserDto;

public interface UserDao {

    public UserDto findByUserName(UserDto userDto) throws Exception;

    public boolean existsByUserName(UserDto userDto) throws Exception;

    public void createUser(UserDto userDto) throws Exception;

    public void createGuest(UserDto userDto) throws Exception;
}
