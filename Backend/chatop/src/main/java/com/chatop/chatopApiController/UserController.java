package com.chatop.chatopApiController;

import com.chatop.Interface.ChatopApiServiceInterface.UserServiceInterface;
import com.chatop.Interface.UtilEntityAndDTOCreationInterface.EntityAndDTOCreationComponentInterface;
import com.chatop.Interface.UtilResponseInterface.UserResponseComponentInterface;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.utils.SwaggerApiResponse.SwaggerApiMessageResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling user-related operations.
 * Provides endpoints for retrieving user information.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserServiceInterface userService;

  @Autowired
  private UserResponseComponentInterface userResponseComponent;

  @Autowired
  private EntityAndDTOCreationComponentInterface entityAndDTOCreationComponent;

  /**
   * Retrieves user information based on the provided user ID.
   *
   * @param id The ID of the user to retrieve.
   * @return ResponseEntity<Object> containing the UserDTO representing the requested user,
   *         along with an appropriate HTTP status.
   */
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Get user success",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UserDTO.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad request, wrong id parameter",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "401",
        description = "Unauthorize",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
    }
  )
  @Operation(security = { @SecurityRequirement(name = "bearer-key") })
  @GetMapping("/{id}")
  public ResponseEntity<Object> getUser(@PathVariable final Long id) {
    try {
      Optional<DbUser> optionalUser = userService.getUserById(id);
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(userResponseComponent.getUserInvalidIdParameterJsonString());
      }
      DbUser user = optionalUser.get();

      UserDTO userDTO = entityAndDTOCreationComponent.getFactoryUserDTO(user);

      return ResponseEntity.ok().body(userDTO);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(userResponseComponent.getUserErrorOccurJsonString());
    }
  }
}
