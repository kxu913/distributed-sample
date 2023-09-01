/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package {{.Package}};
{{- $domain :=.TableInfo.Domain}}
import java.util.List;
import com.kevin.sample.domain.{{$domain}};
public interface {{$domain}}Service {
    public List{{.lt}}{{$domain}}> get{{$domain}}();

    public long save{{$domain}}({{$domain}} domain);

}
