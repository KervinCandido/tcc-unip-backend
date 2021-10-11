package br.unip.cc.tcc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.cc.tcc.config.validation.exception.FormErrorException;
import br.unip.cc.tcc.controller.dto.ProfileDTO;
import br.unip.cc.tcc.controller.form.ProfileForm;
import br.unip.cc.tcc.model.Profile;
import br.unip.cc.tcc.repository.ProfileRepository;
import br.unip.cc.tcc.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<ProfileDTO> findById(Long id) {
		Optional<Profile> profileOptional = profileRepository.findById(id);
		return profileOptional.map(ProfileDTO::new);
	}
	
	public ProfileDTO save(ProfileForm profileForm) {
		Profile profile = profileForm.toProfile();
		
		if (!userRepository.existsById(profile.getUser().getId())) {
			throw new FormErrorException("userId", "Usuário informado não existe");
		}
		
		Optional<Profile> alreadyExisting = profileRepository.findByUserId(profile.getUser().getId());
		alreadyExisting.ifPresent(prof -> profile.setId(prof.getId()));
		Profile createdProfile = profileRepository.save(profile);
		
		return new ProfileDTO(createdProfile);
	}

	public Optional<ProfileDTO> findByUserId(Long id) {
		Optional<Profile> profileOptional = profileRepository.findByUserId(id);
		return profileOptional.map(ProfileDTO::new);
	}

	public Optional<ProfileDTO> findByUserName(String userName) {
		Optional<Profile> profileOptional = profileRepository.findByUserUserOpenFireUserName(userName);
		return profileOptional.map(ProfileDTO::new);
	}
}
