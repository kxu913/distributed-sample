/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.domain;

import lombok.Data;

import java.util.Date;
{{- $domain :=.TableInfo.Domain}}
@Data
public class {{$domain}} {
{{range $index, $field := .TableInfo.Fields}}
    private {{$field.JavaType}} {{$field.FieldCaml}};
{{end}}

}
