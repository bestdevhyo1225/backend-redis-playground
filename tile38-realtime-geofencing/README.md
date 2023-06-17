# Tile38 실시간 지오펜싱 서버(Redis)를 활용한 간단 예제

## Tile38?

- `Tile38` 은 오픈 소스(MIT 라이선스), `메모리 내 지리 위치 데이터 저장소`, `공간 인덱스 및 실시간 지오펜싱` 서버이다.
- `위도/경도 지점`, `경계 상자`, `XYZ 타일`, `Geohash` 및 `GeoJSON` 을 비롯한 다양한 객체 유형을 지원한다.

## SET

### Syntax

> SET key id [FIELD name value ...] [EX seconds] [NX|XX] (OBJECT geojson)|(POINT lat lon z)|(BOUNDS minlat minlon maxlat
> maxlon)|(HASH geohash)|(STRING value)

### Description

- `id` 의 값을 설정한다. 값이 해당 키/ID에 이미 연결되어 있으면 덮어쓴다.

### Documentation

- [SET](https://tile38.com/commands/set)

## GET

### Syntax

> GET key id [WITHFIELDS] [OBJECT|POINT|BOUNDS|(HASH geohash)]

### Description

- `id` 의 `Object` 를 가져옵니다. 기본 출력 형식은 `GeoJSON` 객체이다.

### Documentation

- [GET](https://tile38.com/commands/get)

## 참고

- [tile38 github](https://github.com/tidwall/tile38)
- [tile38 documentation](https://tile38.com/topics/installation)
