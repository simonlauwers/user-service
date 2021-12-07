package com.theta.userservice.config


import Sl4jLogger.Companion.log
import com.theta.userservice.domain.exceptions.UnauthorizedException
import com.theta.userservice.service.JwtService
import io.jsonwebtoken.JwtException
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
@Slf4j
class AuthFilter(val jwtService: JwtService) : HandlerInterceptor {


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        try {
            if(request.cookies == null) throw UnauthorizedException("user/unauthorized")
            val jwtCookie = request.cookies.filter { cookie ->  cookie.name == "jwt"}[0].value
            // throws exceptions if the jwt is not valid
            jwtService.getJwtClaims(jwtCookie)
            return true
        } catch (e: JwtException) {
            log.warn(e.message)
            return false

        }
    }

}