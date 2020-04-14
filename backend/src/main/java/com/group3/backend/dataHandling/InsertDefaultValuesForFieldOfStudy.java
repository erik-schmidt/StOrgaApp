package com.group3.backend.dataHandling;

import com.group3.backend.model.*;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.InitDataFieldOfStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InsertDefaultValuesForFieldOfStudy implements ApplicationListener<ApplicationReadyEvent> {
    @NonNull
    private final InitDataFieldOfStudyRepository initDataFieldOfStudyRepository;

    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (initDataFieldOfStudyRepository.count() > 0) {
            return;
        }

        DataHandler dataHandler = new DataHandler();
        Set<InitDataFieldOfStudy> fieldOfStudies = dataHandler.getInitValuesFieldsOfStudy();
        for(InitDataFieldOfStudy fieldOfStudy: fieldOfStudies){
            initDataFieldOfStudyRepository.save(fieldOfStudy);
        }

    }
}