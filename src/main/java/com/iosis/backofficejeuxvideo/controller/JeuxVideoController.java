package com.iosis.backofficejeuxvideo.controller;

import com.iosis.backofficejeuxvideo.exception.ResourceNotFoundException;
import com.iosis.backofficejeuxvideo.model.metier.JeuxVideo;
import com.iosis.backofficejeuxvideo.repository.ConsoleRepository;
import com.iosis.backofficejeuxvideo.repository.JeuxVideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class JeuxVideoController {

    private JeuxVideoRepository jeuxVideoRepository;

    private ConsoleRepository consoleRepository;

    // Autorise l'accès par le front
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/jeuxVideos/titre/{jeuVideoTitre}")
    public List<JeuxVideo> getJeuxVideoByTitreContaining(@PathVariable String jeuVideoTitre) {
        log.info("Coucou");
        return jeuxVideoRepository.findJeuxVideoByTitreContaining(jeuVideoTitre);
    }

    @GetMapping("/jeuxVideos/partOfTitre/{jeuVideoTitre}")
    public List<JeuxVideo> getJeuxVideoByPartOfTitre(@PathVariable String jeuVideoTitre) {
        return jeuxVideoRepository.findJeuxVideoByPartOfTitre(jeuVideoTitre);
    }

//    @GetMapping("/jeuxVideos/titre/{jeuVideoTitre}")
//    public List<JeuxVideo> getJeuxVideoByTitre(@PathVariable String jeuVideoTitre) {
//        return jeuxVideoRepository.findJeuxVideoByTitre(jeuVideoTitre);
//    }

    @GetMapping("/jeuxVideos/{jeuxVideoId}")
    public Optional<JeuxVideo> getJeuxVideosById(@PathVariable Long jeuxVideoId) {
        if(!jeuxVideoRepository.existsById(jeuxVideoId)) {
            throw new ResourceNotFoundException("Console not found with id : " + jeuxVideoId);
        }
        return jeuxVideoRepository.findById(jeuxVideoId);
    }

    @GetMapping("/jeuxVideos")
    public Page<JeuxVideo> getJeuxVideos(Pageable pageable) {
        return jeuxVideoRepository.findAll(pageable);
    }

    @GetMapping("/consoles/{consoleId}/jeuxVideos")
    public List<JeuxVideo> getJeuxVideosByConsoleId(@PathVariable Long consoleId) {
        return jeuxVideoRepository.findByConsoleId(consoleId);
    }

    @PostMapping("/consoles/{consoleId}/jeuxVideos")
    public JeuxVideo addJeuxVideo(@PathVariable Long consoleId,
                                  @Valid @RequestBody JeuxVideo jeuxVideo) {

        log.info(jeuxVideo + "");
        return consoleRepository.findById(consoleId)
                .map(console -> {
                    jeuxVideo.setConsole(console);
                    return jeuxVideoRepository.save(jeuxVideo);
                }).orElseThrow(() -> new ResourceNotFoundException("Console not found with id : " + consoleId));
    }

    @PutMapping("/consoles/{consoleId}/jeuxVideos/{jeuxVideoId}")
    public JeuxVideo updateJeuxVideo(@PathVariable Long consoleId,
                                     @PathVariable Long jeuxVideoId,
                                     @Valid @RequestBody JeuxVideo jeuxVideoRequest) {
        if(!consoleRepository.existsById(consoleId)) {
            throw new ResourceNotFoundException("Console not found with id : " + consoleId);
        }

        return jeuxVideoRepository.findById(jeuxVideoId)
                .map(jeuxVideo -> {
                    jeuxVideo.setTitre(jeuxVideoRequest.getTitre());
                    jeuxVideo.setDeveloppeur(jeuxVideoRequest.getDeveloppeur());
                    jeuxVideo.setEditeur(jeuxVideoRequest.getEditeur());
                    jeuxVideo.setStatut(jeuxVideoRequest.isStatut());
                    jeuxVideo.setNote(jeuxVideoRequest.getNote());
                    jeuxVideo.setDescription(jeuxVideoRequest.getDescription());
                    return jeuxVideoRepository.save(jeuxVideo);
                }).orElseThrow(() -> new ResourceNotFoundException("JeuxVideo not found with id : " + jeuxVideoId));
    }

    @DeleteMapping("/consoles/{consoleId}/jeuxVideos/{jeuxVideoId}")
    public ResponseEntity<?> deleteJeuxVideo(@PathVariable Long consoleId,
                                             @PathVariable Long jeuxVideoId) {
        if(!consoleRepository.existsById(consoleId)) {
            throw new ResourceNotFoundException("Console not found with id : " + consoleId);
        }

        return jeuxVideoRepository.findById(jeuxVideoId)
                .map(jeuxVideo -> {
                    jeuxVideoRepository.delete(jeuxVideo);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("JeuxVideo not found with id : " + jeuxVideoId));
    }

    @Autowired
    public void setJeuxVideoRepository(JeuxVideoRepository jeuxVideoRepository) {
        this.jeuxVideoRepository = jeuxVideoRepository;
    }

    @Autowired
    public void  setConsoleRepository(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }
}
