package org.example.trabajo_back.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    // Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Origen permitido: aplicación Angular
        configuration.setAllowedOrigins(
                List.of("http://localhost:4200")
        );

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        );

        // Cabeceras HTTP permitidas
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Cabeceras que Angular puede leer en la respuesta
        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        // No se utilizan cookies de autenticación
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        // Aplicar CORS a todos los endpoints
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // Login público
                        .requestMatchers("/login/OpticVision").permitAll()
                        //Citas
                        .requestMatchers(HttpMethod.POST, "/citas/registrarCitas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/citas/listarCitas").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/citas/actualizarCitas").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/citas/EliminarLaCita/**").permitAll()
                        //
                        .requestMatchers(HttpMethod.GET, "/citas/buscarCitasPorId/**").permitAll()
                        //Detalles
                        .requestMatchers(HttpMethod.POST, "/detalles/registrarDetalle").permitAll()
                        .requestMatchers(HttpMethod.GET, "/detalles/listarDetalles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/detalles/buscarDetallePorId/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/detalles/actualizarDetalle").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/detalles/eliminarDetalle/**").permitAll()
                        //Intervencion
                        .requestMatchers(HttpMethod.POST, "/api/intervenciones/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/intervenciones").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/intervenciones/buscarPorID/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/intervenciones/actualizar").permitAll()
                        //Lentes
                        .requestMatchers(HttpMethod.POST, "/lentes/registrarLente").permitAll()
                        .requestMatchers(HttpMethod.GET, "/lentes/listarLentes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/lentes/buscarLentePorId/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/lentes/actualizarLente").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/lentes/eliminarLente/**").permitAll()
                        //Oftalmologo
                        .requestMatchers(HttpMethod.POST, "/api/oftalmologos/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/oftalmologos/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/oftalmologos/buscarID/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/oftalmologos/actualizar").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/oftalmologos/eliminar/**").permitAll()
                        //Paciente
                        .requestMatchers(HttpMethod.POST, "/api/paciente/registrarP").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/paciente/listarP").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/paciente/buscarIDP/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/paciente/actualizarPaciente").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/paciente/eliminarPaciente/**").permitAll()
                        //Recetas
                        .requestMatchers(HttpMethod.POST, "/api/recetas/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/recetas/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/recetas/buscarPorID/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/recetas/actualizar").permitAll()
                        //Tratamiento
                        // Tratamientos
                        .requestMatchers(HttpMethod.GET, "/api/tratamientos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/tratamientos/registrar").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/tratamientos/actualizar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/tratamientos/buscarPorID/**").permitAll()


                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        new CustomJwtAuthenticationConverter()
                                )
                        )
                );

        return http.build();
    }
}

