/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package {{.Package}};
{{- $domain :=.TableInfo.Domain}}

import com.google.common.base.Strings;

import com.kevin.sample.domain.{{$domain}};
import com.kevin.sample.{{.Project}}.service.{{$domain}}Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class {{$domain}}Controller {

    private final {{$domain}}Service {{.FieldDomain}}Service;

    @GetMapping("/{{.FieldDomain}}")
    public List{{.lt}}{{$domain}}> get{{$domain}}() {
        return {{.FieldDomain}}Service.get{{$domain}}();
    }

    @PostMapping("/{{.FieldDomain}}")
    public long save{{$domain}}(@RequestBody {{$domain}} domain) {
        return  {{.FieldDomain}}Service.save{{$domain}}(domain);
    }
    
}
