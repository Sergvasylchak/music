package com.music.lbry.controllers;

import com.music.lbry.basic.security.UnauthorizedException;
import com.music.lbry.models.entities.LibraryUser;
import com.music.lbry.services.UserService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping(Constants.API_ENDPOINT + "/users")
@AllArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;

    @GetMapping("/me")
    public Mono<LibraryUser> user() throws UnauthorizedException {
        return this.userService.getMe();
    }

    @PutMapping("/me")
    public Mono<LibraryUser> updateInfo(@RequestBody LibraryUser user) {
        return this.userService.getMe().flatMap(u -> {
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            return this.userService.update(u);
        });
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Optional<LibraryUser>> grantRights(@PathVariable("id") Long id) {
        return this.userService.grantRights(id);
    }
}
