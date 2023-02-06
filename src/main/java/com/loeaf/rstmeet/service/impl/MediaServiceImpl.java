package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.Media;
import com.loeaf.rstmeet.repository.MediaRepository;
import com.loeaf.rstmeet.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl
        extends ServiceImpl<MediaRepository, Media, String>
        implements MediaService {
    private final MediaRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Media());
    }
}
