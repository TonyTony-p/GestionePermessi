package it.permessi.rest.permessi.mapper;

import it.permessi.rest.permessi.dto.GruppoDto;
import it.permessi.rest.permessi.dto.CaneDto;
import it.permessi.rest.permessi.dto.RazzaDto;
import it.permessi.rest.permessi.dto.PermessoDto;
import it.permessi.rest.permessi.dto.PermessoRuoloDto;
import it.permessi.rest.permessi.dto.RuoloDto;
import it.permessi.rest.permessi.dto.UtenteDto;
import it.permessi.rest.permessi.entity.Gruppo;
import it.permessi.rest.permessi.entity.Permesso;
import it.permessi.rest.permessi.entity.Ruolo;
import it.permessi.rest.permessi.entity.RuoloPermesso;
import it.permessi.rest.permessi.entity.Utente;
import it.permessi.rest.permessi.entity.Cane;
import it.permessi.rest.permessi.entity.Razza;

import java.util.List;
import java.util.stream.Collectors;



/**
 * Mapper Entity -> DTO con metodi ESPLICITI (niente overload con booleani),
 * per evitare confusione ed eliminare ricorsioni.
 */
public class DtoMapper {

   
    /** Gruppo "light": solo campi del gruppo, senza lista dei permessi. */
    public static GruppoDto toGruppoDtoLight(Gruppo g) {
        if (g == null) return null;
        GruppoDto dto = new GruppoDto();
        dto.setId(g.getId());
        dto.setNome(g.getNome());
        dto.setAlias(g.getAlias());
        dto.setCreatedAt(g.getCreatedAt());
        dto.setUpdatedAt(g.getUpdatedAt());
        return dto;
    }

    /** Gruppo "withPermessi": include anche la lista dei PermessoDto (light). */
    public static GruppoDto toGruppoDtoWithPermessi(Gruppo g) {
        if (g == null) return null;
        GruppoDto dto = toGruppoDtoLight(g);
        if (g.getPermessi() != null) {
            dto.setPermessi(
                g.getPermessi().stream()
                  .map(DtoMapper::toPermessoDtoLight)  // permesso light
                  .collect(Collectors.toList())
            );
        }
        return dto;
    }

    
    /** Permesso "light": solo campi + gruppo (light). */
    public static PermessoDto toPermessoDtoLight(Permesso p) {
        if (p == null) return null;
        PermessoDto dto = new PermessoDto();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setAlias(p.getAlias());
        dto.setGruppo(toGruppoDtoLight(p.getGruppo())); // gruppo light
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }

    /**
     * Permesso "withAssoc": include anche la lista di associazioni ruolo-permesso.
     * Ogni associazione è mappata in modo "safe" (ruolo/permesso light) per evitare ricorsioni.
     */
    public static PermessoDto toPermessoDtoWithAssoc(Permesso p) {
        if (p == null) return null;
        PermessoDto dto = toPermessoDtoLight(p);
        if (p.getRuoloPermessi() != null) {
            dto.setRuoloPermessi(
                p.getRuoloPermessi().stream()
                  .map(DtoMapper::toRuoloPermessoDtoSafe) // safe: nested light
                  .collect(Collectors.toList())
            );
        }
        return dto;
    }

    
    /** Ruolo "light": solo campi base, senza lista associazioni. */
    public static RuoloDto toRuoloDtoLight(Ruolo r) {
        if (r == null) return null;
        RuoloDto dto = new RuoloDto();
        dto.setId(r.getId());
        dto.setNome(r.getNome());
        dto.setAlias(r.getAlias());
        dto.setCreatedAt(r.getCreatedAt());
        dto.setUpdatedAt(r.getUpdatedAt());
        return dto;
    }

    /**
     * Ruolo "withAssoc": include la lista di associazioni ruolo-permesso.
     * Ogni associazione è mappata in modo "safe" (ruolo/permesso light) per evitare ricorsioni.
     */
    public static RuoloDto toRuoloDtoWithAssoc(Ruolo r) {
        if (r == null) return null;
        RuoloDto dto = toRuoloDtoLight(r);
        if (r.getRuoloPermessi() != null) {
            dto.setRuoloPermessi(
                r.getRuoloPermessi().stream()
                  .map(DtoMapper::toRuoloPermessoDtoSafe) // safe: nested light
                  .collect(Collectors.toList())
            );
        }
        return dto;
    }

    
    /**
     * Associazione "safe": mappa RuoloPermesso con:
     * - Ruolo LIGHT (senza lista ruoloPermessi)
     * - Permesso LIGHT (senza lista ruoloPermessi)
     * per evitare strutture annidate infinite.
     */
    public static PermessoRuoloDto toRuoloPermessoDtoSafe(RuoloPermesso rp) {
        if (rp == null) return null;

        PermessoRuoloDto dto = new PermessoRuoloDto();
        dto.setId(rp.getId());

        // Ruolo light
        Ruolo ruolo = rp.getRuolo();
        if (ruolo != null) {
            dto.setRuolo(toRuoloDtoLight(ruolo));
        }

        // Permesso light
        Permesso perm = rp.getPermesso();
        if (perm != null) {
            dto.setPermesso(toPermessoDtoLight(perm));
        }

        return dto;
    }

    
    /**
     * UtenteDto con Ruolo LIGHT (niente lista di associazioni),
     * sufficiente per la maggior parte degli scenari e senza ricorsioni.
     */
    public static UtenteDto toUtenteDto(Utente u) {
        if (u == null) return null;
        UtenteDto dto = new UtenteDto();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCognome(u.getCognome());
        dto.setCodiceFiscale(u.getCodiceFiscale());
        dto.setEmail(u.getEmail());
        dto.setDataNascita(u.getDataNascita());
        dto.setTelefono(u.getTelefono());
        dto.setIndirizzo(u.getIndirizzo());
        dto.setRuolo(toRuoloDtoLight(u.getRuolo())); // ruolo light
        dto.setCreatedAt(u.getCreatedAt());
        dto.setUpdatedAt(u.getUpdatedAt());
        return dto;
    }

    /** UtenteDto con lista cani (light) senza ricorsione su proprietario. */
    public static UtenteDto toUtenteDtoWithCani(Utente u, java.util.List<Cane> cani) {
        UtenteDto dto = toUtenteDto(u);
        if (cani != null) {
            dto.setCani(
                cani.stream()
                    .map(DtoMapper::toCaneDtoLight)
                    .collect(java.util.stream.Collectors.toList())
            );
        }
        return dto;
    }

    /** Razza light. */
    public static RazzaDto toRazzaDtoLight(Razza r) {
        if (r == null) return null;
        RazzaDto dto = new RazzaDto();
        dto.setId(r.getId());
        dto.setNome(r.getNome());
        dto.setCreatedAt(r.getCreatedAt());
        dto.setUpdatedAt(r.getUpdatedAt());
        return dto;
    }
    
    public static RazzaDto toRazzaDtoWhitCane(Razza r) {
   	 if (r == null) return null;
   	 RazzaDto dto = new RazzaDto();
   	 dto.setId(r.getId());
        dto.setNome(r.getNome());
        //ti crei una lista di cani e te li prendi da razza
        List<Cane> cani = r.getCani();System.out.println(cani);
        //per ogni cane ti crei un cane dto e te lo metti in una lista di cani dto
        //la setti nel dto razza
        List<CaneDto> canidto = cani.stream()
       		 .map(DtoMapper::toCaneDtoLight)
       		 .collect(Collectors.toList());
        dto.setCani(canidto);	
       
        /*if (r.getCani() != null) {
            List<CaneDto> caniDto = r.getCani().stream()
                .map(DtoMapper::toCaneDtoLight)
                .collect(Collectors.toList());
            dto.setCani(caniDto);
        }*/
       
        
        dto.setCreatedAt(r.getCreatedAt());
        dto.setUpdatedAt(r.getUpdatedAt());
        return dto;
   }

    /** Cane light: include proprietario (utente light) e razza (light). */
    public static CaneDto toCaneDtoLight(Cane c) {
        if (c == null) return null;
        CaneDto dto = new CaneDto();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setDataNascita(c.getDataNascita());
        dto.setPeso(c.getPeso());
        dto.setPedigree(c.getPedigree());
        dto.setProprietario(toUtenteDto(c.getProprietario())); // utente dto (già light)
        dto.setRazza(toRazzaDtoLight(c.getRazza()));
        dto.setCreatedAt(c.getCreatedAt());
        dto.setUpdatedAt(c.getUpdatedAt());
        return dto;
    }
}
