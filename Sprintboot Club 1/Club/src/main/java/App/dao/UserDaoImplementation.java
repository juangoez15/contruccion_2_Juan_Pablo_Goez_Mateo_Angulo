package App.dao;

import App.dto.UserDto;
import App.model.User;
import App.Helper.Helper;
import App.dao.interfaces.UserDao;
import App.dao.interfaces.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service

public class UserDaoImplementation implements UserDao {
    
    @Autowired
    
    UserRepository userRepository;

    @Override
    public UserDto findByUserName(UserDto userDto) throws Exception {
        User user = userRepository.findByUserName(userDto.getUserName());
        return Helper.parse(user);
    }

    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        return userRepository.existsByUserName(userDto.getUserName());
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.save(user);
        userDto.setId(user.getId());
    }
}
