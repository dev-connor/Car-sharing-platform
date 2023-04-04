# 로컬 개발환경 세팅

## 필요

- docker api version 1.41 이상

## Infra 구축 (rdbms)

```shell
cd ./docker
sh start-local-infra.sh
```

## Infra 삭제
```shell
cd ./docker
sh stop-local-infra.sh
```