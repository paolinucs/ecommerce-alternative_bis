package it.paolone.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.paolone.ecommerce.entities.User;
import it.paolone.ecommerce.services.UserInfoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth/god")
@AllArgsConstructor
public class GodController {

    private final UserInfoService userInfoService;

    @PostMapping("/new_admin")
    @PreAuthorize("hasAuthority('ROLE_GOD')")
    public ResponseEntity<User> registerNewAdmin(@RequestBody User data) throws ResponseStatusException {

        if (data != null) {

            data.setRoles("ROLE_ADMIN");

            if (!data.getRoles().contains("ROLE_ADMIN"))
                return ResponseEntity.badRequest().build();

            return ResponseEntity.ok(userInfoService.addUser(data));

        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/deleteUser/{email}")
    @PreAuthorize("hasAuthority('ROLE_GOD')")
    public ResponseEntity<Boolean> deleteUserByEmail(@PathVariable String email) {
        if (email != null) {
            int responseData = userInfoService.deleteUser(email);

            if (responseData == 0)
                return ResponseEntity.ok(true);

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // @PostMapping("/edit_user/email/{actualEmail}&{newEmail}")
    // @PreAuthorize("hasAuthority('ROLE_GOD')")
    // public ResponseEntity<Boolean> editUserEmail(@PathVariable String actualEmail, @PathVariable String newEmail) {
    //     if (actualEmail != null && newEmail != null) {
    //         Boolean isEdited = userInfoService.editUserEmail(actualEmail, newEmail);

    //         if (isEdited)
    //             return ResponseEntity.ok(isEdited);

    //         return ResponseEntity.badRequest().build();
    //     }

    //     return ResponseEntity.noContent().build();
    // }

    // @PostMapping("/edit_user/password/{actualPassword}&{newPassword}")
    // @PreAuthorize("hasAuthority('ROLE_GOD')")
    // public ResponseEntity<Boolean> editUserPassword(@PathVariable String actualPassword,
    //         @PathVariable String newPassword) {
    //     if (actualPassword != null && newPassword != null) {
    //         Boolean isEdited = userInfoService.editUserEmail(actualPassword, newPassword);

    //         if (isEdited)
    //             return ResponseEntity.ok(isEdited);

    //         return ResponseEntity.badRequest().build();
    //     }

    //     return ResponseEntity.noContent().build();

    // }

}
