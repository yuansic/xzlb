package com.xz.comtroller;

import com.xz.enums.AuthCode;
import com.xz.util.AuthRequire;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Aspect
public class AuthorizationInterceptor {

    //private static final Logger org.slf4j.Logger.LOGGER = org.slf4j.LoggerFactory.getLogger(AuthorizationInterceptor.class);

    //	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private RedisTemplate redisTemplate;

//	@Around("execution(* com.sijibao.mls.bop.controller..*.*(..))")
//	public Object andleBody(ProceedingJoinPoint pjp) throws Throwable {
//
//		// 接收到请求，记录请求内容
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//
//		MethodSignature signature = (MethodSignature) pjp.getSignature();
//		Method method = signature.getMethod(); //获取被拦截的方法
//
//		/**
//         * 解析鉴权模式：
//         * 1）先判断Action是否要求鉴权；
//         * 2）如果没有，则继续判断Controller是否要求鉴权；
//         * 3）如果仍然没有，则默认允许匿名访问
//         */
//        AuthMode mode = getAuthMode(method, method.getDeclaringClass()); //鉴权模式对象封装
//
//		//检查后判断
//        /**/
//    	if(mode.authCode == AuthCode.REQ_GUEST){ //允许匿名
//    		return pjp.proceed();
//    	}else{
//    		SysUser tmpCompanyUser = JWTUtil.getCompanyUser(request, stringRedisTemplate, false);
//    		if(tmpCompanyUser == null || tmpCompanyUser.getCompanyId() == null){
//    			throw new AuthorizationException();
//    		}
//    		List<String> permslist = tmpCompanyUser.getPermsList();	//获取权限集合
//    		if(permslist == null) permslist = Lists.newArrayList();
//    		if(mode.authCode == AuthCode.REQ_LOGIN){//根据用户的登录状态进行鉴权
//    		}else if(mode.authCode == AuthCode.REQ_PERM){//根据用户的权限进行鉴权
//    			if(StringUtils.isBlank(mode.authId) || !permslist.contains(mode.authId)){	//判断是否存在权限集合
//    				throw new NoAuthorizationException();
//    			}
//    		}else if(mode.authCode == AuthCode.REQ_PERMS){//根据用户的权限集合进行鉴权
//    			boolean auth = false;
//    			for (int i = 0; mode.authIds != null && i < mode.authIds.length; i++) {
//    				if(permslist.contains(mode.authIds[i])){	//判断其中一个存在权限集合则通过
//    					auth = true;
//    					break;
//    				}
//				}
//
//    			if(!auth){
//    				throw new NoAuthorizationException();
//    			}
//    		}
//    	}
//
//		return pjp.proceed();
//	}


    /**
     * 优先进行Action层面的鉴权
     *
     * @param method
     * @param ctrl
     */
    private AuthMode getAuthMode(Method method, Class<?> ctrl) {
        AuthMode mode = new AuthMode();
        if (method.isAnnotationPresent(AuthRequire.Role.class)) {
            mode.authCode = AuthCode.REQ_ROLE;
            mode.authId = method.getAnnotation(AuthRequire.Role.class).value();
        } else if (method.isAnnotationPresent(AuthRequire.Roles.class)) {
            mode.authCode = AuthCode.REQ_ROLES;
            mode.authIds = method.getAnnotation(AuthRequire.Roles.class).value();
        } else if (method.isAnnotationPresent(AuthRequire.Perm.class)) {
            mode.authCode = AuthCode.REQ_PERM;
            mode.authId = method.getAnnotation(AuthRequire.Perm.class).value();
        } else if (method.isAnnotationPresent(AuthRequire.Perms.class)) {
            mode.authCode = AuthCode.REQ_PERMS;
            mode.authIds = method.getAnnotation(AuthRequire.Perms.class).value();
        } else if (method.isAnnotationPresent(AuthRequire.Logined.class)) {
            mode.authCode = AuthCode.REQ_LOGIN;
        } else if (method.isAnnotationPresent(AuthRequire.Guest.class)) {
            mode.authCode = AuthCode.REQ_GUEST;
        } else {

            //进行Controller层面的鉴权，只有当Action未设置时有效
            if (ctrl.isAnnotationPresent(AuthRequire.Role.class)) {
                mode.authCode = AuthCode.REQ_ROLE;
                mode.authId = ctrl.getAnnotation(AuthRequire.Role.class).value();
            } else if (ctrl.isAnnotationPresent(AuthRequire.Roles.class)) {
                mode.authCode = AuthCode.REQ_ROLES;
                mode.authIds = ctrl.getAnnotation(AuthRequire.Roles.class).value();
            } else if (ctrl.isAnnotationPresent(AuthRequire.Perm.class)) {
                mode.authCode = AuthCode.REQ_PERM;
                mode.authId = ctrl.getAnnotation(AuthRequire.Perm.class).value();
            } else if (ctrl.isAnnotationPresent(AuthRequire.Perms.class)) {
                mode.authCode = AuthCode.REQ_PERMS;
                mode.authIds = ctrl.getAnnotation(AuthRequire.Perms.class).value();
            } else if (ctrl.isAnnotationPresent(AuthRequire.Logined.class)) {
                mode.authCode = AuthCode.REQ_LOGIN;
            } else {
                mode.authCode = AuthCode.REQ_GUEST;        //默认可以访问
            }
        }

        return mode;
    }


    /**
     * 鉴权模式封装
     */
    class AuthMode {
        private AuthCode authCode = null; //各种鉴权模式枚举
        private String authId = null; //单条鉴权标识（单角色、单权限）
        private String[] authIds = null; //多条鉴权标识（多角色、多权限）
    }

}
