/*
 *  Copyright 2017 Curity AB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.curity.identityserver.plugin.instagram.config;

import se.curity.identityserver.sdk.config.Configuration;
import se.curity.identityserver.sdk.config.annotation.DefaultBoolean;
import se.curity.identityserver.sdk.config.annotation.Description;
import se.curity.identityserver.sdk.service.ExceptionFactory;
import se.curity.identityserver.sdk.service.HttpClient;
import se.curity.identityserver.sdk.service.Json;
import se.curity.identityserver.sdk.service.SessionManager;
import se.curity.identityserver.sdk.service.WebServiceClientFactory;
import se.curity.identityserver.sdk.service.authentication.AuthenticatorInformationProvider;

import java.util.Optional;

@SuppressWarnings("InterfaceNeverImplemented")
public interface InstagramAuthenticatorPluginConfig extends Configuration
{
    @Description("Client id")
    String getClientId();

    @Description("Secret key of client application")
    String getClientSecret();

    @Description("Request a scope (public_content) that grants access to read any public profile info and media on a user’s behalf")
    @DefaultBoolean(false)
    boolean isPublicContent();

    @Description("Request a scope (follower_list) that grants access to read the list of followers and followed-by users")
    @DefaultBoolean(false)
    boolean isFollowerList();

    @Description("Request a scope (comments) that grants access to post and delete comments on a user’s behalf")
    @DefaultBoolean(false)
    boolean isCommentsAccess();

    @Description("Request a scope (relationships) that grants access to follow and unfollow accounts on a user’s behalf")
    @DefaultBoolean(false)
    boolean isRelationshipsAccess();

    @Description("Request a scope (likes) that grants access to like and unlike media on a user’s behalf")
    @DefaultBoolean(false)
    boolean isLikesAccess();

    @Description("The HTTP client with any proxy and TLS settings that will be used to connect to slack")
    Optional<HttpClient> getHttpClient();

    SessionManager getSessionManager();

    ExceptionFactory getExceptionFactory();

    AuthenticatorInformationProvider getAuthenticatorInformationProvider();

    WebServiceClientFactory getWebServiceClientFactory();

    Json getJson();

}
