/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import com.kevin.sample.uim.domain.Resource;

import java.util.List;

public interface ResourceService {
    public List<Resource> getResource();

    public long saveResource(Resource domain);

}
