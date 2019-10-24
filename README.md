# springSecurity-simple
精简版安全框架springSecurity 学习样例
>使用
springboot 2.X 、SpringSecurity 、Jquery 3.4.1

- [x] 应用配置 ***application.propertis***
```$xslt
# spring.profiles.active=dev
server.port=10010
debug=true

spring.thymeleaf.encoding=UTF-8
spring.mvc.static-path-pattern=/**
# 设置静态文件资源路径，多个可以用逗号分开
spring.resources.static-locations=classpath:/static/
spring.thymeleaf.servlet.content-type=text/html
spring.thymeleaf.mode=HTML
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```
- [x] 初始化内存用户和资源数据
```$xslt
@Service
@Slf4j
public class UserService {
    private final List<PdspUser> pdspUserList = new ArrayList<>();
    public  UserService(){
        if (pdspUserList.size() == 0) {
            initPdspUsers();
        }
    }
    private void initPdspUsers(){
        PdspUser pdspUser = new PdspUser();
        pdspUser.setUId(1L);
        pdspUser.setUName("chenes");
        pdspUser.setNickName("陈帅帅");
        pdspUser.setPWd("123456");
        pdspUserList.add(pdspUser);

        pdspUser = new PdspUser();
        pdspUser.setUId(2L);
        pdspUser.setUName("crystal");
        pdspUser.setNickName("妖姬");
        pdspUser.setPWd("888888");
        pdspUserList.add(pdspUser);
    }
 .....
}

@Service
public class MenuService {

    private final List<PdspMenu> pdspMenuList = new ArrayList<>();

    public MenuService(){
        if (pdspMenuList.size() == 0) {
            initPdspUserToMenus();
        }
    }

    private void initPdspUserToMenus(){
        PdspMenu pdspMenu = new PdspMenu();
        pdspMenu.setMenuId(1L);
        pdspMenu.setUId(1L);
        pdspMenu.setUrl("/user/list");
        pdspMenuList.add(pdspMenu);

        pdspMenu = new PdspMenu();
        pdspMenu.setMenuId(2L);
        pdspMenu.setUId(1L);
        pdspMenu.setUrl("/menu/list");
        pdspMenuList.add(pdspMenu);

        pdspMenu = new PdspMenu();
        pdspMenu.setMenuId(3L);
        pdspMenu.setUId(2L);
        pdspMenu.setUrl("/user/list");
        pdspMenuList.add(pdspMenu);

    }
    ...
 }
    

```

- [x] 用户登录并授权
```$xslt

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam("uname") String uname, @RequestParam("pwd") String pwd, HttpServletRequest request){

        UsernamePasswordAuthenticationToken  upat = new UsernamePasswordAuthenticationToken(uname,pwd);
        Authentication authentication = myAuthenticationManager.authenticate(upat);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 可以扩展存入redis
        // HttpSession session = request.getSession();
        //session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); 

        return "redirect:/system/toindex";
    }
```

- [x] 无需中间件的安全鉴权精简样例 (扩展PdspUserDetailsService可以实现DB,查看 /sql/ext_demo.sql)

```
public class PdspUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        PdspUser pdspUser = userService.loadUserByUname(s);

        //--------------------认证账号
        if (pdspUser == null) {
            throw new UsernameNotFoundException("账号不存在。");
        }

        List<PdspMenu> menus = menuService.getMenusByUserId(pdspUser.getUId());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for (PdspMenu menu : menus) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
            grantedAuthorityList.add(grantedAuthority);
        }

        pdspUser.setAuthorities(grantedAuthorityList);

        return pdspUser;
    }
}
```
- [x] 配置免过滤资源  ***SecurityConfig***
```$xslt
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("favicon.ico","/images/**","/js/**","/login","/system/login").permitAll()
                // 任何尚未匹配的URL只需要验证用户即可访问
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successForwardUrl("/system/toindex").failureForwardUrl("/error?error=1")
                .and()
                .exceptionHandling().accessDeniedPage("/p403");

        http.logout().logoutSuccessUrl("/login");
    }

```


## 1、登录
![](https://github.com/loveboycrystal/springSecurity-simple/blob/master/src/main/resources/static/wiki/login.png)

## 2、校验未授权权限
![](https://github.com/loveboycrystal/springSecurity-simple/blob/master/src/main/resources/static/wiki/index.png)

## 3、校验未授权权限
![](https://github.com/loveboycrystal/springSecurity-simple/blob/master/src/main/resources/static/wiki/%E6%80%95.png)

## 4、校验授权权限
![](https://github.com/loveboycrystal/springSecurity-simple/blob/master/src/main/resources/static/wiki/p2.png)

## 5、无访问提示
![](https://github.com/loveboycrystal/springSecurity-simple/blob/master/src/main/resources/static/wiki/403.png)
