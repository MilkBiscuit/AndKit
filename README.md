# HttpProject
A hotplate project for Apps which have various Restful Apis, written in Kotlin

## Library used:
Retrofit2, RxJava2, Realm, okhttp-oauth2-client, JUnit5 etc.

## Provide several utility methods/extensions, e.g.
### StringUtil
isValidColourHex, getUrlsFromString
### StringExtension
parseDoubleNum, parseIntNum, equalIgnoreCase, toBool
### JsonUtil
isValidJsonObject, isValidJsonArray
### DateUtil
parseYearMonthDate, parseFullDateTime, parseDate, formatDate, getToday, getTomorrow

### Unit tests for above util methods.
app/src/test/java/com/cheng/httpproject/util

## Api examples used in the project:
### BibleApi
https://docs.api.bible/reference

### Open Weather Map
https://openweathermap.org/api

### Infoodle
https://help.infoodle.com/api

### Plexure, search store Api:
GET https://mopjapaneastgateway.plexure.io/store/v2/stores?latitude=26.333351598841787&longitude=127.79896146273005&radius=100000000&size=100

### Some unit test about Api too
app/src/test/java/com/cheng/httpproject/api/