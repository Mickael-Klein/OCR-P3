package com.chatop.chatopApiController;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiService.UserService;
import com.chatop.utils.EntityAndDTOCreation.EntityAndDTOCreationService;
import com.chatop.utils.ReqResModelsAndServices.Response.UserResponseService;
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

  @Autowired
  private UserResponseService userResponseService;

  @Autowired
  private EntityAndDTOCreationService entityAndDTOCreationService;

  @GetMapping("/{id}")
  public ResponseEntity<Object> getUser(@PathVariable final Long id) {
    try {
      Optional<DbUser> optionalUser = userService.getUserById(id);
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getUserInvalidIdParameterJsonString());
      }
      DbUser user = optionalUser.get();

      UserDTO userDTO = entityAndDTOCreationService.getFactoryUserDTO(user);

      return ResponseEntity.ok().body(userDTO);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(userResponseService.getUserErrorOccurJsonString());
    }
  }
}
