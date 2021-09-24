package br.unip.cc.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.cc.tcc.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {}
