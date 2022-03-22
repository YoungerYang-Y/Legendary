//package pers.legendary.auth.server.config.oauth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * Description: 资源服务器配置
// * 注：优先级    ResourceServerConfigurerAdapter > WebSecurityConfigurerAdapter
// *
// * @author YangYang
// * @version 1.0.0
// * @date 2022/3/21 13:30
// */
//@Configuration
//@EnableResourceServer
//@RequiredArgsConstructor
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    private final IgnoreUrlsConfig ignoreUrlsConfig;
//    private final TokenStore tokenStore;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Primary
//    @Bean
//    public RemoteTokenServices remoteTokenServices() {
//        final RemoteTokenServices tokenServices = new RemoteTokenServices();
//        //设置授权服务器check_token端点完整地址
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:13181/oauth/check_token");
//        //设置客户端id与secret，注意：client_secret值不能使用passwordEncoder加密！
//        tokenServices.setClientId("gateway-client");
//        tokenServices.setClientSecret("gateway-client");
//        return tokenServices;
//    }
//
//    /**
//     * 指定当前资源的id和存储方案
//     *
//     * @param resources
//     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        // 也就是当前资源服务的id
//        resources.resourceId("product_api").tokenStore(tokenStore);
//    }
//
//    /**
//     * 这里设置需要token验证的url
//     * 这些url可以在WebSecurityConfigurerAdapter中排除掉，
//     * 对于相同的url，如果二者都配置了验证
//     * 则优先进入ResourceServerConfigurerAdapter,进行token验证。而不会进行
//     * WebSecurityConfigurerAdapter 的 basic auth或表单认证。
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // 通过authorizeRequests()方法来开始请求权限配置
//        http.authorizeRequests()
//                // 允许匿名访问的路径
////                .antMatchers(ignoreUrlsConfig.getUrls().toArray(new String[0])).permitAll()
//                // 其它所有的请求必须通过授权认证（登录）才可以访问
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginProcessingUrl("/login").permitAll()
//                .and()
//                // 关闭跨站请求防护
//                .csrf().disable()
//                // 不使用session
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//}
