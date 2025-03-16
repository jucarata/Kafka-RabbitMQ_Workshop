package co.edu.icesi.service;

import co.edu.icesi.dto.ClassesDTO;
import co.edu.icesi.dto.ClassesResponseDTO;
import co.edu.icesi.dto.TrainerDTO;
import co.edu.icesi.exceptions.NoTrainerFoundException;
import co.edu.icesi.mappers.ClassMapper;
import co.edu.icesi.persistence.model.Classes;
import co.edu.icesi.persistence.repository.ClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    private final RestTemplate restTemplate;

    @Value("${trainer.service.url}")
    private String trainerServiceUrl;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, ClassMapper classMapper, RestTemplate restTemplate) {
        this.classRepository = classRepository;
        this.classMapper = classMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public boolean scheduleClass(ClassesDTO classDTO) throws NoTrainerFoundException {
        Classes classes = classMapper.toClass(classDTO);

        TrainerDTO trainerDTO = fetchTrainer(classes.getTrainerID().getTrainerId());

        if (trainerDTO == null) {
            throw new NoTrainerFoundException();
        }

        Classes savedClass = classRepository.save(classes);
        return savedClass.getId() != null;
    }

    @Override
    @Transactional
    public List<ClassesResponseDTO> getClasses() {
        List<Classes> allClasses = classRepository.findAll();

        return allClasses.stream()
                .map(classes -> CompletableFuture.supplyAsync(() -> fetchTrainer(classes)))
                .map(CompletableFuture::join)
                .toList();
    }

    private ClassesResponseDTO fetchTrainer(Classes classes) {
        TrainerDTO trainerDTO = fetchTrainer(classes.getTrainerID().getTrainerId());

        return classMapper.toDTO(classes, trainerDTO);
    }

    private TrainerDTO fetchTrainer(Long id){
        String url = trainerServiceUrl + "/entrenadores/" + id;

        return restTemplate.getForObject(url, TrainerDTO.class);
    }
}