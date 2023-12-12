package com.chatop.chatopApiController;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiService.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<Object> getUser(@PathVariable final Long id) {
    try {
      Optional<DbUser> optionalUser = userService.getUserById(id);
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body("{'message' : 'No user found with id: " + id + "'}");
      }
      DbUser user = optionalUser.get();

      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getId());
      userDTO.setName(user.getName());
      userDTO.setEmail(user.getEmail());
      userDTO.setCreatedAt(user.getCreatedAt().toLocalDate());
      userDTO.setUpdatedAt(user.getUpdatedAt().toLocalDate());

      return ResponseEntity.ok().body(userDTO);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("{'message' : 'An error occured'}");
    }
  }
}
