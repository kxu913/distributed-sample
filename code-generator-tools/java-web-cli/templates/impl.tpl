/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package {{.Package}};
{{- $domain :=.TableInfo.Domain}}

import com.kevin.sample.domain.{{$domain}};
import com.kevin.sample.{{.Project}}.mapper.{{$domain}}Mapper;
import com.kevin.sample.{{.Project}}.service.{{$domain}}Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class {{$domain}}ServiceImpl implements {{$domain}}Service {
    private final {{$domain}}Mapper {{.FieldDomain}}Mapper;

    public {{$domain}}ServiceImpl({{$domain}}Mapper {{.FieldDomain}}Mapper) {
        this.{{.FieldDomain}}Mapper = {{.FieldDomain}}Mapper;
    }

    @Override
    public List{{.lt}}{{$domain}}> get{{$domain}}() {
        return {{.FieldDomain}}Mapper.get{{$domain}}();
    }
    @Override
    public long save{{$domain}}({{$domain}} domain) {
        return  {{.FieldDomain}}Mapper.save{{$domain}}(domain);
    }
}
