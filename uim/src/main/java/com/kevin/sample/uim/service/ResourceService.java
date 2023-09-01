/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import java.util.List;
import com.kevin.sample.domain.Resource;
public interface ResourceService {
    public List<Resource> getResource();

    public long saveResource(Resource domain);

}
