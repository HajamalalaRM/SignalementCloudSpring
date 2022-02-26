package main.projetCloud.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.projetCloud.domain.Region;
import main.projetCloud.domain.Signalement;
import main.projetCloud.domain.TypeSignalement;
import main.projetCloud.domain.User;
import main.projetCloud.repositories.RegionRepository;
import main.projetCloud.repositories.SignalementRepository;
import main.projetCloud.repositories.TypeSignalementRepository;
import main.projetCloud.repositories.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/signalement")
public class SignalementResource {
	
	@Autowired
	SignalementRepository signalementRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	TypeSignalementRepository typeSignalementRepository; 
	
	@GetMapping("/allsignal")
	public List<Signalement> getSignalements(){
		List<Signalement> liste = signalementRepository.allSignalement();
		return liste;
	}

	@PostMapping("/insertsignal")
	public Signalement createSignalement(
			@RequestParam(name="usernom") String usernom,
			@RequestParam String regionname, 
			@RequestParam String type,
			@RequestParam Double longitude,
			@RequestParam Double latitude,
			@RequestParam String statu,
			@RequestParam String commentaire,
			@RequestParam String isAffecte
			) {
		Signalement signalement = new Signalement();
		User user = userRepository.findByName(usernom);
		Region region = regionRepository.regionByNom(regionname);
		TypeSignalement typesign = typeSignalementRepository.typeSignalementBydesc(type);
		
		signalement.setIdUtilisateur(user.getId());
		signalement.setIdRegion(region.getId());
		signalement.setIdTypeSignalement(typesign.getId());		
		signalement.setLongitude(longitude);
		signalement.setLatitude(latitude);
		signalement.setStatut(statu);		
		signalement.setIsAffecte(isAffecte);

		signalementRepository.createSignalement(usernom, regionname, type, longitude, latitude, statu, commentaire, isAffecte);
		
		return null;
	}
}
