package com.cheng.httpproject.constant

/**
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
object PxParams {

    //################################## Parameter keys ##################################

    const val KEY_GRANT_TYPE = "grant_type"
    const val KEY_PASSWORD = "password"
    const val KEY_RETURN_CROSS_REFERENCES = "returnCrossReferences"
    const val KEY_RETURN_CONSUMER_INFO = "returnConsumerInfo"
    const val KEY_CONSUMER_INFO = "consumerInfo"
    const val KEY_CROSS_REFERENCES = "crossReferences"

    // Because of a bug in the backend APIs we need to have 2 username params, one with capital N and one without.
    // Pay attention to which one you should use for each API by checking the API docs: https://vmobglobal.atlassian.net/wiki/display/APIDocs/Consumer+API
    const val KEY_USERNAME = "username"
    const val KEY_USER_NAME = "userName"

    const val KEY_CURRENT_MERCHANT_ID = "currentMerchantId"
    const val KEY_GET_MERCHANT_ID = "merchantId"
    const val KEY_TAG_REFERENCE = "tagReference"

    const val KEY_DEFAULT_TO_CONSUMER = "defaultToConsumer"
    const val KEY_DEFAULT_TO_CONSUMER_USERNAME = "defaultToConsumerUsername"
    const val KEY_DEFAULT_TO_CONSUMER_PASSWORD = "defaultToConsumerPassword"
    const val KEY_ALLOW_AUTO_CONSUMER_CREATION = "allowAutoConsumerCreation"

    const val KEY_EMAIL_REGISTRATION = "emailRegistration"
    const val KEY_MOBILE_REGISTRATION = "mobilePhoneNumberRegistration"

    const val KEY_EMAIL_ADDRESS = "emailAddress"
    const val KEY_ACTION = "action"
    const val KEY_FIRST_NAME = "firstName"
    const val KEY_LAST_NAME = "lastName"
    const val KEY_FULL_NAME = "fullName"
    const val KEY_GENDER = "gender"
    const val KEY_DATE_OF_BIRTH = "dateOfBirth"
    const val KEY_HOME_CITY = "homeCity"
    const val KEY_MOBILE_PHONE_NUMBER = "mobilePhoneNumber"
    const val KEY_MOBILE_NUMBER = "mobileNumber"
    const val KEY_POSTCODE = "postcode"

    const val KEY_EMAIL_TEMPLATE_CODE = "emailTemplate"
    const val KEY_INBOUND_CHANNEL = "inboundChannel"

    const val KEY_CONSENT_GRANTED = "consentToStoreAndProcess"
    const val KEY_SERVICES = "services"

    const val KEY_TOKEN_TYPE = "token_type"
    const val KEY_ACCESS_TOKEN = "access_token"
    const val KEY_JWT_ACCESS_TOKEN = "jwtAccessToken"
    const val KEY_JWT_REFRESH_TOKEN = "jwtRefreshToken"

    const val KEY_SYSTEM_TYPE = "systemType"
    const val KEY_EXTERNAL_ID = "externalId"

    const val KEY_INCLUDE_HIDDEN = "includeHidden"
    const val KEY_EXTENDED_DATA = "extendedData"
    const val KEY_CHANNEL = "channel"
    const val KEY_PLACEMENT = "placement"
    const val KEY_LIMIT = "limit"
    const val KEY_OFFSET = "offset"
    const val KEY_LAST_UPDATED = "lastUpdated"
    const val KEY_IGNORE_DAY_FILTER = "ignoreDayFilter"
    const val KEY_IGNORE_DAILY_TIME_FILTER = "ignoreDailyTimeFilter"

    const val KEY_FIELDS = "fields"
    const val KEY_GIFT_ID = "giftId"
    const val KEY_GIFT_CODE = "giftCode"

    const val KEY_CONTENT = "content"
    const val KEY_CONTENT_ID = "contentId"
    const val KEY_CONTENT_TYPE = "contentType"

    const val KEY_TAGS = "tagValueReferenceCodes"
    const val KEY_TAGS_ADD = "tagValueAddReferenceCodes"
    const val KEY_TAGS_REMOVE = "tagValueRemoveReferenceCodes"

    const val KEY_NEW_PASSWORD = "newPassword"
    const val KEY_CURRENT_PASSWORD = "currentPassword"

    const val KEY_TOKEN = "token"

    const val KEY_SOCIAL_SOURCE = "social_source"
    const val KEY_CREDENTIAL = "credential"
    const val KEY_UPDATE_CONSUMER_INFO = "updateConsumerInfo"

    const val KEY_OFFER_ID = "offerId"
    const val KEY_OFFER_INSTANCE_UNIQUE_ID = "offerInstanceUniqueId"
    const val KEY_PROMO_IMAGE_PATH = "promoImagePath"
    const val KEY_PROMO_IMAGE_DESCRIPTION = "promoImageDescription"
    const val KEY_ALT_IMAGE_PATH = "altImagePath"
    const val KEY_ALT_IMAGE_DESCRIPTION = "altImageDescription"

    const val KEY_PAYMENT_PROVIDER_SUCCESS_URL = "paymentProviderSuccessUrl"

    const val KEY_LOYALTY_CARD_ID = "loyaltyCardId"
    const val KEY_POINTS_REQUESTED = "pointsRequested"
    const val KEY_POINTS_REQUESTS = "pointsRequests"
    const val KEY_TRANSACTION_ID = "transactionId"
    const val KEY_AUTO_ACTIVATE_REWARD = "autoActivateReward"
    const val KEY_FILL_MULTIPLE_CARDS = "fillMultipleCards"
    const val KEY_LOYALTY_CARD_TYPE = "loyaltyCardType"
    const val KEY_POINTS_EXPIRY_IN_DAYS = "pointsExpiryDays"
    const val KEY_EXPIRING_WITHIN_DAYS = "expiringWithinDays"
    const val KEY_EXPIRY_DAY = "expiryDayUtc"

    const val KEY_RATING = "rating"
    const val KEY_FEEDBACK_TEXT = "feedbackText"
    const val KEY_UNIQUE_ID = "uniqueIdentifier"

    const val KEY_START_DATE = "startDate"
    const val KEY_END_DATE = "endDate"

    const val KEY_CATEGORY_ID = "categoryId"

    const val KEY_INCLUDE_EXCLUDED_CATEGORIES = "includeConsumerExcludedCategories"

    const val KEY_LATITUDE = "latitude"
    const val KEY_LONGITUDE = "longitude"

    const val KEY_IS_RANKED_SEARCH = "isRankedSearch"
    const val KEY_KEYWORD = "keyword"
    const val KEY_MERCHANT_ID = "merchantId"
    const val KEY_ORGANISATION_ID = "organisationId"
    const val KEY_ORDER_BY = "orderBy"
    const val KEY_ORDER_DIRECTION = "orderDirection"

    const val KEY_WEIGHTED_CONTENT_ID = "weightedContentId"
    const val KEY_TYPE = "type"

    const val KEY_TAG_EXPRESSION = "tagExpression"
    const val KEY_INCLUDE = "include"
    const val KEY_DATE_TIME = "dateTime"
    const val KEY_REGION_ID = "regionId"
    const val KEY_INCLUDE_VENUE_OFFERS = "includeVenueRelatedOffers"
    const val KEY_VENUE_ID = "venueId"
    const val KEY_TAG_CODE = "tagCode"

    const val KEY_IGNORE_OFFER_START_END_DATES = "ignoreOfferStartEndDates"

    const val KEY_NUMERIC_TOKEN = "numericToken"

    const val KEY_ACTIVITIES = "activities"

    const val KEY_ID = "id"
    const val KEY_SOURCE_ACTIVITY_TIME = "sourceActivityTime"
    const val KEY_SOURCE_ACTIVITY_TIME_ZONE_OFFSET = "sourceActivityTimeZoneOffset"
    const val KEY_ACTION_TYPE_CODE = "actionTypeCode"
    const val KEY_ACTION_CODE = "actionCode"
    const val KEY_ACTION_VALUE_1 = "actionValue1"
    const val KEY_ACTION_VALUE_2 = "actionValue2"
    const val KEY_ACTION_VALUE_3 = "actionValue3"
    const val KEY_LOCATION_SOURCE = "locationSource"
    const val KEY_LOCATION_ACCURACY = "locationAccuracy"
    const val KEY_ITEM_ID = "itemId"
    const val KEY_ITEM_CODE = "itemCode"
    const val KEY_BEACONS = "beacons"

    const val KEY_IS_GIFTABLE = "isGiftable"
    const val KEY_IS_A_GIFT = "isAGift"
    const val KEY_GIFTED_BY = "giftedBy"
    const val KEY_GIFTED_COPY = "giftedCopy"

    const val KEY_PROXIMITY_UUID = "proximityUuid"
    const val KEY_MAJOR_NUMBER = "majorNumber"
    const val KEY_MINOR_NUMBER = "minorNumber"
    const val KEY_SIGNAL_STRENGTH = "signalStrength"

    const val KEY_OFFERS = "offers"
    const val KEY_ADVERTISEMENTS = "advertisements"
    const val KEY_VENUES = "venues"
    const val KEY_MERCHANTS = "merchants"

    const val KEY_VERIFICATION_TOKEN = "verificationToken"
    const val KEY_EXPIRY_DATE = "expiryDate"

    const val KEY_CHANNEL_CODE = "channelCode"
    const val KEY_CLICK_THROUGH_URL = "clickThroughUrl"
    const val KEY_DATE_CREATED = "dateCreated"
    const val KEY_DATE_MODIFIED = "dateModified"
    const val KEY_EVENT_START_DATE_TIME = "eventStartDateTime"
    const val KEY_DAYS_OF_WEEK = "daysOfWeek"
    const val KEY_IS_ACTIVE = "isActive"
    const val KEY_PLACEMENT_CODE = "placementCode"
    const val KEY_CONTENT_URL = "contentUrl"
    const val KEY_DISTANCE_TO_CLOSEST_VENUE = "distanceToClosestVenue"
    const val KEY_CLOSEST_VENUE = "closestVenue"
    const val KEY_CONTENT_TAG_REFERENCE_CODES = "contentTagReferenceCodes"

    const val KEY_CARD_IMAGE = "cardImage"
    const val KEY_CARD_IMAGE_DESCRIPTION = "cardImageDescription"
    const val KEY_MAX_INSTANCES = "maxInstances"
    const val KEY_SUBTITLE = "subtitle"
    const val KEY_INSTRUCTIONS = "instructions"
    const val KEY_TERMS_AND_CONDITIONS = "termsAndConditions"
    const val KEY_POINTS_REQUIRED = "pointsRequired"
    const val KEY_INITIAL_POINTS = "initialPoints"
    const val KEY_MAX_POINTS_PER_DAY = "maxPointsPerDay"
    const val KEY_MAX_POINTS_REQUESTS_PER_DAY = "maxPointsRequestsPerDay"
    const val KEY_INSTANCES_AVAILABLE = "instancesAvailable"
    const val KEY_ASSETS_PATH = "assetsPath"
    const val KEY_INSTANCES = "instances"
    const val KEY_CATEGORY_NAME = "categoryName"
    const val KEY_REASON_CODES = "reasonCodes"

    const val KEY_INSTANCE_ID = "instanceId"
    const val KEY_OUTCOME_CODE = "outcomeCode"
    const val KEY_OUTCOME_DESCRIPTION = "outcomeDescription"
    const val KEY_POINTS_BALANCE = "pointsBalance"
    const val KEY_POINTS = "points"
    const val KEY_POINTS_ALLOCATED = "pointsAllocated"
    const val KEY_POINT_CREATION_SUMMARY = "pointCreationSummary"
    const val KEY_IS_POINTS_EXPIRY = "isPointsExpiry"

    const val KEY_REDEEMED_OFFER_ID = "redeemedOfferId"
    const val KEY_REDEMPTION_ID = "redemptionId"

    const val KEY_LOYALTY_CARD_INSTANCE = "loyaltyCardInstance"
    const val KEY_DATE_REQUESTED = "dateRequested"

    const val KEY_NAME = "name"
    const val KEY_DESCRIPTION = "description"
    const val KEY_IS_HIDDEN = "isHidden"
    const val KEY_ADDRESS_LINE_1 = "addressLine1"
    const val KEY_ADDRESS_LINE_2 = "addressLine2"
    const val KEY_ADDRESS_LINE_3 = "addressLine3"
    const val KEY_PHONE_NUMBER = "phoneNumber"
    const val KEY_POST_CODE = "postCode"
    const val KEY_WEBSITE_URL = "websiteUrl"

    const val KEY_SORT_ORDER = "sortOrder"

    const val KEY_BURNT_COUNT = "burntCount"
    const val KEY_CODE_TYPE = "codeType"
    const val KEY_GIFT_BATCH_ID = "giftBatchId"
    const val KEY_GIFTED_ON_DATE = "giftedOnDate"
    const val KEY_IMAGE_ALT = "imageAlt"
    const val KEY_IMAGE_ALT_DESCRIPTION = "imageAltDescription"
    const val KEY_IS_MERCHANT_FAVOURITE = "isMerchantFavourite"
    const val KEY_IS_PREMIUM_PLACEMENT = "isPremiumPlacement"
    const val KEY_IS_REPEATABLE_OFFER = "isRespawningOffer"
    const val KEY_IS_REWARD = "isReward"
    const val KEY_LAST_UPDATED_AT = "lastUpdatedAt"
    const val KEY_PAYMENT_AMOUNT = "paymentAmount"
    const val KEY_PAYMENT_TAX_RATE = "paymentTaxRate"
    const val KEY_PAYMENT_TYPE = "paymentType"
    const val KEY_REDEMPTION_COUNT = "redemptionCount"
    const val KEY_REPEAT_DAYS_NUMBER = "respawnsInDays"
    const val KEY_REPEAT_LIMIT = "respawnLimit"
    const val KEY_VENUE_IDS = "venueIds"
    const val KEY_VENUE_EXTERNAL_IDS = "venueExternalIds"
    const val KEY_LAST_REDEEMED_AT = "lastRedeemedAt"
    const val KEY_LAST_BURNT_AT = "lastBurntAt"
    const val KEY_CODE_EXPIRY_IN_MINUTES = "codeExpiryInMinutes"
    const val KEY_POINT_VALUE = "pointValue"

    const val KEY_REDEMPTION_IMAGE = "redemptionImage"
    const val KEY_REDEMPTION_TEXT = "redemptionText"
    const val KEY_REDEMPTION_TEXT_EXPIRY = "redemptionTextExpiry"
    const val KEY_BURNT = "burnt"
    const val KEY_REDEEMED_AT = "redeemedAt"
    const val KEY_BURNT_AT = "burntAt"

    const val KEY_ADDRESS = "address"
    const val KEY_PHONE = "phone"
    const val KEY_EMAIL = "email"
    const val KEY_WEB = "web"
    const val KEY_CITY = "city"
    const val KEY_COUNTRY = "country"
    const val KEY_FEATURES = "features"
    const val KEY_DISTANCE_FROM_CURRENT_LOCATION = "distanceFromCurrentLocation"
    const val KEY_OPEN_HOURS = "openHours"

    const val KEY_DAY = "day"
    const val KEY_START = "start"
    const val KEY_END = "end"
    const val KEY_NOTE = "note"

    const val KEY_DELETED_VENUE_IDS = "DeletedVenueIds"
    const val KEY_MODIFIED_VENUE_LIST = "ModifiedVenueList"

    const val KEY_WEIGHT = "weight"
    const val KEY_ADVERTISEMENT = "advertisement"
    const val KEY_OFFER = "offer"
    const val KEY_VENUE = "venue"

    const val KEY_TITLE = "title"
    const val KEY_IMAGE = "image"

    const val KEY_REFERENCE_CODE = "referenceCode"

    const val KEY_WEIGHTING = "weighting"
    const val KEY_IMAGE_DESCRIPTION = "imageDescription"
    const val KEY_IS_AVAILABLE_ALL_STORES = "isAvailableAllStores"
    const val KEY_DAILY_START_TIME = "dailyStartTime"
    const val KEY_DAILY_END_TIME = "dailyEndTime"

    const val KEY_RADIUS = "radius"

    const val KEY_LOCATION_LAT = "locationLat"
    const val KEY_LOCATION_LONG = "locationLong"
    const val KEY_STATE_ID = "stateId"

    const val KEY_CONFIGURATION_API_URL = "configurationApiUrl"
    const val KEY_ACTIVITY_API_URL = "activityApiUrl"
    const val KEY_CONSUMER_API_URL = "consumerApiUrl"
    const val KEY_LOCATION_API_URL = "locationApiUrl"
    const val KEY_OFFER_API_URL = "offerApiUrl"
    const val KEY_ADVERTISEMENT_API_URL = "advertisementApiUrl"
    const val KEY_OFFER_IMAGE_PREFIX = "offerImagePrefix"
    const val KEY_GEO_TILE_SIZE_IN_DEGREES = "geoTileSizeInDegrees"
    const val KEY_BEACON_REGIONS = "beaconRegions"
    const val KEY_EXTENDED_PARAMETERS = "extendedParameters"
    const val KEY_AUTHORIZATION_API_URL = "authorizationApiUrl"
    const val KEY_TENANT_ID = "tenantId"

    const val KEY_FACEBOOK_OAUTH2 = "facebookOAuth2"
    const val KEY_FACEBOOK_ACCESS_TOKEN = "facebookAccessToken"
    const val KEY_TWITTER_ACCESS_TOKEN = "twitterAccessToken"

    const val KEY_BRANCHES = "branches"
    const val KEY_SUB_CATEGORIES = "subCategories"
    const val KEY_NUM_ACTIVE_OFFERS = "numActiveOffers"

    const val KEY_CONSUMER_ENABLED = "consumerEnabled"

    const val KEY_GEOFENCES = "geofences"
    const val KEY_CENTER_TILE_ID = "centerTileId"
    const val KEY_APP_KEY = "appKey"
    const val KEY_DISTANCE_IN_METERS = "distanceInMeters"


    //################################## Parameter values ##################################

    const val VALUE_DEFAULT_GRANT_TYPE = "password"

    const val VALUE_PASSWORD_RESET = "emailPasswordReset"

    const val VALUE_ASC = "asc"
    const val VALUE_DESC = "desc"
    const val SORT_CRITERIA_WEIGHT = "Weighting"
    const val SORT_CRITERIA_TITLE = "Title"
    const val SORT_CRITERIA_LAST_UPDATE_AT = "LastUpdatedAt"
    const val SORT_CRITERIA_START_DATE = "StartDate"

    const val VALUE_OFFER = "offer"
    const val VALUE_ADVERTISEMENT = "advertisement"
    const val VALUE_VENUE = "venue"
    const val VALUE_MERCHANT = "merchant"

    const val VALUE_GEOFENCE_ENTRY = "geofence-entry"
    const val VALUE_GEOFENCE_EXIT = "geofence-exit"

    const val VALUE_ITEM_CODE_OFFER = "O"
    const val VALUE_ITEM_CODE_ADVERTISEMENT = "A"
    const val VALUE_ITEM_CODE_LOYALTY_CARD = "L"
    const val VALUE_ITEM_CODE_PUSH_MESSAGE = "M"

    const val VALUE_GENDER_MALE = "m"
    const val VALUE_GENDER_FEMALE = "f"

    const val VALUE_STAMP_CARD = "StampCard"
    const val VALUE_POINT_CARD = "PointsCard"

    const val VALUE_CONSUMER_WELCOME = "CONSUMERWELCOMEEMAIL"
}
