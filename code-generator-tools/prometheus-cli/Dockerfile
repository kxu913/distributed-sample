FROM golang:1.19-alpine AS build


WORKDIR /work/

RUN go env -w  GOPROXY=https://goproxy.cn,direct

RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY go.* /work/
COPY *.tpl /work/
COPY main.go /work/
COPY src /work/src/

WORKDIR /work/
RUN go mod download

RUN go build -o .

FROM alpine:latest

WORKDIR /

COPY --from=build /work /work

EXPOSE 9000

WORKDIR /work/

ENTRYPOINT ["./prometheus-cli"]
