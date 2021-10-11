package br.unip.cc.tcc.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.unip.cc.tcc.controller.dto.ProfileDTO;
import br.unip.cc.tcc.controller.form.ProfileForm;
import br.unip.cc.tcc.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

	@GetMapping("/{id}")
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
		Optional<ProfileDTO> optionalProfile = profileService.findById(id);
		if (optionalProfile.isPresent()) {
			return ResponseEntity.ok(optionalProfile.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/user/id/{id}")
	public ResponseEntity<ProfileDTO> getProfileByUserId(@PathVariable Long id) {
		Optional<ProfileDTO> optionalProfile = profileService.findByUserId(id);
		if (optionalProfile.isPresent()) {
			return ResponseEntity.ok(optionalProfile.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/user/userName/{userName}")
	public ResponseEntity<ProfileDTO> getProfileByUserName(@PathVariable String userName) {
		Optional<ProfileDTO> optionalProfile = profileService.findByUserName(userName);
		if (optionalProfile.isPresent()) {
			return ResponseEntity.ok(optionalProfile.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ProfileDTO> createProfile(@RequestBody @Valid ProfileForm profileForm, UriComponentsBuilder uriComponensts) {
		ProfileDTO profileDTO = profileService.save(profileForm);
		URI uriProfile = uriComponensts.buildAndExpand("/profile/{id}", profileDTO.id()).toUri();
		return ResponseEntity.created(uriProfile).body(profileDTO);
	}
	
	@PutMapping
	public ResponseEntity<ProfileDTO> updateProfile(@RequestBody @Valid ProfileForm profileForm) {
		ProfileDTO optionalProfile = profileService.save(profileForm);
		return ResponseEntity.ok(optionalProfile);
	}
}
