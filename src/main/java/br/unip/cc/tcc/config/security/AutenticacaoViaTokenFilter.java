package br.unip.cc.tcc.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.unip.cc.tcc.model.User;
import br.unip.cc.tcc.repository.UserRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private final TokenService tokenService;
	private final UserRepository repository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperaToken(request);
		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autentificaUsuario(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autentificaUsuario(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<User> optional = repository.findById(idUsuario);
		optional.ifPresent(usuario -> {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		});
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}
}
