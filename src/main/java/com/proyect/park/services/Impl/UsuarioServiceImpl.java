package com.proyect.park.services.Impl;

import com.proyect.park.models.dto.UsuarioDto;
import com.proyect.park.models.dto.mapper.DtoMapperUsuario;
import com.proyect.park.models.entities.Role;
import com.proyect.park.models.entities.Usuario;
import com.proyect.park.models.request.UsuarioRequest;
import com.proyect.park.repositories.RoleRepository;
import com.proyect.park.repositories.UsuarioRepository;
import com.proyect.park.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios
                .stream()
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDto> encontrarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build());
    }

    @Override
    public UsuarioDto guardarUsuario(Usuario usuario) {
        Optional<Role> conductor = null;
        Optional<Role> propietario = null;
        List<Role> roles = new ArrayList<>();
        if(usuario.getType() == 1){
            // nos ayuda con poner el usuario como conductor
            conductor = roleRepository.findByName("ROLE_CONDUCTOR");
            if(conductor.isPresent() ){
                roles.add(conductor.orElseThrow());
            }
        }else if (usuario.getType() == 2) {
            // nos ayuda con poner el usuario como conductor
            propietario = roleRepository.findByName("ROLE_PROPIETARIO");
            if(propietario.isPresent() ){
                roles.add(propietario.orElseThrow());
            }

        }else {
            // nos ayuda con poner el usuario como conductor
            conductor = roleRepository.findByName("ROLE_CONDUCTOR");

            // nos ayuda con poner el usuario como conductor
            propietario = roleRepository.findByName("ROLE_PROPIETARIO");

            propietario.ifPresent(roles::add);
            conductor.ifPresent(roles::add);
        }

        usuario.setRoles(roles);
        return DtoMapperUsuario.builder().setUsuario(usuarioRepository.save(usuario)).build();
    }

    @Override
    public Optional<UsuarioDto> actualizarUsuario(UsuarioRequest usuario, Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);

        Usuario usuarioOptional = null;
        if(o.isPresent()){
            Usuario usuarioDb= o.orElseThrow();
            usuarioDb.setFullname(usuario.getFullname());
            usuarioDb.setCellphone(usuario.getCellphone());
            usuarioDb.setEmail(usuario.getEmail());

            usuarioOptional = usuarioRepository.save(usuarioDb);
        }
        return Optional.ofNullable(DtoMapperUsuario.builder().setUsuario(usuarioOptional).build());
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
