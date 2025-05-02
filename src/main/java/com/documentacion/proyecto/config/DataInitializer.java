package com.documentacion.proyecto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.documentacion.proyecto.model.Rol;
import com.documentacion.proyecto.model.Usuario;
import com.documentacion.proyecto.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(UsuarioRepository usuarioRepo, PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        // *Verifica si el usuario "admin" ya existe*
        if (usuarioRepo.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin Principal");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRol(Rol.ADMIN); // *Asignación directa del enum Rol*
            usuarioRepo.save(admin);
        }
    }
}
// @Component
// public class DataInitializer {
//     @Autowired
//     private UsuarioRepository usuarioRepo;
//     @Autowired
//     private PasswordEncoder encoder;

//     @PostConstruct
//     public void init() {
//         if (usuarioRepo.findByUsername("admin").isEmpty()) {
//             Usuario admin = new Usuario();
//             admin.setNombre("Admin Principal");
//             admin.setUsername("admin");
//             admin.setPassword(encoder.encode("admin123"));
//             admin.setRol(Rol.ADMIN);
//             usuarioRepo.save(admin);
//         }
//     }
// }

