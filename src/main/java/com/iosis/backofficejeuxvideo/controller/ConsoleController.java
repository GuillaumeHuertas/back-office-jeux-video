package com.iosis.backofficejeuxvideo.controller;

import com.iosis.backofficejeuxvideo.exception.ResourceNotFoundException;
import com.iosis.backofficejeuxvideo.model.Console;
import com.iosis.backofficejeuxvideo.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConsoleController {

    private ConsoleRepository consoleRepository;

    @GetMapping("/consoles/searchByName/{consoleName}")
    public List<Console> getConsoleByNameContaining(@PathVariable String consoleName) {
        return consoleRepository.findConsoleByNameContaining(consoleName);
    }

    @GetMapping("/consoles/{consoleCode}")
    public List<Console> getConsoleByCode(@PathVariable String consoleCode) {
        return consoleRepository.findByConsoleCode(consoleCode);
    }

    @GetMapping("/consoles")
    public Page<Console> getConsoles(Pageable pageable) {
        return consoleRepository.findAll(pageable);
    }

    @PostMapping("/consoles")
    public Console createConsole(@Valid @RequestBody Console console) {
        return consoleRepository.save(console);
    }

    @PutMapping("/consoles/{consoleId}")
    public Console updateConsole(@PathVariable Long consoleId,
                                 @Valid @RequestBody Console consoleRequest) {
        return consoleRepository.findById(consoleId)
                .map(console -> {
                    console.setName(consoleRequest.getName());
                    console.setCode(consoleRequest.getCode());
                    console.setConstructeur(consoleRequest.getConstructeur());
                    return consoleRepository.save(console);
                }).orElseThrow(() -> new ResourceNotFoundException("Console not found with id : " + consoleId));
    }

    @DeleteMapping("/consoles/{consoleId}")
    public ResponseEntity<?> deleteConsole(@PathVariable Long consoleId) {
        return consoleRepository.findById(consoleId)
        .map(console -> {
            consoleRepository.delete(console);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Console not found with id : " + consoleId));
    }

    @Autowired
    public void  setConsoleRepository(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }
}
