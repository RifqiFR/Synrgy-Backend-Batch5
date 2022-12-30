package com.synergy.challenge6.service;

import com.synergy.challenge6.model.User;
import com.synergy.challenge6.repository.UserRepository;
import com.synergy.challenge6.view.APIResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Map createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            User createdUser = userRepository.save(user);
            logger.info("User baru berhasil dibuat, userId : " + user.getId());
            return APIResponse.success(
                    createdUser,
                    201
            );
        }catch(Exception e) {
            logger.error("User gagal dibuat, error : " + e);
            return APIResponse.error("User gagal dibuat", 400);
        }
    }

    @Override
    public Map updateUser(long userId, User user) {
        try{
            user.setId(userId);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return APIResponse.success(
                    userRepository.save(user),
                    200
            );
        }catch(Exception e) {
            return APIResponse.error("User gagal diupdate", 400);
        }
    }

    @Override
    public Map getAll()  {
        try{
            return APIResponse.success(
                    userRepository.findAll(),
                    200
            );
        }catch(Exception e) {
            return APIResponse.error("Error", 400);
        }
    }

    @Override
    public Map login(String email, String password) {
        Optional<User> user = userRepository.findByEmailAddress(email);
        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword()))
            return APIResponse.error("Email atau password salah", HttpStatus.BAD_REQUEST.value());

        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = user.get().getRole().getAuthority();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(user.get().getUsername())
                .claim("scope", scope)
                .build();
        // @formatter:on
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return APIResponse.success(
                token,
                HttpStatus.OK.value()
        );

    }

    @Override
    public Map deleteUser(long userId) {
        try{
            userRepository.deleteById(userId);
            return APIResponse.success(
                    null,
                    204
            );
        }catch(Exception e) {
            return APIResponse.error("User gagal dihapus", 400);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username).orElseThrow(
                () -> new RuntimeException("User not found: " + username)
        );
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        return new org.springframework.security.core.userdetails.User(
                user.getEmailAddress(), user.getPassword(), Arrays.asList(authority)
        );
    }
}
