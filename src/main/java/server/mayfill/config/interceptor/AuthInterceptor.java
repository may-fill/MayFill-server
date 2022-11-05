package server.mayfill.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import server.mayfill.config.security.JwtConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final LoginCheckHandler loginCheckHandler;

    /**
     *
     * @param request HttpServletRequest: http 프로토콜의 request 정보를 서블릿에게 전달하기 위한 목적으로 사용하며 헤더 정보, 파라미터,
     *                쿠키, URI, URL 등의 정보를 읽어 들이는 메서드와 Body 의 Stream 을 읽어 들이는 메서드를 가지고 있다
     * @param response HttpServletResponse: WAS 는 어떤 클라이언트가 요청을 보냈는지 알고 있고, 해당 클라이언트에게 응답을 보내기 위한
     *                 HttpServletResponse 객체를 생성하여 서블릿에게 전달하고 이 객체를 활용하여 content type, 응답 코드, 응답 메시지 등을 전송
     * @param handler @RequestMapping 이 붙은 메소드의 정보를 추상화한 객체로 org.springframework.web.method.HandlerMethod 라는 클래스로 바인드 되어 전달되는 인자
     *                즉, 핸들러 매핑이 찾아준 컨트롤러 빈에 매핑되는 HandlerMethod 라는 새로운 타입의 객체
     * @return 반환값이 true 이면 다음 단계로 넘어가고, false 이면 다음 단계로 넘어가지 않는다
     * @throws Exception
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }
        Long userId = loginCheckHandler.getUserId(request);
        request.setAttribute(JwtConstants.USER_ID, userId);
        return true;
    }

}
