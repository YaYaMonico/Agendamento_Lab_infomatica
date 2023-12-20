package com.agendamento.yasmin.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = { @Server( url="http://localhost:8080") }, 
info = @Info (title="Documentação nossa API", 
description= "Descrição da nossa API", version = "v1.0"))

@SecurityScheme(name = "security_auth", 
type =  SecuritySchemeType.OAUTH2,
flows = @OAuthFlows ( clientCredentials = @OAuthFlow(tokenUrl="http://localhost:8080/oauth/token", 
scopes = {@OAuthScope( name = "password", 
description = "Escopo padrão")})))

public class OpenAPI3Configuration {


	

}
