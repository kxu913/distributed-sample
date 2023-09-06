/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package {{.Package}};
{{- $m :=.TableInfo.LastFieldIndex}}{{- $db :=.TableInfo.DBTable}}{{- $domain :=.TableInfo.Domain}}
import com.kevin.sample.domain.{{$domain}};
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface {{$domain}}Mapper {

    @Results(id = "{{$domain}}", value = {
    {{range $index, $field := .TableInfo.Fields}}
            @Result(property = "{{$field.FieldCaml}}",column = "{{$field.Field}}"),{{end}}
    })

    @Select("select * from {{$db}}")
    public List{{.lt}}{{$domain}}> get{{$domain}}();

    @Insert("insert into {{$db}}({{range $index, $field := .TableInfo.Fields}}{{ if lt $index $m}}{{$field.Field}}, {{else}} {{$field.Field}}{{end}}{{end}}) values({{range $index, $field := .TableInfo.Fields}}{{ if lt $index $m}}{{.left}}{{$field.FieldCaml}}{{.right}}, {{else}} {{.left}}{{$field.FieldCaml}}{{.right}}{{end}}{{end}}) returning {{.TableInfo.Id}}" )
    @Options(useGeneratedKeys = true,keyProperty = "{{.TableInfo.IdCaml}}", keyColumn = "{{.TableInfo.Id}}")
    public long save{{$domain}}({{$domain}} domain);
}
