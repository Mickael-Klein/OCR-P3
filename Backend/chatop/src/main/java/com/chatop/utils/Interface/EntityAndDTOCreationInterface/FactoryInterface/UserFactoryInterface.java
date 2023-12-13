package com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.utils.ReqResModelsAndServices.Request.RegisterRequestModel;

public interface UserFactoryInterface {
  DbUser getPostUserEntity(RegisterRequestModel registerRequestUser);
  UserDTO getUserDTO(DbUser user);
}
