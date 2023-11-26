package cibertec.edu.pe.DSWII_T3_MarcoBegazo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cibertec.edu.pe.DSWII_T3_MarcoBegazo.Model.bd.Usuario;

@Repository
public interface UsuarioRepository extends
        JpaRepository<Usuario, Integer> {

    Usuario findByNomusuario(String nomusuario);

}