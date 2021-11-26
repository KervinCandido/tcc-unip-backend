package br.unip.cc.tcc.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import br.unip.cc.tcc.config.validation.exception.FormErrorException;
import br.unip.cc.tcc.controller.dto.ProfileDTO;
import br.unip.cc.tcc.controller.form.ProfileForm;
import br.unip.cc.tcc.model.MovieGenre;
import br.unip.cc.tcc.model.MusicalGenre;
import br.unip.cc.tcc.model.Profile;
import br.unip.cc.tcc.repository.MovieGenreRepository;
import br.unip.cc.tcc.repository.MusicalGenreRepository;
import br.unip.cc.tcc.repository.ProfileRepository;
import br.unip.cc.tcc.repository.UserRepository;
import br.unip.cc.tcc.service.storage.FileSystemStorageService;
import br.unip.cc.tcc.controller.dto.FavoriteMovieGenreDTO;
import br.unip.cc.tcc.controller.dto.FavoriteMusicalGenreDTO;

@Service
public class ProfileService {

	private static final String PICTURE = "picture.jpg";

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MusicalGenreRepository musicalRepository;
	
	@Autowired
	private MovieGenreRepository movieRepository;
	
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
		
		Arrays.asList(profileForm.getFavoriteMusicalGenre()).stream().forEach(musicGenreId -> {
			Optional<MusicalGenre> optional = musicalRepository.findById(musicGenreId);
			optional.ifPresent(profile::addFavoriteMusicGenre);
		});
		
		Arrays.asList(profileForm.getFavoriteMovieGenre()).stream().forEach(movieGenreId -> {
			Optional<MovieGenre> optional = movieRepository.findById(movieGenreId);
			optional.ifPresent(profile::addFavoriteMovieGenre);
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
	

	public List<FavoriteMusicalGenreDTO> getMusicalGenre() {
		return musicalRepository.findAll().stream().map(FavoriteMusicalGenreDTO::new).toList();
	}
	
	public List<FavoriteMovieGenreDTO> getMovieGenre() {
		return movieRepository.findAll().stream().map(FavoriteMovieGenreDTO::new).toList();
	}
}
