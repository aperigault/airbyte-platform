/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.server.apis;

import static io.airbyte.commons.auth.AuthRoleConstants.ADMIN;

import io.airbyte.api.generated.UserApi;
import io.airbyte.api.model.generated.UserAuthIdRequestBody;
import io.airbyte.api.model.generated.UserCreate;
import io.airbyte.api.model.generated.UserIdRequestBody;
import io.airbyte.api.model.generated.UserRead;
import io.airbyte.api.model.generated.UserUpdate;
import io.airbyte.commons.auth.SecuredUser;
import io.airbyte.commons.server.handlers.UserHandler;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

/**
 * This class is migrated from cloud-server UserApiController
 * {@link io.airbyte.cloud.server.apis.UserApiController}.
 *
 * TODO: migrate all User endpoints (including some endpoints in WebBackend API) from Cloud to OSS.
 */
@SuppressWarnings("MissingJavadocType")
@Controller("/api/v1/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserApiController implements UserApi {

  private final UserHandler userHandler;

  public UserApiController(final UserHandler userHandler) {
    this.userHandler = userHandler;
  }

  @Post("/create")
  @SecuredUser
  @Secured({ADMIN})
  @Override
  public UserRead createUser(final UserCreate userCreate) {
    return ApiHelper.execute(() -> userHandler.createUser(userCreate));
  }

  @Post("/get")
  @SecuredUser
  @Secured({ADMIN})
  @Override
  public UserRead getUser(final UserIdRequestBody userIdRequestBody) {
    return ApiHelper.execute(() -> userHandler.getUser(userIdRequestBody));
  }

  @Post("/get_by_auth_id")
  @SecuredUser
  @Secured({ADMIN})
  @Override
  public UserRead getUserByAuthId(final UserAuthIdRequestBody userAuthIdRequestBody) {
    return ApiHelper.execute(() -> userHandler.getUserByAuthId(userAuthIdRequestBody));
  }

  @Post("/delete")
  @SecuredUser
  @Secured({ADMIN})
  @Override
  public void deleteUser(final UserIdRequestBody userIdRequestBody) {
    ApiHelper.execute(
        () -> {
          userHandler.deleteUser(userIdRequestBody);
          return null;
        });
  }

  @Post("/update")
  @SecuredUser
  @Secured({ADMIN})
  @Override
  public UserRead updateUser(final UserUpdate userUpdate) {
    return ApiHelper.execute(() -> userHandler.updateUser(userUpdate));
  }

}