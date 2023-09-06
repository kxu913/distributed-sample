/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service.impl;

import com.kevin.sample.uim.domain.Resource;
import com.kevin.sample.uim.mapper.ResourceMapper;
import com.kevin.sample.uim.service.ResourceService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {
    private final ResourceMapper resourceMapper;

    public ResourceServiceImpl(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public List<Resource> getResource() {
        return resourceMapper.getResource();
    }
    @Override
    public long saveResource(Resource domain) {
        return  resourceMapper.saveResource(domain);
    }
}
