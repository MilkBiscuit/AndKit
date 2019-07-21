# Plexure Challenge
plexure-app-debug.apk is uploaded for your convenience

## Screenshot
![screenshot](https://user-images.githubusercontent.com/20746964/61588593-8565fe00-abf2-11e9-9ed2-5a82083a4b67.png)

## Completed features
Basic feature set
Data handling - Filter
Data handling - Sort
Data handling - Validation, it is hardcoded as enabled, any store further than 1000km is grey out

## Library used:
Retrofit2, RxJava2, Realm, JUnit5 etc.

## Provide several utility methods/extensions, e.g.
### StringUtil
isValidColourHex, getUrlsFromString
### StringExtension
parseDoubleNum, parseIntNum, equalIgnoreCase, toBool
### JsonUtil
isValidJsonObject, isValidJsonArray
### DateUtil
parseYearMonthDate, parseFullDateTime, parseDate, formatDate, getToday, getTomorrow

### Unit tests for above util methods
app/src/test/java/com/cheng/httpproject/util

## Api examples used in the project:
### Search store API:
https://mopjapaneastgateway.plexure.io/store/v2/stores?latitude=26.333351598841787&longitude=127.79896146273005&radius=100000000&size=100

### Search store API unit test:
app/src/test/java/com/cheng/httpproject/api/PlexureApiUnitTest