package com.buenoezandro.linkedIn.api.services;

import com.buenoezandro.linkedIn.api.entities.Usuario;
import com.buenoezandro.linkedIn.api.repositories.UsuarioRepository;
import com.buenoezandro.linkedIn.api.services.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public Usuario criar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> buscar() {
        return this.usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarPorId(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    @Override
    public Usuario atualizar(Long id, Usuario usuario) {
        var usuarioAtualizado = this.buscarPorId(id);

        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setDataNascimento(usuario.getDataNascimento());
        usuarioAtualizado.setTitulo(usuario.getTitulo());
        usuarioAtualizado.setLocalizacao(usuario.getLocalizacao());
        usuarioAtualizado.setTelefones(usuario.getTelefones());
        usuarioAtualizado.setPosts(usuario.getPosts());

        return this.usuarioRepository.save(usuarioAtualizado);
    }

    @Transactional
    @Override
    public void deletarPorId(Long id) {
        this.buscarPorId(id);
        this.usuarioRepository.deleteById(id);
    }

}