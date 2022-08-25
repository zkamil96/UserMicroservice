package com.example.usermicroservice.Service;

import com.example.usermicroservice.Config.JwtTokenUtil;
import com.example.usermicroservice.Model.Users;
import com.example.usermicroservice.Respository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    public ResponseEntity<?> loginUser(Users user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

            String accessToken  = jwtTokenUtil.generateAccessToken(user);
            return ResponseEntity.ok(accessToken);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<?> registerUser(Users user) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            usersRepository.save(user);
            return ResponseEntity.ok(user);
    }
}
