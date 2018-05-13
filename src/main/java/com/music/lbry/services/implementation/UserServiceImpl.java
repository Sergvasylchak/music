package com.music.lbry.services.implementation;

import com.music.lbry.models.entities.LibraryUser;
import com.music.lbry.models.enums.Role;
import com.music.lbry.repository.UserRepository;
import com.music.lbry.services.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<Page<LibraryUser>> findAllBySearch(String search, Pageable pageable) {
        return Mono.fromCallable(() -> this.userRepository.findAllBySearch(search, pageable));
    }

    @Override
    public Mono<Optional<LibraryUser>> findById(Long id) {
        return Mono.fromCallable(() -> this.userRepository.findById(id));
    }

    @Override
    public Mono<LibraryUser> add(LibraryUser libraryUser) {
        return Mono.fromCallable(() -> this.userRepository.save(libraryUser))
                .onErrorResume(e -> Mono.empty());
    }

    @Override
    public Mono<Optional<LibraryUser>> findByUsername(String useename) {
        return Mono.fromCallable(() -> this.userRepository.findByUsername(useename));
    }

    @Override
    public Mono<LibraryUser> getMe() {
        val username = ((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return Mono.fromCallable(() -> this.userRepository.findByUsername(username))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    @Override
    public Mono<LibraryUser> update(LibraryUser user) {
        return Mono.fromCallable(() -> this.userRepository.save(user));
    }

    @Override
    public Mono<Optional<LibraryUser>> grantRights(Long id) {
        return Mono.fromCallable(() -> this.userRepository.findById(id)
                .map(u -> {
                    val role = u.getRole().equals(Role.USER) ? Role.ADMIN : Role.USER;
                    u.setRole(role);
                    return this.userRepository.save(u);
                })
        );
    }
}
