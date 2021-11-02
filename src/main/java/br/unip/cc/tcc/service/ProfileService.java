package br.unip.cc.tcc.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import br.unip.cc.tcc.config.validation.exception.FormErrorException;
import br.unip.cc.tcc.controller.dto.ProfileDTO;
import br.unip.cc.tcc.controller.form.ProfileForm;
import br.unip.cc.tcc.model.Profile;
import br.unip.cc.tcc.repository.ProfileRepository;
import br.unip.cc.tcc.repository.UserRepository;
import br.unip.cc.tcc.service.storage.FileSystemStorageService;

@Service
public class ProfileService {

	private static final String PICTURE = "picture.jpg";

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FileSystemStorageService storageService;
	
	public Optional<ProfileDTO> findById(Long id) {
		Optional<Profile> profileOptional = profileRepository.findById(id);
		return profileOptional.map(ProfileDTO::new);
	}
	
	public ProfileDTO save(ProfileForm profileForm) {
		Profile profile = profileForm.toProfile();
		
		if (!userRepository.existsById(profile.getUser().getId())) {
			throw new FormErrorException("userId", "Usuário informado não existe");
		}
		
		boolean hasContent = profileForm.getPhoto() != null && profileForm.getPhoto().getSize() > 0;
		if (hasContent) {
			var photoSubPath = getPhotoProfilePath(profileForm.getUserId());
			storageService.store(profileForm.getPhoto(), photoSubPath, PICTURE);
			profile.setPhoto("/profile/picture/"+ profileForm.getUserId());
		}
		
		Optional<Profile> alreadyExisting = profileRepository.findByUserId(profile.getUser().getId());
		alreadyExisting.ifPresent(prof -> {
			profile.setId(prof.getId());
			if (!hasContent && prof.getPhoto() != null) {
				profile.setPhoto(prof.getPhoto());
			}
		});
		Profile createdProfile = profileRepository.save(profile);
		
		return new ProfileDTO(createdProfile);
	}

	private String getPhotoProfilePath(Long userId) {
		return "/user/" + userId + "/profile/picture";
	}

	public Optional<ProfileDTO> findByUserId(Long id) {
		Optional<Profile> profileOptional = profileRepository.findByUserId(id);
		return profileOptional.map(ProfileDTO::new);
	}

	public Optional<ProfileDTO> findByUserName(String userName) {
		Optional<Profile> profileOptional = profileRepository.findByUserUserName(userName);
		return profileOptional.map(ProfileDTO::new);
	}

	public Optional<Resource> getProfilePicture(Long userId) {
		return Optional.of(storageService.loadAsResource(PICTURE, getPhotoProfilePath(userId)));
	}
}
