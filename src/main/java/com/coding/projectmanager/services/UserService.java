package com.coding.projectmanager.services;

import com.coding.projectmanager.models.User;
import com.coding.projectmanager.models.LoginUser;
import com.coding.projectmanager.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User newUser, BindingResult result){
        Optional<User> checkUser = userRepository.findByEmail(newUser.getEmail());
        if(checkUser.isPresent()){
           result.rejectValue("email","Matches", "Email is already registered");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())){
            result.rejectValue("password", "Matches", "Provided passwords must match.");
        }
        if(result.hasErrors()){
            return null;
        }
        else{
            newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
            return userRepository.save(newUser);
        }

    }

    public User login(LoginUser newLoginObject, BindingResult result){
        Optional<User> checkLogin = userRepository.findByEmail(newLoginObject.getEmail());
        if(checkLogin.isPresent()) {
            if (!BCrypt.checkpw(newLoginObject.getPassword(), checkLogin.get().getPassword())) {
                result.rejectValue("password", "Matches", "Password is Invalid!");
            }
        }else{
                result.rejectValue("email", "Missing", "Email is invalid");
            }
        if(result.hasErrors()){
            return null;
        }
        else{
            return checkLogin.get();
        }
    }

    public User findUser(Long id){
        Optional<User> oneUser = userRepository.findById(id);
        if(oneUser.isPresent()){
            return oneUser.get();
        }
        return null;
    }
}
