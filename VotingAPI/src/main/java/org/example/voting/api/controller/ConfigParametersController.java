package org.example.voting.api.controller;

import org.example.voting.api.dto.ConfigDTO;
import org.example.voting.api.model.ConfigParameters;
import org.example.voting.api.repository.ConfigRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("configs")
public class ConfigParametersController {

    private final ConfigRepository configRepository;

    public ConfigParametersController(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @GetMapping
    public ResponseEntity<ConfigDTO> getConfigParameters(@RequestParam(defaultValue = "numeroDeEmparedados")
                                                          String key) {
        Optional<ConfigParameters> search  = configRepository.findById(key);
        if (search.isPresent()) {
            return ResponseEntity.ok(ConfigDTO.from(search.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveConfigParameters(@RequestBody ConfigDTO dto) {

        var params = new ConfigParameters(dto.key(), dto.value(), dto.type());
        configRepository.save(params);
        return ResponseEntity.ok().build();
    }
}
