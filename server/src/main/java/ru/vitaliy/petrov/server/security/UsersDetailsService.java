package ru.vitaliy.petrov.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.UnauthorizedApiException;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Users> user = usersRepository.findByUserPhoneNumber(username);
        if (user.isPresent()) {
            return new UsersDetails(user.get());
        } else {
            throw new UnauthorizedApiException("Пользователя " + username + " нет в базе данных");
        }
    }
}
