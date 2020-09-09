package com.auth.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/9/25 17:00
 * @Description 自定义错误提示json格式
 */
@Component
@Configuration
public class SecurityHandlerConfig {

	@Resource
	private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类

	/**
	 * 登陆成功，返回Token 装配此bean不支持授权码模式
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new SavedRequestAwareAuthenticationSuccessHandler() {
			private RequestCache requestCache = new HttpSessionRequestCache();
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
				super.onAuthenticationSuccess(request, response, authentication);
				return;
			}
		};
	}

	/**
	 * 登陆失败
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return (request, response, exception) -> {
			String msg;
			if (exception instanceof BadCredentialsException) {
				msg = "密码错误";
			} else {
				msg = exception.getMessage();
			}
			Map<String, String> rsp = new HashMap<>();
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			rsp.put("resp_code", HttpStatus.UNAUTHORIZED.value() + "");
			rsp.put("rsp_msg", msg);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(rsp));
			response.getWriter().flush();
			response.getWriter().close();

		};
	}

	@Bean
	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {
			public static final String BAD_MSG = "坏的凭证";
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				// e.printStackTrace();
				OAuth2Exception oAuth2Exception;
				if (e.getMessage() != null && e.getMessage().equals(BAD_MSG)) {
					oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
				} else if (e instanceof InternalAuthenticationServiceException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else if (e instanceof RedirectMismatchException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else if (e instanceof InvalidScopeException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else {
					oAuth2Exception = new UnsupportedResponseTypeException(e.getMessage(), e);
				}
				ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
				ResponseEntity.status(oAuth2Exception.getHttpErrorCode());
				response.getBody().addAdditionalInformation("resp_code", oAuth2Exception.getHttpErrorCode() + "");
				response.getBody().addAdditionalInformation("resp_msg", oAuth2Exception.getMessage());

				return response;
			}

		};
	}


}
