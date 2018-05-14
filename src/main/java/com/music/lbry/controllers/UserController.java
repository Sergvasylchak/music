package com.music.lbry.controllers;

import com.music.lbry.basic.security.UnauthorizedException;
import com.music.lbry.models.entities.LibraryUser;
import com.music.lbry.services.UserService;
import com.music.lbry.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<Page<LibraryUser>> getAllUsers(@RequestParam(name = Constants.PAGE, required = false, defaultValue = Constants.PAGE_NUMBER) Integer page,
                                               @RequestParam(name = Constants.SIZE, required = false, defaultValue = Constants.PAGE_SIZE) Integer size,
                                               @RequestParam(name = Constants.NAME, defaultValue = Constants.BLANK) String search) {
        return this.userService.findAllBySearch(search, getPageable(page, size));
    }

}
