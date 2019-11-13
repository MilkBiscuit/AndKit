const offerExample = {
    "active": true,
    "burtCount": 0,
    "description": "qqq",
    "endDate": "2029 Nov 06 00:00:00",
    "id": 220,
    "startDate": "2019 Oct 01 00:00:00",
    "title": "aaa",
    "tags": [],
    "venueIds": [2417],
    "weight": 0
}
const testString = "{\"id\":220,\"title\":\"JK\",\"description\":\"Just kidding\",\"merchantId\":0,\"isMerchantFavourite\":false,\"categoryId\":0,\"sortOrder\":0,\"isPremiumPlacement\":false,\"paymentAmount\":0.0,\"paymentTaxRate\":0.0,\"redemptionCount\":0,\"burntCount\":0,\"isGiftable\":false,\"isAGift\":false,\"venueIds\":[2417]}"

function convertOffer(offer) {
    return {
        "title": offer.title,
        "subTitle": offer.description
    };
}

var PlexureConverter = {
    "convertOffer": convertOfferAndToString
};

function convertOfferAndToString(offerString) {
    var jsonObject = JSON.parse(offerString)
    var convertedOffer = convertOffer(jsonObject);

    return JSON.stringify(convertedOffer);
}

function convertExample() {
    const convertedOffer = convertOffer(offerExample);

    return JSON.stringify(convertedOffer);
}

