package callcenter

import com.pw.security.Usuario

class Adicional {

    Clientes clientes
    Usuario usuario
    String producto
    String cedula // aqui va la poliza
    String plan
    String valor
    Boolean tarjetaCreada




    static constraints = {
        producto nullable: true
        plan nullable: true
        cedula nullable: true
        //sexo inList: ['MASCULINO', 'FEMENINO']
        //estadoCivil inList: ['SOLTERO', 'CASADO', 'VIUDO', 'DIVORCIADO', 'UNION DE HECHO', 'UNION LEGALIZADA', 'UNION LIBRE']
        //parentesco inList:  ['CONYUGUE', 'UNION DE HECHO', 'PADRES', 'HERMANOS', 'HIJOS', 'HIJASTROS', 'TIOS', 'PRIMOS', 'SOBRINOS', 'ABUELOS', 'NIETOS', 'CUÑADOS', 'AMIGO(A)', 'COMPAÑERO DE TRABAJO', 'NOVIO(A)', 'YERNOS', 'NUERAS']
        valor nullable: true

    }

    static mapping = {
        version false
        tarjetaCreada defaultValue: "false"
    }
}
